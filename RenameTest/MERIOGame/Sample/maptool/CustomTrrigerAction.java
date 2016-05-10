package MERIOGame.Sample.maptool;

import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.MapTool.TrrigersAction;

public class CustomTrrigerAction extends TrrigersAction{

	private String targetname;
	private boolean overwrite;

	@Override
	protected void fistAction() {
		super.fistAction();
		targetname = "";
		overwrite = false;
	}
	@Override
	protected void trrigerAction(String key, String value,int posx,int posy,CollionHagrma colhag) {
		if(value != null){
			if(key.equalsIgnoreCase("target")){
				targetname = value;
			}
			if(key.equalsIgnoreCase("overwrite")){
				overwrite = Boolean.parseBoolean(value);
				System.out.println("TRUE!!!!!!!!!!!!!!!"+overwrite);
			}

			if(targetname.equalsIgnoreCase(colhag.getTag())){
				if(key.equalsIgnoreCase("chenge")){

					String[] param = value.split(",");
					int chenge = getIntmap()[posx][posy];
					int sx = posx;
					int sy = posy;
					if(param.length > 0){
						chenge = Integer.parseInt(param[0]);
						if(param.length > 2){
							sx += Integer.parseInt(param[1]);
							sy += Integer.parseInt(param[2]);
						}
					}
					if(getMoMID().containsKey(chenge)){
						if(sx > -1 && sy > -1 && sx < getIntmap().length && sy < getIntmap()[0].length){
							if(!(!(overwrite) && ((!(sx==posx && sy==posy))&&(getMC().getMap(sx, sy)!=null?getMC().getMap(sx, sy).isBlockF():true)))){
								getIntmap()[sx][sy] = chenge;
							}else{
								System.out.println("overwrite = "+overwrite);
							}
						}
					}
				}
			}
		}
	}
}
