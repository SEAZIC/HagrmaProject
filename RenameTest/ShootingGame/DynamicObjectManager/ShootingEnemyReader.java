package ShootingGame.DynamicObjectManager;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveHagrma;
import ObjectEngin.dynamicObj.DynaObjManager;
import ObjectEngin.dynamicObj.DynaObjReader;
import ShootingGame.StateHagrma.StateHagrma;
import ShootingGame.StateHagrma.StateLoader;

public class ShootingEnemyReader extends DynaObjReader {

    private LinkedList<Integer> sectimes;
    private LinkedList<Integer> milsectimes;
    private int sectime;
    private int milsectime;
    private String defaultPass;

    public ShootingEnemyReader(String filename) {
        super(filename);
        sectimes = new LinkedList<Integer>();
        milsectimes = new LinkedList<Integer>();
        sectime = 0;
        milsectime = 0;
        defaultPass = "";
    }

    @Override
    public LinkedList<BaseObject> getDynaObjects(DynaObjManager DOM, int chipsize) {
        return super.getDynaObjects(DOM, chipsize);
    }

    @Override
    protected void addAction(LinkedList<BaseObject> obj, DynaObjManager DOM, String[] spr, int chipsize) {
        if (spr.length > 0) {
            if (spr[0].equalsIgnoreCase("time")) {
                if (spr.length > 1) {
                    if (sectime < Integer.parseInt(spr[1])) {
                        sectime = Integer.parseInt(spr[1]);
                        if (spr.length > 2) {
                            milsectime = Integer.parseInt(spr[2]);
                        } else {
                            milsectime = 0;
                        }
                    } else if (spr.length > 2) {
                        if (milsectime < Integer.parseInt(spr[2])) {
                            milsectime = Integer.parseInt(spr[2]);
                        }
                    } else {
                        milsectime = 0;
                    }
                }
                return;
            }
            if (spr[0].equalsIgnoreCase("defaultPass")) {
                if (spr.length > 1) {
                    defaultPass = spr[1];
                }
            }
        }
        if (spr.length > 4) {
            Rectangle rec = new Rectangle(
                    (int) (Double.parseDouble(spr[0]) * (double) chipsize),
                    (int) (Double.parseDouble(spr[1]) * (double) chipsize),
                    (int) (Double.parseDouble(spr[2]) * (double) chipsize),
                    (int) (Double.parseDouble(spr[3]) * (double) chipsize)
            );
            BaseObject dyObj = DOM.getObj(spr[4]);

            dyObj.getBoundBox().setBounds(rec);
            dyObj.setOldBox(rec);
            if (spr.length > 11) {
                if (spr[5].equalsIgnoreCase("free")) {
                    dyObj.addHagrma(new MoveHagrma(spr[6], spr[7], Double.parseDouble(spr[8]) * (double) chipsize,
                            Double.parseDouble(spr[9]) * (double) chipsize, Double.parseDouble(spr[10]) * (double) chipsize, Boolean.parseBoolean(spr[11])
                    )
                    );
                }
                if (spr[5].equalsIgnoreCase("moves")) {
                    setHagrma(dyObj, spr, 6, chipsize);
                    //					for(int cnt = 1;cnt < ((spr.length-6)/6)+1;cnt++){
                    //						dyObj.addHagrma(new MoveHagrma(spr[cnt*6], spr[cnt*6+1], Double.parseDouble(spr[cnt*6+2])*(double)chipsize,
                    //								Double.parseDouble(spr[cnt*6+3])*(double)chipsize, Double.parseDouble(spr[cnt*6+4])*(double)chipsize, Boolean.parseBoolean(spr[cnt*6+5])
                    //								)
                    //							);
                    //					}
                }
                System.out.println("moveHagrmaSet");
            }
            if (spr[5].equalsIgnoreCase("files")) {
                StateHagrma SH = new StateHagrma();
                StateLoader SL = new StateLoader(defaultPass + spr[6], SH, dyObj);
                dyObj.addHagrma(SH);
            }
            //			dyObj.getOldBox();
            if (dyObj != null) {
                obj.add(dyObj);
                sectimes.add(sectime);
                milsectimes.add(milsectime);
            }
        }
    }

    private void setHagrma(BaseObject dyObj, String[] spr, int offset, int chipsize) {
        for (int cnt = offset; cnt < spr.length; cnt += 6) {
            dyObj.addHagrma(
                    new MoveHagrma(spr[cnt], spr[cnt + 1],
                            Double.parseDouble(spr[cnt + 2]) * (double) chipsize,
                            Double.parseDouble(spr[cnt + 3]) * (double) chipsize,
                            Double.parseDouble(spr[cnt + 4]) * (double) chipsize,
                            Boolean.parseBoolean(spr[cnt + 5])
                    )
            );
        }
    }

    public LinkedList<Integer> getSectimes() {
        return sectimes;
    }

    public LinkedList<Integer> getMilsectimes() {
        return milsectimes;
    }
}
