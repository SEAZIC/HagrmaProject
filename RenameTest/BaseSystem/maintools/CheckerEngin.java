package BaseSystem.maintools;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import BaseSystem.FpsSetting.FpsManager;

public class CheckerEngin implements Callable<String> {

    FpsManager fpsM;

    public CheckerEngin() {
        fpsM = new FpsManager(1, 0);
    }

    @Override
    public String call() {
        while (true) {
            try {

                System.out.println(getMemoryInfo());

                TimeUnit.NANOSECONDS.sleep(fpsM.fpsClc());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (fpsM.getovertimeflag()) {

                Thread.yield();
            }
        }
    }

    /**
     * Java 仮想マシンのメモリ総容量、使用量、 使用を試みる最大メモリ容量の情報を返します。
     *
     * @return Java 仮想マシンのメモリ情報
     */
    public static String getMemoryInfo() {
        DecimalFormat f1 = new DecimalFormat("#,###KB");
        DecimalFormat f2 = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024;
        long used = total - free;
        double ratio = (used * 100 / (double) total);
        String info
                = "Java メモリ情報 : 合計=" + f1.format(total) + "、"
                + "使用量=" + f1.format(used) + " (" + f2.format(ratio) + "%)、"
                + "使用可能最大=" + f1.format(max);
        return info;
    }
}
