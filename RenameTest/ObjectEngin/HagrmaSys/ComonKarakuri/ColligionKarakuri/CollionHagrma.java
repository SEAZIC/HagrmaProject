package ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri;

import java.awt.Rectangle;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;

/**
 * <PRE>
 * 当たり判定歯車
 * 属性：歯車
 * 当たり判定用の歯車です
 * </PRE>
 *  */
public class CollionHagrma extends Hagrma{

//	private Rectangle rect;
	private String tag;
	/**　コンストラクタ */
	public CollionHagrma() {
		setTag("");
	}
	/** 
	 * <PRE>
	 * オブジェクト登録
	 * オブジェクトに歯車を登録した時に歯車にオブジェクトを登録します
	 * 基本的には触らないでください
	 * </PRE>
	 *  */
	@Override
	public void setParent(BaseObject parent) {
		// TODO Auto-generated method stub
		super.setParent(parent);
//		rect = parent.getBoundBox();
	}
	/**
	 * <PRE>
	 * タグ登録
	 * タグを登録します
	 * @param tagname String 設定するタグの名前
	 * </PRE>
	 *  */
	public void setTag(String tagname){
		this.tag = tagname;
	}
	/**　
	 * <PRE>
	 * タグ取得
	 * 登録されているタグを取得します
	 * @return String 設定されたタグの名前
	 * </PRE>
	 *  */
	public String getTag() {
		return tag;
	}
	/**
	 * <PRE>
	 * 当たり判定
	 * 当たり判定の大きさをレクタングルで返します
	 * @return {@link Rectangle} 当たり判定の大きさ
	 * </PRE>
	 *  */
	public Rectangle getRect() {
		return parent.getBoundBox();
	}
	/**
	 * <PRE>
	 *  当たり判定チェック
	 * 引数に渡したレクタングルとの当たり判定
	 * @param rect {@link Rectangle} この歯車と当たり判定を行うレクタングル
	 * @return boolean 当たったらtrue 
	 * </PRE>
	 * */
	public boolean isHit(Rectangle rect){
		return this.parent.getBoundBox().intersects(rect);
	}
	
	//以下はインターフェースの内容により割愛
	
	public void RightPushed(){
		if(parent instanceof ColligionHagrmaInf){
			((ColligionHagrmaInf) parent).RightPushed();
		}
	}
	public void LeftPushed(){
		if(parent instanceof ColligionHagrmaInf){
			((ColligionHagrmaInf) parent).LeftPushed();
		}
	}
	public void UpPushed(){
		if(parent instanceof ColligionHagrmaInf){
			((ColligionHagrmaInf) parent).UpPushed();
		}
	}
	public void DownPushed(){
		if(parent instanceof ColligionHagrmaInf){
			((ColligionHagrmaInf) parent).DownPushed();
		}
	}
}
