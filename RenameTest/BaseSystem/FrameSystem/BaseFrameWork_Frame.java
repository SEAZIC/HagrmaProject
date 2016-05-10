package BaseSystem.FrameSystem;

import javax.swing.JFrame;

import BaseSystem.maintools.MessagePack;

/**
 * <PRE>
 * {@link JFrame}用ベースシステムフレームワークインターフェース
 * ベースシステムに {@link JFrame}を登録するときに継承してもらうインターフェース
 * </PRE>
 */
public interface BaseFrameWork_Frame {
//	String FrameName = "DefaultFrameName";

    public boolean shincRock = true;

    /**
     * １フレームごとに呼ばれる処理
     */
    public void run();

    /**
     * メッセージの送信
     */
    public MessagePack getmessage();

    /**
     * メッセージの受け取り
     */
    public void setmessage(MessagePack param);

    /**
     * エンジン内でメッセージを削除したときに呼ばれる
     */
    public void removeMessage();

    /**
     * フレームの名前を設定する
     */
    public void setFrameName(String S);

    /**
     * フレームの名前を取得する
     */
    public String getFrameName();
}
