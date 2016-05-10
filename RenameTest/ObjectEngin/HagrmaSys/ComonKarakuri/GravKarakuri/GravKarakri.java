package ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri;

import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/** 
 * <PRE>
 * 重力カラクリ
 * 属性：カラクリ
 * プライオリティ：−１
 * 重力歯車{@link GravHagrma}を動かすカラクリです
 * 重力歯車{@link GravHagrma}を使用したい場合はこのカラクリのインスタンスを生成しオブジェクトマネージャーに登録してください
 * </PRE>
 * */
public class GravKarakri extends Karakuri{

	private LinkedList<Hagrma> hagrma;
	/**コンストラクタ */
	public GravKarakri() {

		super();
		priority = 1;
	}
	/** 
	 * <PRE>
	 * カラクリの可動部です
	 * 基本的には重力カラクリをエンジンから得て，すべてのmovegravメソッドを呼び，重力を更新しています
	 * </PRE>
	 * @throws Exception 
	 * */
	@Override
	public String call() throws Exception {
		hagrma = objM.getHagrmas(GravHagrma.class);
		for(Hagrma hag : hagrma){
			if(hag instanceof GravHagrma){
				((GravHagrma)hag).movegrav();
			}
		}
		hagrma = null;
		return super.call();
	}
	
}
