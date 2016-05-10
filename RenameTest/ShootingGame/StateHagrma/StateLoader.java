package ShootingGame.StateHagrma;

import java.util.ArrayList;

import BaseSystem.TextEdit.TextReader;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri.HomingHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveHagrma;
import ObjectEngin.HagrmaSys.DrawableKarakuri.DrawableHagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrma;
import ShootingGame.BulletHagrma.BulletHagrma;
import ShootingGame.GameObject.CharactorObject;
import ShootingGame.GameObject.Myfighter;
/** ステートローダー
 * 属性：ローダー
 * テキストファイルからステート歯車を生成するクラス */
public class StateLoader {
	/** テキストファルを読み込み，保存する変数 */
	private TextReader TR;
/** コンストラクタ
 * @param S String ファイル名
 * @param statehagrma {@link StateHagrma} 初期化するステート歯車
 * @param object {@link BaseObject} 追加予定のオブジェクト
 * 初期化する歯車にテキストファイルを適用する*/
	public StateLoader(String S,StateHagrma statehagrma,BaseObject object) {
		System.out.println(S);
		TR = new TextReader();
		TR.readFile(S);
		ArrayList<String> buff = TR.getTextData();
		int nowstate = 0;
		for(String data : buff){
			String[] split = data.split(":");
			if(split.length != 2)
				continue;
			if(split[0].equalsIgnoreCase("state")){
				String[] param = split[1].split(",");
				nowstate = Integer.parseInt(param[0]);
				statehagrma.Init(param);
			}
			if(split[0].equals("HP")){
				if(object instanceof CharactorObject){
					((CharactorObject) object).setHP(Integer.parseInt(split[1]));
				}
			}
			if(split[0].equalsIgnoreCase("move")){
				statehagrma.addHagrma(nowstate, new MoveHagrma(split[1]));
			}
			if(split[0].equalsIgnoreCase("homing")){
				statehagrma.addHagrma(nowstate, new HomingHagrma(split[1]));
			}
			if(split[0].equalsIgnoreCase("outdelete")){
				if(split[1].equalsIgnoreCase("true"))
				statehagrma.addHagrma(nowstate, new OutRangeHagrma());
			}
			if(split[0].equalsIgnoreCase("bullet")){
				BulletHagrma BH = new BulletHagrma();
				String[] sp = split[1].split(",", 5);
				BH.init(sp[0], Long.parseLong(sp[1]),Double.parseDouble(sp[2]),Double.parseDouble(sp[3]), sp[4]);
				statehagrma.addHagrma(nowstate, BH);
			}
			if(split[0].equalsIgnoreCase("coll")){
				CollionHagrma col = new CollionHagrma();
				col.setTag(split[1]);
				statehagrma.addHagrma(nowstate, col);
			}
			if(split[0].equalsIgnoreCase("image")){
				DrawableHagrma draw = new DrawableHagrma();
				draw.Init(split[1]);
				statehagrma.addHagrma(nowstate, draw);
			}
		}
		
	}
	
}
