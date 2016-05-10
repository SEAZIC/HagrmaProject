package ShootingGame.StateHagrma;

import ObjectEngin.ComonObject.BaseObject;

/** ステータス歯車の状態取得リスナーインターフェース */
public interface StateHagrmaListner {
	/** ステータスが変化した時に呼び出すメソッド */
	public void chengedState(StateHagrma shagrma);
	
}
