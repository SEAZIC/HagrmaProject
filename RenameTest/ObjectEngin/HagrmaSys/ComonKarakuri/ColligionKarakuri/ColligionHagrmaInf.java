package ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri;

/**
 * <PRE>
 * 当たり判定歯車インターフェース
 * マップとの当たり判定などプッシュされた方からものがぶつかったことを表すメソッド
 * 現在はマップクラスを使用している時のみ使用可能
 * </PRE>
 *
 */
public interface ColligionHagrmaInf {

    public void RightPushed();

    public void LeftPushed();

    public void UpPushed();

    public void DownPushed();

}
