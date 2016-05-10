package ObjectEngin.HagrmaSys.MouseKarakuri;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;

/**
 * <PRE>
 * マウスカラクリ
 * 属性：特殊カラクリ
 * パネルごとに取得するマウス動作リスナークラスです
 * オブジェクトマネージャーをインスタンス化するパネルに実装してください
 * </PRE>
 */
public class MouseKarakuri extends Karakuri implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static int WheelMoved = 1;
    public static int Dragged = 2;
    public static int Moved = 3;
    public static int Clicked = 4;
    public static int Pressed = 5;
    public static int Released = 6;
    public static int Exited = 7;
    public static int Entered = 8;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseEvent(WheelMoved, e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseEvent(Dragged, e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseEvent(Moved, e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseEvent(Clicked, e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseEvent(Pressed, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseEvent(Released, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEvent(Entered, e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseEvent(Exited, e);
    }

    synchronized private void mouseEvent(int EventName, MouseEvent e) {
        ArrayList<LinkedList<Hagrma>> hags = objM.getchiledHagrmas(MouseHagrma.class);
        for (LinkedList<Hagrma> Lh : hags) {
            for (Hagrma h : Lh) {
                if (h instanceof MouseHagrma) {
                    ((MouseHagrma) h).mouseEventListner(EventName, e);
                }
            }
        }
    }

}
