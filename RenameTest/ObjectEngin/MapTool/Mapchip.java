package ObjectEngin.MapTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import BaseSystem.ImageTool.ImageServer;
import BaseSystem.TextEdit.TextReader;

/**
 * <PRE>
 * マップチップクラス
 * チップごとのテンプレートになるクラス
 * </PRE>
 */
public class Mapchip {

    private int mapID;
    private int[] element;
    private String imagename;
    private ImageServer IS;
    private boolean blockF;
    private boolean[] nonthrow;
    private HashMap<String, String> state;
    private ArrayList<String> data;

    private int maxLongString;

    public Mapchip(String filename) {

        IS = new ImageServer();

        TextReader textReader = new TextReader();
        //		textReader.setSpacedelete(true);
        textReader.readFile(filename);
        data = textReader.getTextData();
        state = new HashMap<String, String>();
        boolean tokenF = false;

        nonthrow = new boolean[]{true, true, true, true};

        String tokenkey = "";
        String tokenvalue = "";
        maxLongString = 0;
        for (String S : data) {
            if (maxLongString < S.trim().length()) {
                maxLongString = S.trim().length();
            }
            System.out.println(S);
            if (S.matches("([^\\{]*)(\\{)(.*)")) {
                tokenF = true;
                tokenvalue = "";
                String[] copyS = S.split("\\{");
                tokenkey = copyS[0];
                if (copyS.length > 1) {
                    if (S.matches("([^\\}]*)(\\})(.*)")) {
                        copyS = S.split("\\}");
                        tokenkey += copyS[copyS.length - 1];
                        tokenF = false;
                    } else {
                        tokenvalue += copyS[1] + "}";
                    }
                }
            } else if (S.matches("([^\\}]*)(\\})(.*)")) {
                tokenF = false;
                String[] copyS = S.split("\\}");
                if (copyS.length > 0) {
                    tokenkey += copyS[copyS.length - 1];
                }
                System.out.println(tokenvalue);
                state.put(tokenkey, tokenvalue);
            } else if (!tokenF) {
                String[] SS = S.split(":");
                state.put(SS[0].trim(), SS[1].trim());
            } else {
                tokenvalue += S + "}";
            }
            //			System.out.println(SS[0]+"|"+SS[1]);
        }

        if (state.containsKey("image")) {
            String fname = state.get("image");
            System.out.println(fname);
            IS.setOverrite(false);
            IS.setdefault("Res/data");
            IS.createImage(fname, fname);
            //			if(IS.getImage(fname) != null){
            //				System.out.println("createdfile");
            //			}
            if (state.containsKey("name")) {
                imagename = state.get("name");
                int x = Integer.parseInt(state.get("posx"));
                int y = Integer.parseInt(state.get("posy"));
                int w = Integer.parseInt(state.get("sizex"));
                int h = Integer.parseInt(state.get("sizey"));
                IS.createImage(imagename, IS.getImage(fname).getSubimage(x, y, w, h));
                System.out.println("setImage = " + imagename);
            }
        }
        if (state.containsKey("mapID")) {
            mapID = Integer.parseInt(state.get("mapID"));
        }
        if (state.containsKey("block")) {
            blockF = "true".equalsIgnoreCase(state.get("block"));
        } else {
            blockF = true;
        }
        if (state.containsKey("leftthrow")) {
            nonthrow[0] = !Boolean.parseBoolean(state.get("leftthrow"));
        }
        if (state.containsKey("rightthrow")) {
            nonthrow[1] = !Boolean.parseBoolean(state.get("rightthrow"));
        }
        if (state.containsKey("upthrow")) {
            nonthrow[2] = !Boolean.parseBoolean(state.get("upthrow"));
        }
        if (state.containsKey("downthrow")) {
            nonthrow[3] = !Boolean.parseBoolean(state.get("downthrow"));
        }
    }

    public int getMapID() {
        return mapID;
    }

    public String getImagename() {
        return imagename;
    }

    public BufferedImage getImage() {
        return IS.getImage(imagename);
    }

    public boolean isBlockF() {
        return blockF;
    }

    public String getTrriger(String trriger) {
        return state.get(trriger);
    }

    public boolean[] getNonthrow() {
        return nonthrow;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public int getMaxLongString() {
        return maxLongString;
    }
}
