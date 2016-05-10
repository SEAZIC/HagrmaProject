package ShootingGame.DynamicObjectManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import BaseSystem.FpsSetting.FpsManager;
import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.dynamicObj.DynaObjCreater;
import ObjectEngin.dynamicObj.DynaObjManager;
import ObjectEngin.dynamicObj.DynaObjReader;

public class ShootingEnemyCreater extends DynaObjCreater{



	public ShootingEnemyCreater() {

	}
	@Override
	public void setCreate(ObjectManager OM, DynaObjManager DOM, int MCchipsize, DynaObjReader DOR) {
		// TODO Auto-generated method stub
		super.setCreate(OM, DOM, MCchipsize, DOR);
	}
	@Override
	public void setCreate(ObjectManager OM, DynaObjManager DOM, MapClass MC, DynaObjReader DOR) {
		// TODO Auto-generated method stub
		super.setCreate(OM, DOM, MC, DOR);
	}
	@Override
	public void setCreate(ObjectManager OM, DynaObjManager DOM, MapClass MC, String S) {
		// TODO Auto-generated method stub
		super.setCreate(OM, DOM, MC, S);
	}
	@Override
	protected void setObjM(ObjectManager OM, DynaObjManager DOM, int MCchipsize) {
		if(DOR instanceof ShootingEnemyReader){

			LinkedList<BaseObject> addobj = DOR.getDynaObjects(DOM, MCchipsize);
			LinkedList<Integer> sectimes = ((ShootingEnemyReader) DOR).getSectimes();
			LinkedList<Integer> milsectimes = ((ShootingEnemyReader) DOR).getMilsectimes();
			ObjectManager ObjMana = OM;
			ShootingEnemyCreaterEngin SECE = new ShootingEnemyCreaterEngin();
			SECE.setInit(addobj, sectimes, milsectimes, ObjMana);
			ExecutorService ES = Executors.newSingleThreadExecutor();
			ArrayList<Callable<String>> calls = new ArrayList<Callable<String>>();
			calls.add(SECE);
			for(Callable<String> call : calls){
				ES.submit(call);
			}
		}else{
			super.setObjM(OM, DOM, MCchipsize);
		}
	}
}
