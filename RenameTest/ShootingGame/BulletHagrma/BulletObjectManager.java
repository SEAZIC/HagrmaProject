package ShootingGame.BulletHagrma;

import ObjectEngin.dynamicObj.DynaObjManager;
import ShootingGame.GameObject.Bullet.BulletStraight;

public class BulletObjectManager extends DynaObjManager {

    public BulletObjectManager() {
        super();
        addtempObj(BulletStraight.class);
    }

}
