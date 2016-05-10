package ObjectEngin.dynamicObj;

import java.util.ArrayList;
import java.util.HashMap;

import ObjectEngin.ComonObject.BaseObject;

/**
 *  ダイナミックオブジェクトマネージャー
 *  オブジェクトネームとオブジェクトクラスをマッピングするクラス
 *  インスタンスを生成した後，
 *  テンプレートとなるオブジェクトをaddメソッドを使用して追加します．
 *  getメソッドにオブジェクトネームを指定した場合，
 *  追加されたオブジェクトの中からオブジェクトネームが同じもののインスタンスを生成します．
 * */
public class DynaObjManager {

	private ArrayList<Class<? extends BaseObject>> templeobj;
	private HashMap<String, Integer> objmap;
	
	/**　コンストラクタ */
	public DynaObjManager() {
		templeobj = new ArrayList<Class<? extends BaseObject>>();
		objmap = new HashMap<String,Integer>();
	}
	
	/**
	 * addメソッド
	 * 追加したBaseオブジェクトからオブジェクトネームをキー，オブジェクトのクラスを値としてマッピングします
	 *  */
	public void addtempObj(BaseObject Bobj){
		try{
		templeobj.add(Bobj.getClass());
		}catch(Exception e){
			e.printStackTrace();
		}
		objmap.put(Bobj.getName(), templeobj.size()-1);
	}
	

	/**
	 * addメソッド
	 * 追加したBaseオブジェクトのクラスから強制的にインスタンスを生成した後，
	 * オブジェクトネームをキー，オブジェクトのクラスを値としてマッピングします
	 *  */
	public void addtempObj(Class<? extends BaseObject> Bobjclass){
		BaseObject Bobj;
		try {
			Bobj = Bobjclass.newInstance();
			addtempObj(Bobj);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**getメソッド
	 * 指定したオブジェクトネームを持つクラスのインスタンスを返します 
	 * */
	public BaseObject getObj(String tempname){
		try {
			return templeobj.get(objmap.get(tempname)).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
