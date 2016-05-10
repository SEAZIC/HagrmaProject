package ObjectEngin.HagrmaSys.KeyInputKarakuri;

import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

/**
 * <PRE>
 * キー入力カラクリ
 * 属性：特殊カラクリ
 * キー入力歯車を操作するためのカラクリです．
 * オブジェクトマネージャーに追加するときに，フレームにもキーリスナーとして登録してください．
 * </PRE>
 */
public class KeyinputKarakuri extends Karakuri implements KeyListener {

    public KeyinputKarakuri() {
        priority = 3;
    }

    @Override
    public String call() throws Exception {
        ArrayList<LinkedList<Hagrma>> hags = objM.getchiledHagrmas(KeyinputHagrma.class);
        for (LinkedList<Hagrma> Lh : hags) {
            for (Hagrma h : Lh) {
                if (h instanceof KeyinputHagrma) {
                    ((KeyinputHagrma) h).hagrmaRun();
                }
            }
        }
        return super.call();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<LinkedList<Hagrma>> hags = objM.getchiledHagrmas(KeyinputHagrma.class);
        for (LinkedList<Hagrma> Lh : hags) {
            for (Hagrma h : Lh) {
                if (h instanceof KeyinputHagrma) {
                    ((KeyinputHagrma) h).keyinput(e.getKeyCode());
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ArrayList<LinkedList<Hagrma>> hags = objM.getchiledHagrmas(KeyinputHagrma.class);
        for (LinkedList<Hagrma> Lh : hags) {
            for (Hagrma h : Lh) {
                if (h instanceof KeyinputHagrma) {
                    ((KeyinputHagrma) h).Relese(e.getKeyCode());
                }
            }
        }
    }

}
