package ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri;

/**
 * <PRE>
 *  移動歯車リスナー
 * 移動歯車からの通知を受け取るのに使用します
 * </PRE>
 *
 */
public interface MoveHagrmaListner {

    /**
     * <PRE>
     * 移動歯車終了通知
     * 移動歯車の移動処理が終了した時に通知がきます
     * </PRE>
	 *
     */
    public void stoped();

}
