package ObjectEngin.dynamicObj;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import BaseSystem.TextEdit.TextReader;
import ObjectEngin.ComonObject.BaseObject;

/**
 * ダイナミックオブジェクトリーダー
 * テキストファイルからダイナミックオブジェクトマネージャーのマッピングを利用して一行ごとにオブジェクトを生成する
 * */
public class DynaObjReader {

	private TextReader TR;
	
	/**　コンストラクタ */
	public DynaObjReader(String filename) {
		TR = new TextReader();
		readFile(filename);
	}
	/**テキストリードメソッド　
	 * テキストファイルを読み込む
	 * @param filename String 読み込むファイル名
	 * @return boolean 読み込めたならtrue
	 *  */
	private boolean readFile(String filename){
		TR.readFile(filename);
		return TR.isTextFound();
	}
	
	/** 
	 * オブジェクトテキストコンバート
	 * ダイナミックオブジェクトマネージャーのマッピングを利用してテキストファイルからオブジェクトを生成します
	 *  */
	public LinkedList<BaseObject> getDynaObjects(DynaObjManager DOM,int chipsize){
		LinkedList<BaseObject> obj = new LinkedList<BaseObject>();
		for(String S :TR.getTextData()){
			String[] spr = S.split(",");
			this.addAction(obj,DOM, spr,chipsize);
		}
		return obj;
	}
	/**
	 * オブジェクト生成規則
	 * ここでは１行ごとにどのようにオブジェクトを生成するかを具体的に決めている
	 * 生成の仕方，つまりパラメータの追加，変更，削除や，どのように作っていくかなど細かい内容を決める
	 *  */
	protected void addAction(LinkedList<BaseObject> obj,DynaObjManager DOM,String[] spr,int chipsize){
		if(spr.length > 4){
			Rectangle rec = new Rectangle(
					(int)(Double.parseDouble(spr[0])*chipsize),
					(int)(Double.parseDouble(spr[1])*chipsize),
					(int)(Double.parseDouble(spr[2])*chipsize),
					(int)(Double.parseDouble(spr[3])*chipsize)
			);
			BaseObject dyObj = DOM.getObj(spr[4]);
			
			dyObj.getBoundBox().setBounds(rec);
//			dyObj.getOldBox();
			if(dyObj != null)
			obj.add(dyObj);
		}
	}
}
