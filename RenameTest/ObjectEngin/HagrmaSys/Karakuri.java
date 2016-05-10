package ObjectEngin.HagrmaSys;

import java.util.concurrent.Callable;

import ObjectEngin.ObjectManager;

/** 
 * <PRE>
 * カラクリ
 * 属性：カラクリ
 * プライオリティ：０　デフォルト値
 * これを継承してカラクリをオブジェクトマネージャーに追加することでエンジンを構成し，
 * また，カラクリが歯車を操作することで，ほぼ最小の処理量でオブジェクトを間接的に操作する
 * </PRE>
 * */
public class Karakuri implements Callable<String>{

	protected ObjectManager objM;
	
	protected int priority;
	public Karakuri() {
		priority = 0;
	}
	public void setObjM(ObjectManager objM){
		this.objM = objM;
	}
	
	public int getPriority() {
		return priority;
	}
	@Override
	public String call() throws Exception {
		
		return null;
	}
}
