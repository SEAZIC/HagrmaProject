package BaseSystem.maintools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Sample.GameMainOption;
import Sample.Frame.mainFrame;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameMain {

    public static void main(String[] args) {

        GameEngin GE = new GameEngin();
        ExecutorService ES = Executors.newScheduledThreadPool(2);
//		EngineListener EL = new EngineListener();
//		EL.setES(ES);
//		GE.setEL(EL);
        GameMainOption GMO = new GameMainOption(GE);
        GMO.setES(ES);
        ArrayList<Callable<String>> threadList = new ArrayList<Callable<String>>();

        GMO.init();
        threadList.add(GE);
        if (GMO.memoricheck()) {
            CheckerEngin CE = new CheckerEngin();
            threadList.add(CE);
        }
        try {
            for (Callable<String> r : threadList) {
                ES.submit(r);
            }
            try {
                ES.invokeAll(threadList);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            try {
                ES.shutdown();
                if (!ES.awaitTermination(500, TimeUnit.NANOSECONDS)) {
                    ES.shutdownNow();
                    if (!ES.awaitTermination(500, TimeUnit.NANOSECONDS)) {
                        System.out.println("ERROR:main - EnginTask - shutdown");
                    }
                }
                //System.exit(0);
            } catch (InterruptedException ex) {
                ES.shutdownNow();
                Logger.getLogger(GameMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
