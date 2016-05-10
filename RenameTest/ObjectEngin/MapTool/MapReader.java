package ObjectEngin.MapTool;

import java.util.ArrayList;

import BaseSystem.TextEdit.TextReader;

/**
 * <PRE>
 * マップリーダー
 * テキストファイルのマップをマップクラスとして作るクラス
 * </PRE>
 */
public class MapReader {

    private TextReader textReader;
    private ArrayList<String> mapchip;
    private int[][] intMaps;

    public MapReader() {
        textReader = new TextReader();
        mapchip = new ArrayList<String>();
        intMaps = new int[][]{};
    }

    private void readmap(String filename, boolean intf) {
        textReader.readFile(filename);
        System.out.println("MapReader");
        int cnty = 0;
        if (textReader.isTextFound()) {
            int x = textReader.getTextData().get(0).split(",").length;
            int y = textReader.getTextData().size() - 1;
            if (intf) {
                intMaps = new int[x][y];
            }
            for (String S : textReader.getTextData()) {
                int cntx = 0;
                String[] splitresult = S.split(",");
                if (splitresult == null || splitresult.length < 1) {
                    break;
                }
                if (splitresult[0].equalsIgnoreCase("mapchip")) {
                    mapchip.add(splitresult[1]);
                    System.out.println("saas  " + mapchip);
                    continue;
                }
                for (String SS : splitresult) {
                    if (intf) {
                        intMaps[cntx][cnty] = Integer.parseInt(SS);
                    }
                    cntx++;
                }
                //System.out.println("");
                cnty++;
            }
        }
    }

    public int[][] readIntMap(String filename) {
        readmap(filename, true);
        return intMaps;
    }

    public int[][] getIntMaps() {
        return intMaps;
    }

    public ArrayList<String> getMapchip() {
        return mapchip;
    }
}
