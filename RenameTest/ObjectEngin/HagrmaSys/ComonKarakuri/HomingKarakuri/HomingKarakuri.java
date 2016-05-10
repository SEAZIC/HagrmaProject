package ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri;

import java.util.LinkedList;

import BaseSystem.MathPack.Vec2;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/** 
 * 追尾カラクリ
 * 属性：カラクリ
 * プライオリティ：０
 *  追尾歯車{@link HomingHagrma}を動かすカラクリです
 * 追尾歯車{@link HomingHagrma}を使用したい場合はこのカラクリのインスタンスを生成しオブジェクトマネージャーに登録してください
 *  */
public class HomingKarakuri extends Karakuri{

	/** 
	 * カラクリの可動部です
	 * 追尾のための角度修正などが行われています
	 * */
	@Override
	public String call() throws Exception {
		LinkedList<Hagrma> MHs = objM.getHagrmas(HomingHagrma.class);
		if(MHs != null && !MHs.isEmpty())
			for(Hagrma H:MHs){
				if(H instanceof HomingHagrma){
					HomingHagrma hag = (HomingHagrma)H;
					if(!hag.isSetTarget()&&hag.isHoming()){

						BaseObject obj = objM.searchObject(hag.getTargetData());
						if(obj != null){
							
							System.out.println("GetTarget!!!!"+" parent "+H.getParent().getName()+" ");
							hag.setTarget(obj);
						}
					}
					if(hag.isUseAnimation()&&hag.isHoming()){
//						System.out.println("修正");
						Vec2 Na = hag.getNa();
						Vec2 Nb = hag.getNb();
						float cos = Na.getX()*Nb.getX()+(Na.getY()*Nb.getY());
						float sin = Na.getX()*Nb.getY()-(Na.getY()*Nb.getX());
						float cos_max = (float)Math.cos(hag.getTheta_max());
						float sin_max = (float)Math.sin(hag.getTheta_max());
//						System.out.println(" "+Na.getX()+" "+Na.getY());
						if(sin < 0){
							if(cos > 0){
								if(sin*sin < sin_max*sin_max){
									Na = Nb;
								}else{
									Na = new Vec2(
											Na.getX()*cos_max-(Na.getY()*sin_max),
											Na.getX()*sin_max+(Na.getY()*cos_max)
											);
								}
							}else{
								Na = new Vec2(
										Na.getX()*cos_max-(Na.getY()*sin_max),
										Na.getX()*sin_max+(Na.getY()*cos_max)
										);
							}
						}else{
							if(cos > 0){
								if(sin*sin < sin_max*sin_max){
									Na = Nb;
								}else{
									Na = new Vec2(
											Na.getX()*cos_max+(Na.getY()*sin_max),
											(-Na.getX()*sin_max)+(Na.getY()*cos_max)
											);
								}
							}else{
								Na = new Vec2(
										Na.getX()*cos_max+(Na.getY()*sin_max),
										(-Na.getX()*sin_max)+(Na.getY()*cos_max)
										);
							}
						}
//						System.out.println(" "+Na.getX()+" "+Na.getY());
						hag.setNa(Na);
					}
					if(hag.isAnimation())
					hag.move();

				}
			}
		MHs = null;
		return super.call();
	}

}
