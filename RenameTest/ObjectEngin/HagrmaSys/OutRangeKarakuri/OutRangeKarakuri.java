package ObjectEngin.HagrmaSys.OutRangeKarakuri;

import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/** 
 * <PRE>
 * 区画外識別カラクリ
 * 属性：特殊カラクリ
 * プライオリティ：０
 * 画面外に出たときなどの処理に使用する区画外識別歯車{@link OutRangeHagrma}を動かすカラクリです
 * すでにオブジェクトマネージャーに設定されています
 * </PRE>
 *  */
public class OutRangeKarakuri extends Karakuri{

	@Override
	public String call() throws Exception {
		if(!objM.isOut())
			return "ErrorRange";
		ArrayList<LinkedList<Hagrma>> hagrmalsit = objM.getchiledHagrmas(OutRangeHagrma.class);
		for(LinkedList<Hagrma> linkedhagrmalist:hagrmalsit){
			for(Hagrma hag:linkedhagrmalist){
				if(hag instanceof OutRangeHagrma){
					if(((OutRangeHagrma) hag).isOutAction()){
						if(hag.getParent() instanceof OutRangeHagrmaListner){
							if(!objM.getOutRange().contains(hag.getParent().getBoundBox())){
								OutRangeHagrmaListner outl = (OutRangeHagrmaListner) hag.getParent();
								outl.outAction();
							}
						}
					}
				}
			}
		}


		return super.call();
	}

}
