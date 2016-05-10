package ObjectEngin.HagrmaSys.ComonKarakuri.MapKarakuri;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.Mapchip;

/**
 * <PRE>
 * 地図歯車（マップ歯車）
 * 属性：特殊歯車
 * マップクラス用に作成した歯車です
 * 基本的にはマップクラス内ですでに作成されています
 * </PRE>
 */
public class MapHagrma extends Hagrma {

    private int[][] intmap;
    private Mapchip[] mapchip;
    private boolean[][] checkMap;
    private boolean[][][] throwMap;

    private ArrayList<Point> leftMap;
    private ArrayList<Point> rightMap;
    private ArrayList<Point> upMap;
    private ArrayList<Point> downMap;

    private int chipsize;
    private HashMap<Integer, Integer> mapID;

    private int posx;
    private int posy;

    @Override
    public void setParent(BaseObject parent) {
        super.setParent(parent);
        if (parent instanceof MapClass) {
            intmap = ((MapClass) parent).getIntmaps();
            mapchip = ((MapClass) parent).getMaps();
            chipsize = ((MapClass) parent).getChipsize();
            posx = ((MapClass) parent).getPosx();
            posy = ((MapClass) parent).getPosy();
            mapID = ((MapClass) parent).getMapofmapID();
        }
    }

    public boolean isHit(CollionHagrma h) {
        Rectangle rect = h.getRect();
        leftMap = new ArrayList<Point>();
        rightMap = new ArrayList<Point>();
        upMap = new ArrayList<Point>();
        downMap = new ArrayList<Point>();
        int cx = (int) ((rect.getX() - posx) / chipsize);
        int cy = (int) ((rect.getY() - posy) / chipsize);
        int sx = (int) ((rect.getMaxX() - posx) / chipsize);
        int sy = (int) ((rect.getMaxY() - posy) / chipsize);
        if (cx < 0) {
            cx = 0;
        }
        if (cy < 0) {
            cy = 0;
        }
        if (sx >= intmap.length) {
            sx = intmap.length - 1;
        }
        if (sy >= intmap[0].length) {
            sy = intmap[0].length - 1;
        }
        if ((sx - cx + 1 <= 0) || (sy - cy + 1 <= 0)) {
            return false;
        }
        checkMap = new boolean[sx - cx + 1][sy - cy + 1];
        throwMap = new boolean[checkMap.length][checkMap[0].length][4];

        boolean check = false;
        int RL = (h.getParent().getBoundBox().x - h.getParent().getOldBox().x);
        int UD = (h.getParent().getBoundBox().y - h.getParent().getOldBox().y);

        for (int cntx = 0; cntx < sx - cx + 1; cntx++) {
            for (int cnty = 0; cnty < sy - cy + 1; cnty++) {
                Mapchip mc = mapchip[mapID.get(intmap[cntx + cx][cnty + cy])];
                throwMap[cntx][cnty] = mc.getNonthrow();
                if (cntx == 0 && RL < 0) {
                    checkMap[cntx][cnty] = mc.isBlockF() && mc.getNonthrow()[0];
                }
                if (cntx == sx - cx && RL > 0) {

                    checkMap[cntx][cnty] = mc.isBlockF() && mc.getNonthrow()[1];
                }
                if (cnty == 0 && UD < 0) {

                    checkMap[cntx][cnty] = mc.isBlockF() && mc.getNonthrow()[3];
                }
                if (cnty == sy - cy && UD >= 0) {

                    checkMap[cntx][cnty] = mc.isBlockF() && mc.getNonthrow()[2];
                }
                if (checkMap[cntx][cnty]) {
                    if (cntx == 0) {
                        leftMap.add(new Point(cntx + cx, cnty + cy));
                    } else if (cntx == sx - cx) {
                        rightMap.add(new Point(cntx + cx, cnty + cy));
                    }
                    if (cnty == 0) {
                        upMap.add(new Point(cntx + cx, cnty + cy));
                    } else if (cnty == sy - cy) {
                        downMap.add(new Point(cntx + cx, cnty + cy));
                    }

                    check = true;
                }
            }
        }
        return check;
    }

    public void push_left(CollionHagrma colhag) {
        boolean b = false;
        if (!rightMap.isEmpty()) {
            for (Point p : rightMap) {
                getTrriger(p.x, p.y, "right_hit", colhag);
                if (mapchip[mapID.get(intmap[p.x][p.y])].getNonthrow()[0]) {
                    b |= true;
                }
            }
        }
        if (b) {
            colhag.getParent().getBoundBox().translate((-(int) (colhag.getParent().getBoundBox().getMaxX() % chipsize) - 1), 0);
        }
    }

    public void push_right(CollionHagrma colhag) {
        boolean b = false;
        if (!leftMap.isEmpty()) {
            for (Point p : leftMap) {
                getTrriger(p.x, p.y, "left_hit", colhag);
                if (mapchip[mapID.get(intmap[p.x][p.y])].getNonthrow()[1]) {
                    b |= true;
                }
            }
        }
        if (b) {
            colhag.getParent().getBoundBox().translate(chipsize - (int) (colhag.getParent().getBoundBox().getMinX() % chipsize) + 1, 0);
        }
    }

    public void push_up(CollionHagrma colhag) {
        boolean b = false;
        if (!downMap.isEmpty()) {
            for (Point p : downMap) {
                getTrriger(p.x, p.y, "down_hit", colhag);
                if (mapchip[mapID.get(intmap[p.x][p.y])].getNonthrow()[2]) {
                    b |= true;
                }
            }
        }
        if (b) {
            colhag.getParent().getBoundBox().translate(0, -(int) (colhag.getParent().getBoundBox().getMaxY() % chipsize));
        }
    }

    public void push_down(CollionHagrma colhag) {
        boolean b = false;
        if (!upMap.isEmpty()) {
            for (Point p : upMap) {
                getTrriger(p.x, p.y, "up_hit", colhag);
                if (mapchip[mapID.get(intmap[p.x][p.y])].getNonthrow()[3]) {
                    b |= true;
                }
            }
        }
        if (b) {
            colhag.getParent().getBoundBox().translate(0, (chipsize - (int) (colhag.getParent().getBoundBox().getMinY() % chipsize) + 1));
        }
    }

    public int getChipsize() {
        return chipsize;
    }

    public boolean[][] getCheckMap() {
        return checkMap;
    }

    private void getTrriger(int posx, int posy, String trriger, CollionHagrma colhag) {
        if (parent instanceof MapClass) {
            ((MapClass) parent).setTrriger(posx, posy, trriger, colhag);
        }
    }

    public Mapchip getMapchip(int posx, int posy) {
        return mapID.containsKey(intmap[posx][posy]) ? mapchip[mapID.get(intmap[posx][posy])] : null;
    }

    public boolean[][][] getThrowMap() {
        return throwMap;
    }
}
