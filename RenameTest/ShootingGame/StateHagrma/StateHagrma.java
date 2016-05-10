package ShootingGame.StateHagrma;

import java.util.HashMap;
import java.util.LinkedList;

import BaseSystem.Animation.Animationtime;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
/** ステート歯車
 * 属性：歯車
 *  行動パターンを保持し，アニメーションタイマーを使用してオブジェクトの歯車を管理する*/
public class StateHagrma extends Hagrma{
/**管理する歯車のリスト */
	private HashMap<String,LinkedList<Hagrma>> stock;
	/**現在のステータス */
	private int state;
	/**ステータスごとのアニメーションタイマー */
	private HashMap<String,Animationtime> animes;
	/**ステータスの順番 */
	private LinkedList<Integer> animationMap;
	/**空かどうかを判定するフラグ */
	private boolean empty;
/** コンストラクタ
 * フィールド変数の初期化
 * */
	public StateHagrma() {

		stock = new HashMap<String,LinkedList<Hagrma>>();
		animes = new HashMap<String, Animationtime>();
		animationMap = new LinkedList<Integer>();
		state = 0;
		empty = false;

	}
	/** 初期化用メソッド
	 * @param S String... 汎用パラメータ
	 * 引数には文字列型に直したパラメータを配列で入力
	 * ステートの初期化を行います
	 * */
	public void Init(String... S){
		animes.put(S[0],new Animationtime(Double.parseDouble(S[1]), Double.parseDouble(S[2]), Double.parseDouble(S[3]), Boolean.parseBoolean(S[4])));
		animationMap.add(Integer.parseInt(S[0]));
	}
	/**歯車追加メソッド
	 * @param state int 追加する歯車のステータス
	 * @param hagrma {@link Hagrma} 追加する歯車
	 * ステータスごとに歯車を追加する */
	public void addHagrma(int state,Hagrma hagrma){
		if(!stock.containsKey(""+state)){
			stock.put(""+state, new LinkedList<Hagrma>());
		}
		stock.get(""+state).add(hagrma);
	}
	@Override
	public void setParent(BaseObject parent) {
		super.setParent(parent);
		if(stock.containsKey(""+state)){
			for(Hagrma listHagrma : stock.get(""+state)){
				parent.addHagrma(listHagrma);
			}
			stock.remove(""+state);
		}

		state = animationMap.removeFirst();
		setAnimetion(animes.remove(""+state));
		
		if(stock.containsKey(""+state)){
			for(Hagrma listHagrma : stock.get(""+state)){
				parent.addHagrma(listHagrma);
			}
		}

	}
	/**リフレッシュ
	 * アニメーションタイマーの更新 */
	public void refresh(){
		getAnimation().refresh();
	}
	/**次のステータスに移動するメソッド
	 * 次ステータスの歯車があれば今オブジェクトに渡している歯車を消して次のステータスの歯車を追加する */
	public void next(){
		if(isEmpty())
			return;
		
		if(stock.containsKey(""+state)){
			for(Hagrma listHagrma : stock.get(""+state)){
				parent.removeHagrma(listHagrma);
			}
		}
		if(animationMap.isEmpty()){
			setEmpty(true);
			if(parent instanceof StateHagrmaListner)
				((StateHagrmaListner) parent).chengedState(this);
			return;
		}
		state = animationMap.removeFirst();
		setAnimetion(animes.remove(""+state));

		if(stock.containsKey(""+state)){
			for(Hagrma listHagrma : stock.get(""+state)){
				parent.addHagrma(listHagrma);
			}
		}

		if(parent instanceof StateHagrmaListner)
			((StateHagrmaListner) parent).chengedState(this);
	}
	/** 次のステータスが空かどうかを確認するメソッド */
	public boolean isEmpty() {
		return empty;
	}
	/** 強制的に空かどうかを指定するメソッド */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	/** 現在のステータスを取得するメソッド */
	public int getState() {
		return state;
	}
}
