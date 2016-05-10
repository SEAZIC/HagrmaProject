package ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri;

import ObjectEngin.ComonObject.BaseObject;

/**
 * <PRE>
 * 　当たり判定インターフェース
 * ぶつかってきたオブジェクトと当たり判定歯車{@link CollionHagrma}を取得するリスナー
 * </PRE>
 *
 */
public interface ColligionHitInf {

    public void hitAction(BaseObject otherObject, CollionHagrma othercol);
}
