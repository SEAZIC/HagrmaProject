package ShootingGame.BulletHagrma;

import java.awt.Rectangle;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrma;
import ShootingGame.GameObject.Bullet.BulletObject;

public class BulletHagrma extends Hagrma{

	private long time;
	private long time_max;
	private String objname;
	private String params;
	private double sizex;
	private double sizey;
	
	private Class<? extends BaseObject> parentClass;
	private Class<? extends BaseObject> targetClass;
	
	public BulletHagrma() {
		setTime(0l);
	}
	@Override
	public void setParent(BaseObject parent) {
		super.setParent(parent);
		if(parent instanceof BulletHagrmaListner){
			parentClass = ((BulletHagrmaListner) parent).getParentClass();
			targetClass = ((BulletHagrmaListner) parent).getTargetClass();
		}
	}
	public void init(String objname,long time_max,double sizex,double sizey,String params){
		this.objname = objname;
		this.time_max = time_max;
		this.params = params;
		this.sizex = sizex;
		this.sizey = sizey;
	}
	public void setTarget(Class<? extends BaseObject> taClass,Class<? extends BaseObject> paClass){
		this.parentClass = paClass;
		this.targetClass = taClass;
	}
	public BulletObject createBullet(BulletObjectManager BOM){
		System.out.println("BulletCreate!!!!");
		BulletObject BObj = null;
		if(this.objname != null){
			System.out.println(params);
			BObj = (BulletObject) BOM.getObj(objname);
			Rectangle b  = parent.getBoundBox();
			BObj.setBoundBox(new Rectangle((int)(b.getCenterX()-(sizex/2)), (int)(b.getCenterY()-(sizey/2)), (int)sizex, (int)sizey));
			BObj.init(params);
			System.out.println(""+(int)(b.getCenterX()-(sizex/2))+" "+(int)(b.getCenterY()-(sizey/2))+" "+(int)sizex+" "+(int)sizey);
			BObj.boxrefresh();
			BObj.setTarget(parentClass, targetClass);
		}
		return BObj;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public void addTime(long dtime){
		this.time += dtime;
	}
	public long getTime_max() {
		return time_max;
	}
	public void setTime_max(long time_max) {
		this.time_max = time_max;
	}
	public boolean isOver(){
		return (time_max < time);
	}
	public void resetTime(){
		time  =0l;
	}
}
