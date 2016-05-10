package ShootingGame.DynamicObjectManager;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import BaseSystem.FpsSetting.FpsManager;
import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;

public class ShootingEnemyCreaterEngin implements Callable<String> {

    private long oldtime;
    private long nowtime;

    private long time;

    private LinkedList<BaseObject> addobj;
    private LinkedList<Integer> sectimes;
    private LinkedList<Integer> milsectimes;
    private ObjectManager ObjMana;

    private FpsManager fpsM;

    public ShootingEnemyCreaterEngin() {
        time = 0l;
        oldtime = System.currentTimeMillis();
        fpsM = new FpsManager(120, 1);
    }

    public void setInit(LinkedList<BaseObject> addobj, LinkedList<Integer> sectimes, LinkedList<Integer> milsectimes, ObjectManager ObjMana) {
        this.addobj = addobj;
        this.sectimes = sectimes;
        this.milsectimes = milsectimes;
        this.ObjMana = ObjMana;
    }

    @Override
    public String call() throws Exception {
        while (!addobj.isEmpty()) {
            nowtime = System.currentTimeMillis();

            time += nowtime - oldtime;
            LinkedList<BaseObject> removelist = new LinkedList<BaseObject>();
            for (BaseObject checkbo : addobj) {
                if (time > sectimes.getFirst() * 1000 + milsectimes.getFirst()) {
                    ObjMana.addReqest(checkbo);
                    removelist.add(checkbo);
                    sectimes.removeFirst();
                    milsectimes.removeFirst();
                } else {
                    break;
                }
            }
            addobj.removeAll(removelist);
            TimeUnit.NANOSECONDS.sleep(fpsM.fpsClc());
            oldtime = nowtime;
        }
        return "allObject_ADDed";
    }
}
