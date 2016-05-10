package MERIOGame.Sample;

import java.awt.Graphics;
import java.awt.Point;

import MERIOGame.Sample.KeyInputKarakuri.MERIOKeyinputHagrma;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.CameraKarakuri.TargetHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.ColligionHagrmaInf;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravHagrma;

public class TestObj extends BaseObject implements ColligionHagrmaInf{
	private GravHagrma gv;
	private MERIOKeyinputHagrma keyH;
	public TestObj() {
		super();
		BoundBox.setLocation(20, 20);
		BoundBox.setSize(30,40);
		keyH = new MERIOKeyinputHagrma();
		gv = new GravHagrma();
		keyH.setGrav(gv);
		gv.setGrav(new Point(0,1),0.3,20);
		addHagrma(gv);
		addHagrma(keyH);
		TargetHagrma TH;
		addHagrma(TH = new TargetHagrma());
		TH.setHavy(5);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		super.show(g);
		g.fillRect(BoundBox.x, BoundBox.y, BoundBox.width, BoundBox.height);
	}
	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		super.reverse();
//		gv.setEnable(false);
		gv.setSpeed(0);
	}
	@Override
	public void boxrefresh() {
		// TODO Auto-generated method stub
		super.boxrefresh();
//		gv.setSpeed(0);
	}

	@Override
	public void RightPushed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LeftPushed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpPushed() {
		gv.setSpeed(0);
		keyH.setJump(false);
	}

	@Override
	public void DownPushed() {
		// TODO Auto-generated method stub
		gv.setSpeed(0);
	}
}
