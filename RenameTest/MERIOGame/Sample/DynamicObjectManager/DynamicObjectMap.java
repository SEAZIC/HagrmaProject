package MERIOGame.Sample.DynamicObjectManager;

import MERIOGame.Sample.EnemySet.TestEnemy;
import MERIOGame.Sample.EnemySet.TestEnemy2;
import ObjectEngin.dynamicObj.DynaObjManager;

public class DynamicObjectMap extends DynaObjManager{

	public DynamicObjectMap() {
		super();
//		addtempObj(Bobj);
		addtempObj(new TestEnemy());
		addtempObj(new TestEnemy2());
	}
	
}
