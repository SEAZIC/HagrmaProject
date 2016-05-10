package MERIOCreater.Panel.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.Mapchip;

public class BlockInspectorObject extends BaseObject{

	private Mapchip chip;
	private MapClass MC;
	private Point chipPos;
	private Point old_chipPos;
	
	private boolean datainspect;
	private Color color;

	private Color fontcolor;
	private ArrayList<String> data;
	private int fontsize;

	private int max_x;
	private int max_y;

	public BlockInspectorObject() {
		super();
		BoundBox = new Rectangle(0, 0, 20, 20);
		color = new Color(0,0,0,200);
		fontcolor = new Color(50,255,50);
		chipPos = new Point(0, 0);
		old_chipPos = new Point(chipPos.x,chipPos.y);
		name = "inspector";
		fontsize = 12;
	}
	@Override
	public void show(Graphics g) {
		super.show(g);
		int posx = getBoundBox().x;
		int posy = getBoundBox().y;
		if(parentobj != null){
			//			System.out.println(">>>>");
			if(g.getClipBounds().getWidth() > parentobj.getBoundBox().getMinX()+max_x){
				posx += parentobj.getBoundBox().getMinX();
			}else{
				posx = (int) (g.getClipBounds().getWidth()-max_x);
			}
			if(g.getClipBounds().getHeight() > parentobj.getBoundBox().getMaxY()+max_y){
				posy += parentobj.getBoundBox().getMaxY();
			}else{
				posy = (int) (parentobj.getBoundBox().getMinY()-max_y);
			}
		}
		if(datainspect){
			g.setColor(color);
			g.fillRect(posx, posy, max_x, max_y);
			g.setColor(fontcolor);
			for(int cnt = 0;cnt < data.size();cnt++){
				g.drawString(data.get(cnt), posx, posy+((cnt+1)*fontsize));
			}
		}
	}
	public void setMC(MapClass mC) {
		MC = mC;
	}
	public MapClass getMC() {
		return MC;
	}
	@Override
	public String call() throws Exception {
		if(MC != null){
			if(old_chipPos.x != chipPos.x || old_chipPos.y != chipPos.y){
				old_chipPos = new Point(chipPos.x,chipPos.y);
				chip = MC.getMap(chipPos.x, chipPos.y);
				int maxlong = 0;
				if(chip != null){
					datainspect = true;
					ArrayList<String> copybuff = chip.getData();
					data = new ArrayList<String>();
					for(int cnt =0;cnt < copybuff.size(); cnt++){
						if(copybuff.get(cnt).startsWith("image") 
								|| copybuff.get(cnt).startsWith("posx") || copybuff.get(cnt).startsWith("posy") 
								|| copybuff.get(cnt).startsWith("sizex") || copybuff.get(cnt).startsWith("sizey") 
								|| copybuff.get(cnt).startsWith("chipsizex") || copybuff.get(cnt).startsWith("chipsizey") 
								){
							continue;
						}
						if(maxlong < copybuff.get(cnt).length()){
							maxlong = copybuff.get(cnt).length();
						}
						data.add(copybuff.get(cnt));
					}
					max_x = (int) (maxlong*fontsize*0.6);
					max_y = data.size()*fontsize;

				}else{
					datainspect = false;
				}
			}
		}else{
			datainspect = false;
		}
		return super.call();
	}
	public Point getChipPos() {
		return chipPos;
	}
	public void setChipPos(Point chipPos) {
		this.chipPos = chipPos;
	}
}
