package ShootingGame.GameObject;

import java.awt.Rectangle;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.CameraKarakuri.RangeHagrma;

public class RangeObject extends BaseObject {

    public RangeObject() {
        super();
        addHagrma(new RangeHagrma());
    }

}
