package ShootingGame.BulletHagrma;

import ObjectEngin.ComonObject.BaseObject;

public interface BulletHagrmaListner {

    public Class<? extends BaseObject> getParentClass();

    public Class<? extends BaseObject> getTargetClass();
}
