package ObjectEngin.HagrmaSys.CameraKarakuri;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

public class CameraKarakuri extends Karakuri{
	private Rectangle LatestCamera;
	private double tposx;
	private double tposy;

	private boolean enable;
	private boolean autoSetting;
	private boolean stop;
	public CameraKarakuri() {
		LatestCamera = new Rectangle(0, 0, 100, 100);
		tposx = 0.0f;
		tposy = 0.0f;
		autoSetting = true;
		enable = false;
		stop = false;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub

		ArrayList<LinkedList<Hagrma>> th =objM.getchiledHagrmas(TargetHagrma.class);
		ArrayList<LinkedList<Hagrma>> range =objM.getchiledHagrmas(RangeHagrma.class);
		if(autoSetting)
			if(th != null && th.size() > 0){
				enable = true;
			}else{
				enable = false;
				return "No Target";
			}
		double x = 0.0f;
		double y = 0.0f;
		int cnt = 0;
		Rectangle rangeSpace = null;
		if(range == null || range.size() < 1 || th == null || th.size() < 1){
			enable = false;
			return "Error";
		}
		for(LinkedList<Hagrma> targethag: range){
			for(Hagrma taghagrma : targethag){
				Rectangle tagRenge = taghagrma.getParent().getBoundBox();
				if(rangeSpace == null){
					rangeSpace = new Rectangle(tagRenge);
					continue;
				}
				rangeSpace.setBounds(rangeSpace.union(tagRenge));
			}
		}
		LatestCamera.setBounds(rangeSpace);
		//		System.out.println(rangeSpace);
		for(LinkedList<Hagrma> rangeH : th){
			for(Hagrma taghagrma : rangeH){
				if(taghagrma instanceof TargetHagrma){
					TargetHagrma target = (TargetHagrma)taghagrma; 
					x += target.getParent().getBoundBox().getCenterX()*target.getHavy(); 
					y += target.getParent().getBoundBox().getCenterY()*target.getHavy(); 
					cnt += target.getHavy();
				}
			}
		}
		tposx = x/cnt;
		tposy = y/cnt;

		th = null;
		range = null;
		return super.call();
	}

	public void setClip(Graphics g){
		if(!enable)
			return ;
		Rectangle graphicsSize = g.getClipBounds();
		boolean[] flag = new boolean[4];
		boolean[] overflag = new boolean[2];
		if(graphicsSize.getWidth() > LatestCamera.getWidth()){
			overflag[0] = true;
		}
		if(graphicsSize.getHeight() > LatestCamera.getHeight()){
			overflag[1] = true;
		}
		//		System.out.println("tposx = "+tposx+" tposy = "+tposy);
		if(!overflag[0]){
			if(LatestCamera.getMinX()+(graphicsSize.getWidth()/2) > tposx){
				flag[0] = true;
			}
			if(LatestCamera.getMaxX()-(graphicsSize.getWidth()/2) < tposx){
				//			System.out.println(LatestCamera.getMaxX());
				flag[1] = true;
			}
			if(!(flag[0]&flag[1])){
//				System.out.println("flag0 or 1"+" 0 "+flag[0]+" 1 "+flag[1]);
				if(flag[0]){
					tposx = LatestCamera.getMinX()+(graphicsSize.getWidth()/2);
				}
				if(flag[1]){
					tposx = LatestCamera.getMaxX()-(graphicsSize.getWidth()/2);
				}
			}
		}else{
			tposx = (LatestCamera.getWidth()/2);
		}
		if(!overflag[1]){
			if(LatestCamera.getMinY()+(graphicsSize.getHeight()/2) > tposy){
				flag[2] = true;
			}
			if(LatestCamera.getMaxY()-(graphicsSize.getHeight()/2) < tposy){
				flag[3] = true;
			}
			if(flag[2]&flag[3]){
			}else{
				if(flag[2])
					tposy = LatestCamera.getMinY()+(graphicsSize.getHeight()/2);
				if(flag[3])
					tposy = LatestCamera.getMaxY()-(graphicsSize.getHeight()/2);
			}
		}else{
			tposy = ((LatestCamera.getHeight())/2);
		}
		//		graphicsSize.setLocation((int)(tposx-(graphicsSize.getWidth()/2)),(int)(tposy-(graphicsSize.getHeight()/2)));
		//		g.setClip(graphicsSize);
		//		System.out.println("tposx = "+tposx+" tposy = "+tposy);
		//		System.out.println(" flag 0 " + flag[0]+"flag 1 "+flag[1]);
		int clipx = (int)(tposx-(graphicsSize.getWidth()/2));
		int clipy = (int)(tposy-(graphicsSize.getHeight()/2));
		g.translate(-clipx, -clipy);

		ArrayList<LinkedList<Hagrma>> th =objM.getchiledHagrmas(TargetHagrma.class);
		if(th != null && !th.isEmpty())
			for(LinkedList<Hagrma> rangeH : th){
				for(Hagrma taghagrma : rangeH){
					if(taghagrma instanceof TargetHagrma){
						((TargetHagrma) taghagrma).setPos(new Point(clipx, clipy));
					}
				}
			}
	}

}
