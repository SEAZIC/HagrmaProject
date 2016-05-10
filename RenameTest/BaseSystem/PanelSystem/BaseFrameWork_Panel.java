package BaseSystem.PanelSystem;

import javax.swing.JFrame;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
/**
 * JPanel用ベースシステムフレームワークインターフェース
 * {@link JFrame}を使用するときに推奨しているパネル用フレームワーク
 *  */
public interface BaseFrameWork_Panel {
	public boolean shincRock = true;
	public void setParent(BaseFrameWork_Frame parent);
	public void panelrun();
}
