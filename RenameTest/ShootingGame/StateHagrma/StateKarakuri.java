package ShootingGame.StateHagrma;

import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/** ステートカラクリ
 *  属性：カラクリ
 *  ステート歯車を管理，運用するカラクリ*/
public class StateKarakuri extends Karakuri{
	/**カラクリ運用Callメソッド 
	 * @return String デフォルトCallメソッドの戻り値
	 * 歯車をオブジェクトマネージャーから所得して更新処理をかける．
	 * また，アニメーションタイマーが止まっていれば次のステートに進むよう，Nextメソッドを呼び出す．*/
	@Override
	public String call() throws Exception {
		ArrayList<LinkedList<Hagrma>> stateHagrma = objM.getchiledHagrmas(StateHagrma.class);
		if(stateHagrma != null && !stateHagrma.isEmpty())
		for(LinkedList<Hagrma> shagrma:stateHagrma){
			for(Hagrma statehag:shagrma){
//				System.out.println("222");
				if(statehag instanceof StateHagrma){
					((StateHagrma) statehag).refresh();
					if(!statehag.isAnimation())
						((StateHagrma) statehag).next();
				}
			}
		}
		stateHagrma = null;
		return super.call();
	}
}
