package ObjectEngin.HagrmaSys.OutRangeKarakuri;

/**
 * <PRE>
 * 区画外識別リスナー
 * 区画外識別歯車 {@link OutRangeHagrma}を持つオブジェクトはこのリスナーを継承することで
 * 区画外識別カラクリ {@link OutRangeKarakuri}で登録した区画から外に出るとoutActionメソッドが呼ばれます
 * </PRE>
 *
 */
public interface OutRangeHagrmaListner {

    /**
     * あうとあくしょん
     */
    public void outAction();

}
