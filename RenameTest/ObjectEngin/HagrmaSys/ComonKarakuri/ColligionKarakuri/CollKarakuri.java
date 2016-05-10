package ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/**
 * <PRE>
 *  当たり判定カラクリ
 * 属性：カラクリ
 * プライオリティ：０
 * 当たり判定歯車{@link CollionHagrma}を処理するためのカラクリです
 * 他の当たり判定歯車{@link CollionHagrma}と当たり判定を行うため，当たり判定タグマップを作成する必要があります
 * setTagMapメソッドを使用して当たり判定タグマップを登録してください
 * </PRE>
 *  */
public class CollKarakuri extends Karakuri{
	private boolean[][] tagMap;
	private String[] tagName;
	private HashMap<String,String> tagnamelist;
	private HashMap<String,HashMap<String,Integer>> tagMaps;
	private HashMap<String,ArrayList<String>> tagMapAll;
	private boolean init;
	/**コンストラクタ */
	public CollKarakuri() {
		tagMaps = new HashMap<String,HashMap<String,Integer>>();
		tagMapAll = new HashMap<String,ArrayList<String>>();
		tagnamelist = new HashMap<String,String>();
		init = false;
	}

	/**
	 * <PRE>
	 * Callメソッド 
	 * 処理する部分です
	 * 基本的に触ることはありません
	 * </PRE>
	 * 　*/
	@Override
	public String call() throws Exception {
		ArrayList<LinkedList<Hagrma>> hags = objM.getchiledHagrmas(CollionHagrma.class);
		HashMap<String, ArrayList<CollionHagrma>> maps = new HashMap<String ,ArrayList<CollionHagrma>>();
		ArrayList<CollionHagrma> colMap = new ArrayList<CollionHagrma>();
		for(LinkedList<Hagrma> hag : hags){
			for(Hagrma hagrma : hag){

//				hagrma.getParent().boxrefresh();
				if(!init)
					continue;
				CollionHagrma collionHagrma = (CollionHagrma)hagrma;
				colMap.add(collionHagrma);
				String tag = collionHagrma.getTag();
				if(!tagnamelist.containsKey(tag))
					tag = "";
				if(!maps.containsKey(tag)){
					maps.put(tag, new ArrayList<CollionHagrma>());
				}
				maps.get(tag).add(collionHagrma);
				if(!tagMapAll.containsKey(tag))
					continue;
				for(String targettag:tagMapAll.get(tag)){
					if(maps.containsKey(targettag))
						for(CollionHagrma col2 :maps.get(targettag)){
							if(col2.isHit(collionHagrma.getRect())){
								int setflag = tagMaps.get(tag).get(targettag);
								if((setflag & 1) == 1 ){
									if(col2.getParent() instanceof ColligionHitInf){
										ColligionHitInf ch = (ColligionHitInf)(col2.getParent());
										ch.hitAction(collionHagrma.getParent(),collionHagrma);
									}
								}
								if((setflag & 2) == 2 ){
									if(collionHagrma.getParent() instanceof ColligionHitInf){
										ColligionHitInf ch = (ColligionHitInf)(collionHagrma.getParent());
										ch.hitAction(col2.getParent(),col2);
									}
								}
							}
						}
				}


			}
		}
		hags = null;
		return super.call();
	}
	/**
	 * <PRE>
	 * setTagMapメソッド
	 * 当たり判定用のタグマップを登録します
	 * @param tagName String[] タグの名前セット
	 * @param tagMap boolean[][] タグの当たり判定マップ
	 * </PRE>
	 *  */
	public void setTagMap(String[] tagName,boolean[][] tagMap){
		init = true;
		int[][] map = new int[tagName.length][tagName.length];
		for(int cnt = 0;cnt < tagName.length;cnt++){
			tagnamelist.put(tagName[cnt],tagName[cnt]);
			for(int cnt2 = 0;cnt2 < tagName.length;cnt2++){
				map[cnt][cnt2] = 0;
			}
		}
		for(int cnt = 0;cnt < tagName.length;cnt++){
			for(int cnt2 = 0;cnt2 < tagName.length;cnt2++){
				if(tagMap[cnt][cnt2]){
					map[cnt][cnt2] += 1;
					map[cnt2][cnt] += 2;
				}
			}
		}
		for(int cnt = 0;cnt < tagName.length;cnt++){
			for(int cnt2 = 0;cnt2 < tagName.length;cnt2++){
				if(map[cnt][cnt2] != 0){
					if(!tagMaps.containsKey(tagName[cnt2]))
						tagMaps.put(tagName[cnt2], new HashMap<String,Integer>());
					tagMaps.get(tagName[cnt2]).put(tagName[cnt],map[cnt][cnt2]);
					if(!tagMapAll.containsKey(tagName[cnt2]))
						tagMapAll.put(tagName[cnt2], new ArrayList<String>());
					tagMapAll.get(tagName[cnt2]).add(tagName[cnt]);
				}

			}
		}
	}
}
