package ObjectEngin;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import BaseSystem.maintools.MessagePack;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;
import ObjectEngin.HagrmaSys.CameraKarakuri.CameraKarakuri;
import ObjectEngin.HagrmaSys.DrawableKarakuri.DrawableHagrma;
import ObjectEngin.HagrmaSys.DrawableKarakuri.DrawableKarakuri;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeKarakuri;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**<PRE>
 * オブジェクトマネージャー
 * オブジェクト {@link BaseObject}と，そのオブジェクト {@link BaseObject}が保持している歯車 {@link Hagrma}を管理し，
 * カラクリ {@link Karakuri}を使用して歯車とオブジェクト {@link BaseObject}を扱う大元のマネージャークラスです
 *  カラクリ {@link Karakuri}を入れて動かす状態のオブジェクトマネージャーをエンジンと言います
 *  </PRE> */
public class ObjectManager {

	private ArrayList<BaseObject> ComonObjects;

	private ArrayList<Karakuri> karakuris;
	private ArrayList<Integer> karakuripriority;

	private LinkedList<LinkedList<Hagrma>> Hagrmas;
	private LinkedList<Class<?>> hagrmaClassMap;

	private ArrayList<BaseObject> addObj;
	private ArrayList<BaseObject> destroyObj;

	private ArrayList<Hagrma> addHagrma;
	private ArrayList<Hagrma> removeHagrma;

	private ThreadPoolExecutor ES;

	private CameraKarakuri camera;
	private DrawableKarakuri drawer;

	private Rectangle OutRange;
	private boolean out;

	/**<PRE>
	 * コンストラクタ
	 *  パラメータの初期化
	 *  </PRE> */
	public ObjectManager() {
		Hagrmas = new LinkedList<>();
		ComonObjects = new ArrayList<>();
		karakuris = new ArrayList<>();
		karakuripriority = new ArrayList<>();
		addObj = new ArrayList<>();
		destroyObj = new ArrayList<>();
		hagrmaClassMap = new LinkedList<>();

		addHagrma = new ArrayList<>();
		removeHagrma = new ArrayList<>();

		ES = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		addKarakuri(camera = new CameraKarakuri());
		addKarakuri(drawer = new DrawableKarakuri());
		addKarakuri(new OutRangeKarakuri());
	}

	/**<PRE>
	 * オブジェクト追加
	 * オブジェクト {@link BaseObject}をオブジェクトマネージャー {@link ObjectManager}に追加します
	 *  その際，歯車 {@link Hagrma}は自動的に取り出され，まとめて管理されます
	 *  @param obj {@link BaseObject} 追加するオブジェクト
	 *  </PRE> */
	public void addObjct(BaseObject obj) {
//		System.out.println(obj.getName());
		if(!ComonObjects.contains(obj)){			//2重登録防止
			ComonObjects.add(obj);
			if(!obj.getChildobj().isEmpty())
				for(BaseObject child:obj.getChildobj()){
					addObjct(child);
				}
		}else{
			return;
		}
		obj.addMane(this);
		if(!obj.getHagrmas().isEmpty())
			for(Hagrma hag : obj.getHagrmas()){
				if(!hagrmaClassMap.contains(hag.getClass())){
					hagrmaClassMap.add(hag.getClass());
					System.out.println("addhagrmaClass "+hag.getClass().getName());
					Hagrmas.add(new LinkedList<Hagrma>());
				}
				Hagrmas.get(hagrmaIndex(hag)).add(hag);
				//				System.out.println("name:"+obj.getName()+" addhagrmaClass "+hag.getClass().getName());
			}
	}

	/**<PRE> 
	 * カラクリ追加
	 * カラクリを追加します
	 * @param karakuri {@link Karakuri} 追加するカラクリ
	 * </PRE> */
	public void addKarakuri(Karakuri karakuri){

		karakuris.add(karakuri);
		System.out.println(""+karakuri.getClass()+" p "+karakuri.getPriority());
		boolean match = false;
		if(!karakuripriority.isEmpty()){
			for(int cnt = 0;cnt < karakuripriority.size();cnt++){
				if(karakuripriority.get(cnt).intValue() == karakuri.getPriority()){
					match = true;
					break;
				}
			}
		}
		if(!match){
			karakuripriority.add(karakuri.getPriority());
			Collections.sort(karakuripriority);
			Collections.reverse(karakuripriority);
		}
		karakuri.setObjM(this);

	}

	/**<PRE> 
	 * 歯車追加
	 * 歯車を追加します
	 * 基本的にはオブジェクトから追加してください
	 * このメソッドは触ることはないと思います
	 * </PRE> */
	public void addHagrma(Hagrma hag){
		if(!hagrmaClassMap.contains(hag.getClass())){
			hagrmaClassMap.add(hag.getClass());
			//			System.out.println("addhagrmaClass "+hag.getClass().getName());
			Hagrmas.add(new LinkedList<Hagrma>());
		}
		Hagrmas.get(hagrmaIndex(hag)).add(hag);
	}
	/**<PRE> 
	 * 実行
	 * カラクリの処理，オブジェクトの管理はここで行われます．
	 * このメソッドは毎フレーム呼ばれるところに書いてください
	 * </PRE> */
	public void running(){

		//		for(Karakuri k : karakuris){
		//			k.run();
		//		}
		List<Future<String>> futures = null;
//		System.out.println("------------------");
		try {
			//					System.out.println("priority "+priority+" start");
			futures = ES.invokeAll(ComonObjects);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!futures.isEmpty())
			for(Future<String> fu : futures){
				try {
					String  S = fu.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		for(int priority:karakuripriority){
			ArrayList<Karakuri> karakuritask = new ArrayList<Karakuri>();
			for(Karakuri k :karakuris){
				if(k.getPriority() == priority){
					karakuritask.add(k);
				}
			}
			try {
				futures = ES.invokeAll(karakuritask);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!futures.isEmpty())
				for(Future<String> fu : futures){
					try {
						String  S = fu.get();
//						System.out.println(priority+" "+S+" ");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//				System.out.println("priority "+priority+" end");
		}
		//				System.out.println("priority "+priority+" end");
//                System.out.println("実行中タスク　"+ES.getActiveCount()+"　実行完了タスク　"+ES.getCompletedTaskCount());
                if(ES.getCompletedTaskCount() > 1024){
                try {
                    ES.shutdown();
                    if(!ES.awaitTermination(500, TimeUnit.NANOSECONDS))
                        ES.shutdownNow();
                    ES = null;
                    ES =  (ThreadPoolExecutor) Executors.newCachedThreadPool();
                } catch (InterruptedException ex) {
                        ES.shutdownNow();
                    ES = null;
                    ES =  (ThreadPoolExecutor) Executors.newCachedThreadPool();
                    System.out.println("ObjectEngin.ObjectManager.running()----ERROR");
                    Logger.getLogger(ObjectManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
		if(!destroyObj.isEmpty()){
			//			System.out.println("destroyed!");
			for(int cnt = destroyObj.size()-1;cnt > -1 ;cnt--){
				BaseObject target = destroyObj.get(cnt);
				for(int cnt2 = 0;cnt2 < target.getHagrmas().size();cnt2++){
					removeHagrmaReqest(target.getHagrmas().get(cnt2));
				}
				ComonObjects.remove(target);
			}
		}
		if(!addObj.isEmpty()){
			//			System.out.println("addObjed!");
			for(int cnt = addObj.size()-1;cnt > -1 ;cnt--){
				BaseObject target = addObj.get(cnt);
				addObjct(target);
			}
			addObj.clear();
		}
		if(!addHagrma.isEmpty()){
			//			System.out.println("addedHagrma!");
			for(Hagrma H:addHagrma){
				addHagrma(H);
			}
			addHagrma.clear();
		}
		if(!removeHagrma.isEmpty()){
			//			System.out.println("removedHagrma!");
			for(Hagrma H:removeHagrma){
				removeHagrma(H);
				H = null;
			}
			removeHagrma.clear();
		}
		for(BaseObject target:destroyObj){
			target.dispose();
			target = null;
		}
		if(!destroyObj.isEmpty())
			destroyObj.clear();
	}
	/**<PRE>
	 * 削除オブジェクト登録
	 * オブジェクトをオブジェクトマネージャーから削除します
	 * 実際のオブジェクトを削除するタイミングは次の実行の最後に行われます
	 * @param BO {@link BaseObject} 削除予定のオブジェクト
	 * </PRE> */
	synchronized public void addDestroyObj(BaseObject BO){
		destroyObj.add(BO);
	}
	private int hagrmaIndex(Hagrma hagrma){

		return hagrmaIndex(hagrma.getClass());
	}
	private int hagrmaIndex(Class<?> hagrmaclass){

		for(int cnt = 0;cnt < hagrmaClassMap.size();cnt++){
			if(hagrmaClassMap.get(cnt) == hagrmaclass){
				return cnt;
			}
		}

		return -1;
	}

	/**<PRE>
	 * 歯車取得
	 * 	特定クラスの歯車を取得します
	 * クラスが完全一致のもののみを返します
	 * @param hagrmaclass Class<?extends Hagrma> 取得したい歯車のクラス
	 * @return {@link LinkedList<Hagrma>} 取得したい歯車のリスト
	 *  </PRE> */
	synchronized public LinkedList<Hagrma> getHagrmas(Class<? extends Hagrma> hagrmaclass){
		int index = hagrmaIndex(hagrmaclass);
		if(index >= 0)
			return Hagrmas.get(hagrmaIndex(hagrmaclass));
		else
			return null;
	}

	/**<PRE> 
	 * 歯車取得
	 * 	特定クラスの歯車を取得します
	 * 指定したクラスを継承しているものをクラスごとにリストにしたリストを返します
	 * @param hagrmaclass Class<?extends Hagrma> 取得したい歯車のクラス
	 * @return {@link ArrayList<LinkedList<Hagrma>>} 取得したい歯車のリストのリスト
	 * </PRE> */
	synchronized public ArrayList<LinkedList<Hagrma>> getchiledHagrmas(Class<? extends Hagrma> hagrmaclass){
		ArrayList<LinkedList<Hagrma>> chiledHagrmas = new ArrayList<LinkedList<Hagrma>>();
		for(int cnt = 0;cnt < hagrmaClassMap.size();cnt++){

			if(hagrmaclass.isAssignableFrom(hagrmaClassMap.get(cnt))){
				//				System.out.println("hitting!!!!!!");
				chiledHagrmas.add(Hagrmas.get(cnt));
			}
		}
		return chiledHagrmas;
	}

	/**<PRE> 
	 * 全オブジェクト取得
	 * オブジェクトを全て取得します
	 * @return {@link ArrayList<BaseObject>} 全オブジェクト
	 * </PRE> */
	synchronized public ArrayList<BaseObject> getComonObjects() {
		return ComonObjects;
	}

	/**<PRE>
	 * 描画
	 * オブジェクトごとに描画します
	 *  </PRE> */
	public void show(Graphics g){
		camera.setClip(g);
		//		System.out.println(g.getClipBounds());
		for(BaseObject bobj : ComonObjects){
//			System.out.println(bobj.getName());
			bobj.show(g);
		}
		drawer.show(g);
	}

	/**<PRE>
	 * 歯車削除
	 * 渡された歯車をオブジェクトマネージャーから排除します
	 * 基本的には触りません
	 *  </PRE> */
	public void removeHagrma(Hagrma hag){
		int Index = hagrmaIndex(hag);
		if(Index < 0){
			return ;
		}
		Hagrmas.get(Index).remove(hag);
		if(Hagrmas.get(Index).isEmpty()){
			Hagrmas.remove(Index);
			hagrmaClassMap.remove(Index);
		}
	}

	/**<PRE> 
	 * オブジェクトマネージャーシャットダウン
	 * 安全に終了するための処理
	 * </PRE> */
	public void shutdown(){
		ES.shutdown();
		System.out.println("オブジェクトマネージャーシャットダウン");
	}

	/**<PRE>
	 * 歯車の追加
	 * 歯車を追加します
	 * @param hagrma {@link Hagrma} 追加する歯車
	 *  </PRE> */
	synchronized public void addHagrmaReqest(Hagrma hagrma) {
		if(!addHagrma.contains(hagrma))
			addHagrma.add(hagrma);
	}

	/**<PRE>
	 * 歯車の削除
	 * 歯車を削除します
	 * @param hagrma {@link Hagrma} 削除する歯車
	 *  </PRE> */
	synchronized public void removeHagrmaReqest(Hagrma hagrma) {
		if(!removeHagrma.contains(hagrma))
			removeHagrma.add(hagrma);
	}

	/**<PRE> 
	 * オブジェクトサーチ
	 * 指定した名前のオブジェクトを探します
	 * @param S String 探すオブジェクトの名前
	 * @return {@link BaseObject} 探してきたオブジェクト，何もなければ多分null
	 * </PRE> */
	public BaseObject searchObject(String S){
		System.out.println("Search...");
		for(BaseObject obj : ComonObjects){
			if(obj.getName().equalsIgnoreCase(S))
				return obj;
		}
		return null;
	}
	/**<PRE> 
	 * オブジェクト追加
	 * オブジェクトをオブジェクトマネージャー起動中に安全に追加します
	 * @param addobj {@link BaseObject} 追加するオブジェクト
	 * </PRE> */
	synchronized public void addReqest(BaseObject addobj){
		addObj.add(addobj);
	}

	/**<PRE> 
	 * 設定範囲
	 * @return {@link Rectangle} 設定した範囲
	 * </PRE> */
	public Rectangle getOutRange() {
		return OutRange;
	}

	/**<PRE> 
	 * 範囲設定
	 * @param outRange {@link Rectangle} 画面の大きさ
	 * </PRE> */
	public void setOutRange(Rectangle outRange) {
		OutRange = outRange;
		setOut(true);
	}

	public boolean isOut() {
		return out;
	}

	private void setOut(boolean out) {
		this.out = out;
	}
}
