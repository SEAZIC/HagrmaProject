package ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri;

import java.awt.Point;
import java.awt.Rectangle;

import BaseSystem.MathPack.Vec2;
import ObjectEngin.HagrmaSys.Hagrma;

/**
 * <PRE>
 * 重力歯車
 * 属性：歯車
 * 重力を表現するための歯車で，非リアルタイム（プレームレートによる更新タイプ的）に速度が加速度で更新される
 * </PRE>
 *  */
public class GravHagrma extends Hagrma{
	
	private Point grav;
	private double accsel;
	private double speed;
	private double maxSpeed;
	
	private boolean enable;
	
	private Vec2 gravVec;
	private Vec2 gravVec_nomarized;
	private float gravVec_length;
	
	/**コンストラクタ */
	public GravHagrma() {
		super();
		grav = new Point(0, 0);
		gravVec = new Vec2(0, 0);
		gravVec_nomarized = new Vec2(0, 0);
		gravVec_length = 0;
		speed = 0;
		maxSpeed = 0;
		enable = true;
	}
	
	/**
	 * <PRE>
	 * 重力の設定メソッド
	 * @param grav {@link Point} 重力の方向
	 * @param accel double 加速度
	 * @param maxSpeed double 重力による最大速度
	 * </PRE>
	 *  */
	public void setGrav(Point grav,double accel,double maxSpeed) {
		this.gravVec.setXY((float)grav.getX(),(float)grav.getY());
		this.gravVec_nomarized = gravVec.nomarized();
		this.gravVec_length = this.gravVec.getlength();
		this.grav = grav;
		this.accsel = accel;
		this.maxSpeed = maxSpeed;
	}
	/**　
	 * <PRE>
	 * 加速度設定
	 * 加速度を設定し直します
	 * @param accsel double 加速度
	 * </PRE>
	 *  */
	public void setAccsel(double accsel) {
		this.accsel = accsel;
	}
	/**
	 * <PRE>
	 * スピード設定
	 * 直接的にスピードを調整します
	 * 地面にいる時には０にするなど特別な時のみ使用してください
	 * @param speed double スピード
	 * </PRE>
	 *  */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * </PRE>
	 * 歯車の有効化
	 * 重力を有効にするかしないかを設定します
	 * デフォルトではtrueです
	 * @param enable boolean 使用するときはtrue
	 * </PRE>
	 *  */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	/** 
	 * <PRE>
	 * カラクリが使用するところです
	 * 基本的には加速度による重力の速度を更新しています
	 * </PRE>
	 * */
	public void movegrav(){
		if(!enable)
			return;
		if((gravVec_nomarized.getX()*parent.getDivx())+(gravVec_nomarized.getY()*parent.getDivy()) < maxSpeed*gravVec_length)
		speed += accsel;
		if(speed > maxSpeed)
			speed = maxSpeed;
//		parent.setOldBox((Rectangle) parent.getBoundBox().clone());
		parent.getBoundBox().translate((int)(grav.x*speed), (int)(grav.y*speed));
	}
}
