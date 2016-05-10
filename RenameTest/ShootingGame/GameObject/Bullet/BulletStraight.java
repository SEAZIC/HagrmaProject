package ShootingGame.GameObject.Bullet;

import java.awt.Color;
import java.awt.Graphics;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.ColligionHitInf;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri.HomingHagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrma;
import ShootingGame.GameObject.CharactorObject;

public class BulletStraight extends BulletObject implements ColligionHitInf {

    public BulletStraight() {
        super();
        name = "straight";
        target = null;
        parentObject = this.getClass();
    }

    @Override
    public void init(String S) {
        String[] split = S.split(",");
        if (split.length > 1) {
            init(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        }
    }

    public void init(double pow, double theta) {
        addHagrma(new HomingHagrma(pow + ",0,1,0,2,true,notarget_bullet,false," + theta));
        addHagrma(new OutRangeHagrma());
        CollionHagrma col = new CollionHagrma();
        col.setTag("bullet");
        addHagrma(col);
    }

    @Override
    public void show(Graphics g) {
        // TODO Auto-generated method stub
        super.show(g);
        Color defc = g.getColor();
        g.setColor(Color.RED);
        g.fillRect((int) BoundBox.getX(), (int) BoundBox.getY(), (int) BoundBox.getWidth(), (int) BoundBox.getHeight());
        g.setColor(defc);
    }

    @Override
    public void hitAction(BaseObject otherObject, CollionHagrma othercol) {
        if (target != null && otherObject.getClass().isAssignableFrom(target)) {
            this.destroy();
            if (otherObject instanceof CharactorObject) {
                ((CharactorObject) otherObject).damage(3);
            }
        } else if (!(otherObject instanceof BulletObject || otherObject.getClass().isAssignableFrom(parentObject))) {
            this.destroy();
            if (otherObject instanceof CharactorObject) {
                ((CharactorObject) otherObject).damage(3);
            }
        }
    }
}
