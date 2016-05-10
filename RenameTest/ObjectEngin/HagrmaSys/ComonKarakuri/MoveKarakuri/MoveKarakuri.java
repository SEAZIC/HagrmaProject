package ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri;

import java.util.LinkedList;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/**
 * <PRE>
 * 移動カラクリ
 * 属性：カラクリ
 * プライオリティ：0
 * 移動歯車を動かすカラクリです
 * 移動歯車を使用したい場合はこのカラクリのインスタンスを生成しオブジェクトマネージャーに登録してください
 * </PRE>
 *
 */
public class MoveKarakuri extends Karakuri {

    public MoveKarakuri() {
        priority = 0;
    }

    @Override
    public String call() throws Exception {
        LinkedList<Hagrma> MHs = objM.getHagrmas(MoveHagrma.class);
        if (MHs != null && MHs.size() > 0) {
            for (Hagrma H : MHs) {
                if (H instanceof MoveHagrma) {
                    ((MoveHagrma) H).move();
                    if (((MoveHagrma) H).isStop()) {
                        BaseObject BO = H.getParent();
                        if (BO instanceof MoveHagrmaListner) {
                            ((MoveHagrmaListner) BO).stoped();
                        }
                    }
                }
            }
        }
        MHs = null;
        return super.call();
    }

}
