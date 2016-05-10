package ObjectEngin.dynamicObj;

import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.MapReader;

/**
 * <PRE>
 * ダイナミックオブジェクトクリエイター
 * ダイナミックオブジェクトマネージャーと
 * ダイナミックオブジェクトリーダーと
 * オブジェクトマネージャーを関連づけ，ダイナミックオブジェクトを生成する
 * </PRE> 
 */
public class DynaObjCreater {

	protected DynaObjReader DOR;
	
	public DynaObjCreater() {
	}
	/** 下位互換のために残してるもの */
	public void setCreate(ObjectManager OM,DynaObjManager DOM,MapClass MC,String S){
		DOR = new DynaObjReader(S);
		setObjM(OM, DOM, MC.getChipsize());
	}
	/** 下位互換のために残してるもの */
	public void setCreate(ObjectManager OM,DynaObjManager DOM,MapClass MC,DynaObjReader DOR){
		setCreate(OM, DOM, MC.getChipsize(), DOR);
	}
	/**　
	 * クリエイトメソッド 
	 * このメソッドを呼び出すことで関連づけを行い，オブジェクトの生成をしている
	 *  */
	public void setCreate(ObjectManager OM,DynaObjManager DOM,int MCchipsize,DynaObjReader DOR){
		this.DOR = DOR;
		setObjM(OM, DOM, MCchipsize);
	}
	
	/**
	 *  ダイナミックオブジェクトリーダーで読み込んだオブジェクトパラメータを
	 * ダイナミックオブジェクトマネージャーを使用してオブジェクト群に直し，
	 * それをオブジェクトマネージャーに追加する処理
	 * 継承して中を書き換えられるようにしてある
	 * */
	protected void setObjM(ObjectManager OM,DynaObjManager DOM,int MCchipsize){
		LinkedList<BaseObject> obj = DOR.getDynaObjects(DOM, MCchipsize);
		for(BaseObject bo : obj){
			OM.addObjct(bo);
		}
	}
}
