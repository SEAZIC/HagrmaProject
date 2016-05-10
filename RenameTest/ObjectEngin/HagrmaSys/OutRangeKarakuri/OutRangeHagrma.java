package ObjectEngin.HagrmaSys.OutRangeKarakuri;

import ObjectEngin.HagrmaSys.Hagrma;

/**
 * <PRE>
 * 区画外識別歯車
 * 属性：歯車
 * オブジェクトが区画外識別カラクリ {@link OutRangeKarakuri} で指定した範囲を超えたかどうかを識別する歯車です
 * 区画外と識別された場合，区画外識別リスナー{@link OutRangeHagrmaListner}を継承しているオブジェクトはoutActionメソッドが呼ばれます
 * </PRE>
 *
 */
public class OutRangeHagrma extends Hagrma {

    private boolean outAction;

    public OutRangeHagrma() {
        outAction = true;
    }

    public boolean isOutAction() {
        return outAction;
    }

    public void setOutAction(boolean outAction) {
        this.outAction = outAction;
    }
}
