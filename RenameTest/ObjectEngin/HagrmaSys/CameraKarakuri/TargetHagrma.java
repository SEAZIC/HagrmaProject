package ObjectEngin.HagrmaSys.CameraKarakuri;

import java.awt.Point;

import ObjectEngin.HagrmaSys.Hagrma;

public class TargetHagrma extends Hagrma {

    private int havy;
//	private Point clipPos;

    public TargetHagrma() {
        super();
        havy = 1;
    }

    public void setHavy(int havy) {
        this.havy = havy;
    }

    public int getHavy() {
        return havy;
    }

    public void setPos(Point setpos) {
//		clipPos.setLocation(setpos.getX(), setpos.getY());
        if (this.parent instanceof CameraTargetListner) {
            ((CameraTargetListner) this.parent).locateChenge(setpos);
        }
    }
}
