package ShootingGame.GameObject.Bullet;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrmaListner;

public class BulletObject extends BaseObject implements OutRangeHagrmaListner {

    protected Class<? extends BaseObject> target;
    protected Class<? extends BaseObject> parentObject;

    public BulletObject() {
        super();
        this.name = "bulletBase";
    }

    public void init(String S) {

    }

    public void setTarget(Class<? extends BaseObject> parent, Class<? extends BaseObject> target) {
        this.target = target;
        this.parentObject = parent;
    }

    @Override
    public void outAction() {
        // TODO Auto-generated method stub
        this.destroy();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        target = null;
        parentObject = null;
    }
}
