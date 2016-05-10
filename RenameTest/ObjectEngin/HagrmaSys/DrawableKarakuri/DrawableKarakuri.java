package ObjectEngin.HagrmaSys.DrawableKarakuri;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

public class DrawableKarakuri extends Karakuri {

    public void show(Graphics g) {
        ArrayList<LinkedList<Hagrma>> hagrma = objM.getchiledHagrmas(DrawableHagrma.class);
        for (LinkedList<Hagrma> hag : hagrma) {
            for (Hagrma drawhagrma : hag) {
                if (drawhagrma instanceof DrawableHagrma) {
                    DrawableHagrma drawhag = (DrawableHagrma) drawhagrma;

                    drawhag.show(g);

                }

            }
        }
    }

}
