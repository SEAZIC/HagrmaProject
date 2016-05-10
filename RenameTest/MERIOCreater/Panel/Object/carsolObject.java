package MERIOCreater.Panel.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import MERIOCreater.Panel.Hagrma.carsolMouseHagrma;
import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.CameraKarakuri.CameraTargetListner;
import ObjectEngin.HagrmaSys.CameraKarakuri.TargetHagrma;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.Mapchip;

public class carsolObject extends BaseObject implements CameraTargetListner{
	private Color color;
	private Point pos;
	private Point chipPos;
	private int size;
	private ObjectManager objManeger;
	
	private boolean wet;

	private Mapchip setchip;
	
	private MapClass MC;
	
	public carsolObject() {
		super();
		name = "carsor";
		color = new Color(0,255,0);
		carsolMouseHagrma cmh = new carsolMouseHagrma();
		carsolKeyHagrma ckh = new carsolKeyHagrma();
		addHagrma(cmh);
		addHagrma(ckh);
		addHagrma(new TargetHagrma());
		addHagrma(new carsolKeyHagrma());
		pos = new Point(0, 0);
		chipPos = new Point(0,0);
		size = 24;


	}
	@Override
	public void addMane(ObjectManager objM) {
		// TODO Auto-generated method stub
		super.addMane(objM);
		this.objManeger = objM;
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		super.show(g);
		//		g.setColor();
		g.setColor(color);
		g.drawRect(getBoundBox().x, getBoundBox().y, getBoundBox().width, getBoundBox().height);
	}
	@Override
	public synchronized void setLocate(int x, int y) {
		super.setLocate(((x+pos.x)/size)*size-1, ((y+pos.y)/size)*size-1);
		BaseObject child = getChildIndex("inspector");
		chipPos = new Point(((x+pos.x)/size),((y+pos.y)/size));
		if(child != null && child instanceof BlockInspectorObject){
			
			((BlockInspectorObject) child).setChipPos(chipPos);
		}
	}
	public void setPos_ChipSize(Point pos,int size){
		this.pos = pos;
		setChipsize(size);
	}
	public void setChipsize(int size){
		this.size = size;
		BoundBox.setSize(size+2, size+2);
	}
	@Override
	public void locateChenge(Point pos) {
		this.pos.setLocation(pos);
	}
	public void movechip(int x,int y){
		moveBox(x*size, y*size);
		chipPos.translate(x, y);
		BaseObject child = getChildIndex("inspector");
		if(child != null && child instanceof BlockInspectorObject){
			System.out.println(chipPos.x +" "+chipPos.y);
			((BlockInspectorObject) child).setChipPos(chipPos);
		}
	}
	public ObjectManager getObjM(){
		return objManeger;
	}
	public boolean isWet() {
		return wet;
	}
	public void setWet(boolean wet) {
		this.wet = wet;
	}
	public Mapchip getSetchip() {
		return setchip;
	}
	public void setSetchip(Mapchip setchip) {
		this.setchip = setchip;
	}
	public void paintcell(){
		if(wet){
			if(MC.getMap(chipPos.x, chipPos.y) != null)
			MC.getIntmaps()[chipPos.x][chipPos.y] = setchip.getMapID();
		}
	}
	public MapClass getMC() {
		return MC;
	}
	public void setMC(MapClass mC) {
		MC = mC;
	}
	
}
