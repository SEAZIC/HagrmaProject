package ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri;

import java.util.Random;

import javax.naming.InitialContext;

import BaseSystem.Animation.Animationtime;
import BaseSystem.MathPack.Vec2;
import BaseSystem.MathPack.patternDif.Sin;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;

/**
 * 追尾歯車
 * 属性：歯車
 * ターゲットとなるオブジェクトに対し，追尾移動します
 * また，ターゲットとなるオブジェクトの名前を渡すことで自動的に検索して追尾します
 *  */
public class HomingHagrma extends Hagrma{

	private BaseObject target;
	private int power;
	private double theta_max;
	private Vec2 Na;
	private Vec2 initNa;
	
	private boolean isSetTarget;
	private String targetData;
	private boolean homing;
	
	/**
	 * <PRE>
	 * コンストラクタ
	 * ターゲットとなるオブジェクトが明白の時に使用してください
	 * @param target {@link BaseObject} ターゲットとなるオブジェクト
	 * @param power int 追尾する力
	 * @param theta_Max double 追尾する時の修正最大角度
	 * </PRE>
	 *  */
	public HomingHagrma(BaseObject target,int power,double theta_Max) {
		this(power,theta_Max);
		this.setTarget(target);
	}
	/**
	 * コンストラクタ
	 * ターゲットとなるオブジェクトの名前しかわからない時に使用してください
	 * @param power int 追尾する力
	 * @param theta_Max double 追尾する時の修正最大角度
	 *  */
	public HomingHagrma(int power,double theta_Max) {
		super();
		Init(power, theta_Max, 1, 0, 2, true);
		setTargetData("noTarget");
		setUseAnimation(true);
		setHoming(true);
	}
	/**
	 * <PRE>
	 * コンストラクタ
	 * パラメータを一つの文字列型として入力します
	 * param7以降は任意パラメータ
	 * param 1 power 	 double 	追尾する力
	 * param 2 theta_Max double 	追尾する時の修正最大角度
	 * param 3 double 	 t			追尾アニメーションタイマー
	 * param 4 double 	 min		追尾アニメーションタイマー最小値
	 * param 5 double 	 max		追尾アニメーションタイマー最大値
	 * param 6 boolean 	 loop		ループフラグ
	 * *param 7 String 	 target		追尾対象の名前
	 * *param 8 boolean	 homing		ホーミングするかしないか	
	 * *param 9	double	 inittheta	初期角度
	 * </PRE>
	 *  */
	public HomingHagrma(String S){
		String[] split = S.split(",");
		if(split.length > 5)
			Init((int)Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), Boolean.parseBoolean(split[5]));
		if(split.length > 6)
		setTargetData(split[6]);
		if(split.length > 7){
			System.out.println("setHoming " + split[7]);
			setUseAnimation(Boolean.parseBoolean(split[7]));
//			this.setNa(getNb());
			setHoming(Boolean.parseBoolean(split[7]));
		}
		if(split.length > 8){
//			System.out.println(""+split[8]);
			double initTheta = Double.parseDouble(split[8]);
			this.setNa(new Vec2((float)Math.cos(initTheta),(float)Math.sin(initTheta)));
			initNa.setXY(this.Na.getX(),this.Na.getY());
		}
	}
	/**
	 *  初期化
	 * 触る必要はありません
	 *  */
	private void Init(int power,double theta_Max,double t,double min,double max,boolean loop){
//		System.out.println(theta_Max);
		this.setPower(power);
		this.setTheta_max(theta_Max);
		float x = (new Random().nextFloat());
		x = (x*2.0f) - 1.0f;
		int r = (new Random().nextInt(2)*2)-1;
		this.setNa(new Vec2(x, ((float)(r))*(float)Math.sqrt(1-(x*x))));
		this.initNa = new Vec2(0, 0);
		initNa.setXY(this.Na.getX(),this.Na.getY());
		setAnimetion(new Animationtime(t, min, max, loop));
	}
	/**
	 *  ターゲットオブジェクト取得
	 * 登録されているターゲットを取得します
	 * @return {@link BaseObject} ターゲットオブジェクト
	 *  */
	public BaseObject getTarget() {
		return target;
	}
	
	/**
	 *  ターゲット登録
	 * ターゲットオブジェクトを登録します
	 * @param target {@link BaseObject} 登録するターゲットオブジェクト
	 *  */
	public void setTarget(BaseObject target) {
		setSetTarget(true);
		this.target = target;
		if(parent != null){
			Vec2 vc2 = getNb();
			this.setNa(new Vec2(-vc2.getX(), -vc2.getY()));
		}
	}

	/**
	 * 移動力取得
	 * 現在設定されているパワーを取得します
	 * @return int 取得するパワー
	 *  */
	public int getPower() {
		return power;
	}

	/**
	 * 移動力登録
	 * パワーを登録します
	 * @param power int 登録するパワー
	 *  */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * 修正最大角度取得
	 * 修正最大角度を取得します
	 * @return double 修正角度
	 *  */
	public double getTheta_max() {
		return theta_max;
	}

	/**
	 * 修正最大角度登録
	 * 修正最大角度を登録します
	 * @param theta_max double 登録する修正最大角度
	 *  */
	public void setTheta_max(double theta_max) {
		this.theta_max = theta_max;
	}

	/**カラクリで使用する */
	public Vec2 getNa() {
		return Na;
	}

	/**カラクリで使用する */
	public void setNa(Vec2 na) {
		Na = na;
	}
	/**カラクリで使用する */
	public Vec2 getNb(){
//		System.out.println(""+target.getBoundBox().getX()+" "+target.getBoundBox().getY()+"");
		return new Vec2(
				(float)(parent.getBoundBox().getCenterX()-target.getBoundBox().getCenterX()),
				(float)(parent.getBoundBox().getCenterY()-target.getBoundBox().getCenterY())
				).nomarized();
	}
	/**カラクリで使用する */
	public void move(){
		parent.setOldBox(parent.getBoundBox());
		parent.getBoundBox().translate((int)(Na.getX()*power), (int)(Na.getY()*power));
		if(power > 5)
		Na = new Vec2(
				(float)(parent.getBoundBox().getX()-parent.getOldBox().getX()),
				(float)(parent.getBoundBox().getY()-parent.getOldBox().getY())
				).nomarized();
		if(Na.iserror()){
//			Na.setX(1.0f);
			Na = initNa;
		}
	}

	/**
	 * ターゲット登録確認
	 * ターゲットが登録されているかを確認します
	 * @return boolean 登録されていればtrue
	 * */
	public boolean isSetTarget() {
		return isSetTarget;
	}

	private void setSetTarget(boolean isSetTarget) {
		this.isSetTarget = isSetTarget;
	}
	/**
	 * ターゲットネーム取得
	 * ターゲットの名前を取得します
	 * ターゲットが設定されていない時はnoTargetが文字列型として渡されます
	 * @return String ターゲットの名前，ない時はnoTarget
	 *  */
	public String getTargetData() {
		return targetData;
	}
	/** 
	 * ターゲットネーム登録
	 * ターゲットの名前を登録します
	 * すでにターゲットが設定されている場合再検索がかかります
	 * @param targetData String ターゲットの名前を登録
	 *  */
	public void setTargetData(String targetData) {
		this.isSetTarget = false;
		this.targetData = targetData;
	}
	/**<PRE> 
	 * ホーミングフラグ
	 * </PRE> */
	public boolean isHoming() {
		return homing;
	}
	private void setHoming(boolean homing) {
		this.homing = homing;
	}
	@Override
	public void setParent(BaseObject parent) {
		// TODO Auto-generated method stub
		super.setParent(parent);
		System.out.println("parent " +parent.getName()+this.isSetTarget);
	}
}
