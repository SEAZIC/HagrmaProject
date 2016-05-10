package ObjectEngin.MapTool;

import java.util.HashMap;

import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;

public class TrrigersAction {
	private MapClass MC;
	public int[][] getIntmap() {
		return MC.getIntmaps();
	}
	public HashMap<Integer, Integer> getMoMID() {
		return MC.getMapofmapID();
	}

	public Mapchip[] getMapchip() {
		return MC.getMaps();
	}

	public void trrigerActions(String trrigers,int posx,int posy,CollionHagrma colhag){
		fistAction();
		
		String[] trriger = trrigers.split("}");
		for(String S : trriger){
			String[] set = S.split(":");
			String S1 = null;
			String S2 = null;
			if(set.length > 0)
				S1 = set[0];
			if(set.length > 1)
				S2 = set[1];
			trrigerAction(S1, S2,posx,posy,colhag);
		}
		
		lastAction();
		
	}

	protected void fistAction(){
		
	}
	protected void trrigerAction(String key,String value,int posx, int posy, CollionHagrma colhag){
		
	}
	protected void lastAction(){
		
	} 
	public MapClass getMC() {
		return MC;
	}

	public void setMC(MapClass mC) {
		MC = mC;
	}


}
