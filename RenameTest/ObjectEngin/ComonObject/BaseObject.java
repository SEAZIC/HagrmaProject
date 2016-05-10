package ObjectEngin.ComonObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

import ObjectEngin.ObjectManager;
import ObjectEngin.HagrmaSys.Hagrma;

/**
 * <PRE>
 * ベースオブジェクトクラス
 * オブジェクトマネージャーで扱う，もっともベースとなるオブジェクトクラスです
 * オブジェクトマネージャーに登録する際はこのクラスを使うか，このクラスを継承して使用してください
 * </PRE>
 */
public class BaseObject implements Callable<String> {

    /**
     * オブジェクトが保持する歯車
     */
//	protected ArrayList<Hagrma> Hagrmas;
    protected LinkedHashMap<String, Hagrma> Hagrmas;
    /**
     * 子のオブジェクト（未実装）
     */
    protected ArrayList<BaseObject> childobj;

    private HashMap<String, BaseObject> childName;

    /**
     * 親のオブジェクト（未実装）
     */
    protected BaseObject parentobj;

    /**
     * １フレーム前のボックス
     */
    protected Rectangle oldBox;
    /**
     * 現在のボックス
     */
    protected Rectangle BoundBox;

    /**
     * オブジェクトの名前
     */
    protected String name;

    protected double divx;
    protected double divy;

    private double rotate;

    private ObjectManager objM;

    /**
     * <PRE>
     *  コンストラクタ
     * 初期化のみ
     * </PRE>
     */
    public BaseObject() {
//		Hagrmas = new ArrayList<Hagrma>();
        Hagrmas = new LinkedHashMap<String, Hagrma>();
        childobj = new ArrayList<BaseObject>();
        childName = new HashMap<String, BaseObject>();

        parentobj = null;

        BoundBox = new Rectangle(0, 0, 0, 0);
        oldBox = new Rectangle(0, 0, 0, 0);
        name = "";
    }

    /**
     * <PRE>
     *  歯車の取得
     *  基本的にはエンジンで使用するもの
     *  @return ArrayList<Hagrma> 歯車のリスト
     * </PRE>
     */
    public ArrayList<Hagrma> getHagrmas() {
        return new ArrayList<Hagrma>(Hagrmas.values());
    }

    /**
     * <PRE>
     *  歯車の追加
     * エンジンに登録する前，登録した後問わず，エンジンの歯車リストに登録してくれる
     * そのため，エンジンにオブジェクトを追加した後に歯車を追加するときも気にせず使用して良い
     * @param hagrma {@link Hagrma} 追加予定の歯車オブジェクト
     * </PRE>
     */
    public void addHagrma(Hagrma hagrma) {
        if (!Hagrmas.containsKey(hagrma.getName())) {
            Hagrmas.put(hagrma.getName(), hagrma);
        } else {
            String[] s = hagrma.getName().split("_");
            int num;
            if (s.length > 1) {
                num = Integer.parseInt(s[1]) + 1;
            } else {
                num = 0;
            }
            hagrma.setName(s[0] + "_" + num);
            addHagrma(hagrma);
            return;
        }
        if (this.objM != null) {
            this.objM.addHagrmaReqest(hagrma);
        }
        hagrma.setParent(this);
    }

    /**
     * <PRE>
     *  歯車の削除
     * エンジンに登録する前，登録した後問わず，エンジンの歯車リストから削除してくれる
     * そのため，エンジンにオブジェクトを追加した後に歯車を削除するときも気にせず使用して良い
     * @param Index 消す予定の歯車のインデックス
     * </PRE>
     */
    public void removeHagrma(String Index) {
        if (this.objM != null) {
            this.objM.removeHagrmaReqest(Hagrmas.remove(Index));
        } else {
            Hagrmas.remove(Index);
        }
    }

    /**
     * <PRE>
     *  歯車の削除
     * エンジンに登録する前，登録した後問わず，エンジンの歯車リストから削除してくれる
     * そのため，エンジンにオブジェクトを追加した後に歯車を削除するときも気にせず使用して良い
     * @param hagrma {@link Hagrma} 消す予定の歯車オブジェクト
     * </PRE>
     */
    public void removeHagrma(Hagrma hagrma) {
//		int index = Hagrmas.indexOf(hagrma);
//		if(index < 0)
//			return;
//		Hagrma key = Hagrmas.get(hagrma.getName());
        removeHagrma(hagrma.getName());
    }

    /**
     * <PRE>
     * エンジン登録
     * 継承先でこれを触ることは想定されていません
     * 継承して使用するには自分でエンジンやオブジェクトの仕組みを理解してからにしてください
     * </PRE>
     */
    public void addMane(ObjectManager objM) {
        this.objM = objM;
    }

    /**
     * <PRE>
     * このオブジェクトの削除
     * エンジンにこのオブジェクトを削除するように依頼します
     * </PRE>
     */
    public void destroy() {
        this.objM.addDestroyObj(this);
    }

    public void dispose() {
        Hagrmas = null;
        childobj = null;
        parentobj = null;
        oldBox = null;
        BoundBox = null;

        name = null;

        objM = null;
    }

    /**
     * <PRE>
     * 描画メソッド
     * これをオーバライドすることで描画処理をオブジェクトごとに書くことができます
     * </PRE>
     */
    public void show(Graphics g) {
//		g.drawString(""+this.getClass(), (int)BoundBox.getX()+0,(int)BoundBox.getY()-5);
    }

    /**
     * 現在のバウンディングボックス取得 
	 *
     */
    public Rectangle getBoundBox() {
        return BoundBox;
    }

    /**
     * １フレーム前のボックスの取得
	 *
     */
    public Rectangle getOldBox() {
        return oldBox;
    }

    /**
     * <
     * PRE>
     * 前のボックス設定
     * オブジェクト生成時などに初期値を与えるために使います
     * そのうちプライヴェートにする予定なので使わないでください
     * </PRE>
     */
    public void setOldBox(Rectangle oldBox) {
        this.oldBox.setBounds(oldBox);
    }

    /**
     * <
     * PRE>
     * バウンディングボックスの設定
     * オブジェクト生成時などに初期値を与えるために使います
     * </PRE>
     */
    public void setBoundBox(Rectangle boundBox) {
        BoundBox = boundBox;
    }

    /**
     * <PRE>
     * オブジェクトのバウンディングボックスの移動
     * </PRE>
     */
    synchronized public void moveBox(int x, int y) {
        oldBox.setBounds(BoundBox);
        BoundBox.translate(x, y);
    }

    /**
     * <PRE>
     * オブジェクトのバウンディングボックスの位置変更
     * </PRE>
     */
    synchronized public void setLocate(int x, int y) {
        oldBox.setBounds(BoundBox);
        BoundBox.setLocation(x, y);
    }

    /**
     * <
     * PRE>
     * 現在のバウンディングボックスを戻す
     * 現在のバウンディングボックスの位置を前のボックスに戻します
     * </PRE>
     */
    public void reverse() {
        this.BoundBox.setBounds(oldBox);
    }

    /**
     * <PRE>
     * 前のボックス更新
     * 前のボックスに現在のボックスを適用します
     * </PRE>
     */
    public void boxrefresh() {
        divx = BoundBox.getCenterX() - oldBox.getCenterX();
        divy = BoundBox.getCenterY() - oldBox.getCenterY();

        this.oldBox.setBounds(BoundBox);
    }

    /**
     * <
     * PRE>
     * オブジェクトネーム取得
     * オブジェクトの名前を取得します
     * </PRE>
     */
    public String getName() {
        return name;
    }

    /**
     * 更新された際のx方向のスピードを返します
     */
    public double getDivx() {
        return divx;
    }

    /**
     * 更新された際のy方向のスピードを返します
     */
    public double getDivy() {
        return divy;
    }

    public double getSpeed() {
        return Math.sqrt((divx * divx) + (divy * divy));
    }

    @Override
    public String call() throws Exception {
        return upDate();
    }

    synchronized protected String upDate() {
        boxrefresh();
        return null;
    }

    public void addParent(BaseObject parent) {
        this.parentobj = parent;
    }

    public void addChiled(BaseObject child) {
        this.childobj.add(child);
        child.addParent(this);
        if (!childName.containsKey(child.getName())) {
            childName.put(child.getName(), child);
        }
        if (objM != null) {
            objM.addObjct(child);
        }
    }

    public ArrayList<BaseObject> getChildobj() {
        return childobj;
    }

    public BaseObject getParentobj() {
        return parentobj;
    }

    public BaseObject getChildIndex(String name) {
        return childName.get(name);
    }
}
