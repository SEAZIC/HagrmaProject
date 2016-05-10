package ShootingGame.DynamicObjectManager;

import ObjectEngin.dynamicObj.DynaObjManager;
import ShootingGame.EnemySet.Enemy;

public class ShootingEnemyManager extends DynaObjManager {

    public ShootingEnemyManager() {
        super();
        addtempObj(Enemy.class);
    }

}
