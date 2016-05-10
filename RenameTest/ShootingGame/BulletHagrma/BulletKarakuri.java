package ShootingGame.BulletHagrma;

import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrma;

public class BulletKarakuri extends Karakuri{
	private long oldtime;
	private BulletObjectManager BOM;
	public BulletKarakuri() {
		super();
		oldtime = System.currentTimeMillis();
		this.BOM = new BulletObjectManager();
	}
	
	@Override
	public String call() throws Exception {
		long newtime = System.currentTimeMillis();
		long deltime = newtime-oldtime;
		ArrayList<LinkedList<Hagrma>> hagrmalsit = objM.getchiledHagrmas(BulletHagrma.class);
		if(hagrmalsit != null && hagrmalsit.size() > 0)
		for(LinkedList<Hagrma> linkedhagrmalist:hagrmalsit){
			for(Hagrma hag:linkedhagrmalist){
				if(hag instanceof BulletHagrma){
					((BulletHagrma) hag).addTime(deltime);
					if(((BulletHagrma) hag).isOver()){
						objM.addReqest(((BulletHagrma) hag).createBullet(BOM));
						((BulletHagrma) hag).resetTime();
					}
				}
			}
		}
		oldtime = newtime;
		hagrmalsit = null;
		return super.call();
	}
}
