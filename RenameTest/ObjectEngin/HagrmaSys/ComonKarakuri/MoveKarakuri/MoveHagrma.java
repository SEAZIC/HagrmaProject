package ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri;

import BaseSystem.Animation.Animationtime;
import BaseSystem.MathPack.patternDif.Patterndif;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;

/**
 * <PRE>
 * 移動歯車
 * 属性：歯車
 * オブジェクトの移動を数式で制御する歯車です
 * 移動は数式で指定することができます
 * 数式は媒介変数を使用して数式を記述します
 * 媒介変数は t です
 * 括弧 ・・・ "(",")"で囲む
 * 累乗 ・・・"^"の後に指数を書く
 * sin,cos,tan,log ・・・　それぞれを書いた後に括弧で囲む
 * </PRE>
 *
 */
public class MoveHagrma extends Hagrma {

    private Patterndif x;
    private Patterndif y;
    private boolean loop;

    private int posx;
    private int posy;

    /**
     * <PRE>
     *  コンストラクタ
     *  すべてのパラメータを文字列型として入力するタイプです
     *  param 1 String x		xに対する数式
     *  param 2 String y		yに対する数式
     *  param 3 double t		/////////////
     *  param 4 double min		アニメーションタイムを使用しています
     *  param 5 double max		ループをtrueにすることで動き続けます
     *  param 6 boolean loop	/////////////
     * </PRE>
	 *
     */
    public MoveHagrma(String S) {
        String[] spr = S.split(",");
        Init(spr[0], spr[1], Double.parseDouble(spr[2]),
                Double.parseDouble(spr[3]), Double.parseDouble(spr[4]), Boolean.parseBoolean(spr[5]));
    }

    /**
     * <PRE>
     *  コンストラクタ
     *  すべてのパラメータを文字列型として入力するタイプです
     *  @param x String		xに対する数式
     * @param y String	yに対する数式
     * @param t double	ヴァーチャル差分タイム
     * @param min double	最小値
     * @param max double	最大値
     * @param loop boolean	ループ値
     * </PRE>
	 *
     */
    public MoveHagrma(String x, String y, double t, double min, double max, boolean loop) {
        super();
        Init(x, y, t, min, max, loop);

    }

    /**
     * <PRE>
     * コンストラクタ
     *  @param x 			String		xに対する数式
     * @param y String	yに対する数式
     * @param animation {@link Animationtime}	移動アニメーション
     * </PRE>
	 *
     */
    public MoveHagrma(String x, String y, Animationtime animation) {
        this.x = new Patterndif(x, 't');
        this.y = new Patterndif(y, 't');
        setAnimetion(animation);
    }

    /**
     * <PRE>
     * 初期化
     *  @param x String		xに対する数式
     * @param y String	yに対する数式
     * @param t double	ヴァーチャル差分タイム
     * @param min double	最小値
     * @param max double	最大値
     * @param loop boolean	ループ値
     * </PRE>
	 *
     */
    private void Init(String x, String y, double t, double min, double max, boolean loop) {
        this.x = new Patterndif(x, 't');
        this.y = new Patterndif(y, 't');
        setAnimetion(new Animationtime(t, min, max, loop));
    }

    @Override
    public void setParent(BaseObject parent) {
        // TODO Auto-generated method stub
        posx = parent.getBoundBox().x;
        posy = parent.getBoundBox().y;
        super.setParent(parent);
    }

    /**
     * <PRE>
     * 歯車の更新
     * カラクリで使用します
     * </PRE>
	 *
     */
    public void move() {
        if (getAnimation().isError() || getAnimation().isStop()) {
            return;
        }

        double dx = (x.Func(getAnimation().getTime()));
        double dy = (y.Func(getAnimation().getTime()));
//		System.out.println("moverun dx = "+dx+" dy = "+dy);
//		parent.setOldBox(parent.getBoundBox());
        if (!((int) dx == 0 && (int) dy == 0)) {
            parent.getBoundBox().setLocation(posx + (int) dx, posy + (int) dy);
        }
        getAnimation().refresh();
    }

}
