package ObjectEngin.MapTool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.CameraKarakuri.RangeHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.MapKarakuri.MapHagrma;

/**<PRE>
 * マップクラス
 * マップ情報をint[][]で保存し，表示の時にチップごとにテンプレートと照らし合わせて表示する
 *  </PRE> */
public class MapClass  extends BaseObject{
	private Mapchip[] Maps;
	private HashMap<Integer, Integer> mapofmapID;
	private MapReader MR;
	private int[][] intmaps;
	private int posx;
	private int posy;
	private int chipsize;
	private TrrigersAction[] trrigers;

	public MapClass(String filename) {
		this(filename,new TrrigersAction[]{new TrrigersAction()});
	}
	public MapClass(String filename,TrrigersAction trrigersaction){
		this(filename,new TrrigersAction[]{trrigersaction});
	}
	public MapClass(String filename,TrrigersAction[] trrigersactions){
		MR = new MapReader();
		System.out.println("asdfasdfas "+filename);
		intmaps = MR.readIntMap(filename);
		mapofmapID = new HashMap<Integer,Integer>();
//		trrigers = new TrrigersAction[1];
		trrigers = trrigersactions;
		for(TrrigersAction trriger:trrigers){
			trriger.setMC(this);
		}
		posx = 0;
		posy = 0;
		chipsize = 36;
		System.out.println("map--"+MR.getMapchip());
		ArrayList<String> dir = new ArrayList<String>();
		dir = MR.getMapchip();
		ArrayList<Mapchip> chips = new ArrayList<Mapchip>();
		for(String mapdir:dir){
			String jarPath = System.getProperty("java.class.path");
			String dirPath = jarPath.substring(0, jarPath.lastIndexOf(File.separator)+1);
			System.out.println("test -> "+dirPath);
			System.out.println(System.getProperty("user.dir")+File.separator+mapdir);
			URL url = getClass().getClassLoader().getResource(mapdir);
			System.out.println("protcol "+url.getProtocol());

			//		try {
			//			System.out.println(url.getPath());
			//			String[] split = url.getPath().split(":");
			//			if(split.length < 2){
			//			derect = new File(url.toURI().getRawPath());
			//			}else{
			//				System.out.println(split[1]);
			//				derect = new File(split[1]);
			//			}
			//		} catch (URISyntaxException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			if(url.getProtocol().equalsIgnoreCase("file")){
				File derect = null;
				derect = new File(url.getFile());
				int cnt = 0;
				for(String file:derect.list()){
					System.out.println(""+file);
					if(file.matches("(^[a-zA-Z]).*\\.txt$")){
						chips.add(new Mapchip(mapdir+File.separator+file));
						mapofmapID.put(chips.get(chips.size()-1).getMapID(),cnt);
						System.out.println("IDs"+chips.get(chips.size()-1).getMapID());
						cnt++;
					}
				}
			}else if(url.getProtocol().equalsIgnoreCase("jar")){
				try {
					JarURLConnection jarUrlConnection = (JarURLConnection)url.openConnection();

					JarFile jarFile = null;
					jarFile = jarUrlConnection.getJarFile();
					Enumeration<JarEntry> jarEnum = jarFile.entries();
					try{
						int cnt = 0;
						while (jarEnum.hasMoreElements()) {
							JarEntry jarEntry = jarEnum.nextElement();
							System.out.println(jarEntry.getName());
							if(jarEntry.getName().startsWith(mapdir)&&jarEntry.getName().matches(".*\\.txt$")){
								chips.add(new Mapchip(jarEntry.getName()));
								mapofmapID.put(chips.get(chips.size()-1).getMapID(),cnt);
								System.out.println("IDs"+chips.get(chips.size()-1).getMapID());
								cnt++;
							}
						}
					} finally {
						if (jarFile != null) {
							jarFile.close();
						}
						//					System.exit(-1);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(url.getProtocol().equalsIgnoreCase("rsrc")){

			}
		}
		BoundBox.setBounds(posx, posy, chipsize*intmaps.length, chipsize*intmaps[0].length);
		Maps =chips.toArray(new Mapchip[]{});
		addHagrma(new MapHagrma());
		addHagrma(new RangeHagrma());
	}
	public void chengeChipsize(int size){
		chipsize = size;
		BoundBox.setBounds(posx, posy, chipsize*intmaps.length, chipsize*intmaps[0].length);

	}
	public int[][] getIntmaps() {
		return intmaps;
	}
	public Mapchip[] getMaps() {
		return Maps;
	}
	public Mapchip getMap(int posx,int posy){
		if(posx < 0 || posy < 0 || posx> intmaps.length-1 || posy > intmaps[0].length-1){
			return null;
		}
		if(mapofmapID.containsKey(intmaps[posx][posy])){
			return Maps[mapofmapID.get(intmaps[posx][posy])];
		}else{
			return null;
		}

	}
	public int getChipsize() {
		return chipsize;
	}
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setTrriger(int posx,int posy,String trriger,CollionHagrma hagrma){
		String S= Maps[mapofmapID.get(intmaps[posx][posy])].getTrriger(trriger);
		if(S != null)
			for(TrrigersAction TA:trrigers){
				TA.trrigerActions(S, posx, posy,hagrma);
			}
	}
	public HashMap<Integer, Integer> getMapofmapID() {
		return mapofmapID;
	}
	@Override
	public void show(Graphics g) {
		Rectangle clip = g.getClipBounds();
		int minx = clip.x/chipsize;
		int miny = clip.y/chipsize;
		int maxx = (clip.width/chipsize)+1;
		int maxy = (clip.height/chipsize)+1;
		minx = minx>=0?minx:0;
		miny = miny>=0?miny:0;
		maxx = minx+maxx+1<=intmaps.length?minx+maxx+1:intmaps.length;
		maxy = miny+maxy+1<=intmaps[0].length?miny+maxy+1:intmaps[0].length;
		for(int cntx = minx;cntx < maxx;cntx++){
			for(int cnty = miny;cnty < maxy;cnty++){
				if(mapofmapID.containsKey(intmaps[cntx][cnty])){
					g.drawImage(Maps[mapofmapID.get(intmaps[cntx][cnty])].getImage(), posx+cntx*chipsize, posy+cnty*chipsize,chipsize,chipsize, null);
					//					System.out.println(mapofmapID.get(intmaps[cntx][cnty]));
					g.drawRect( posx+cntx*chipsize, posy+cnty*chipsize, chipsize,chipsize);
				}
			}
		}
	}
}
