package BaseSystem.FpsSetting;

import java.util.concurrent.TimeUnit;

/** fps計算クラス
 * 制限としてfpsは１〜１２０までの値としています
 *  */
public class FpsManager{
	
	private static long ONE_SEC_TO_NANO = TimeUnit.SECONDS.toNanos(1L);
	private static long ONE_MILLI_TO_NANO = TimeUnit.MILLISECONDS.toNanos(1L);
	
	private int maxfps;
	private int[] fpsBuffer;
	private int fpscnt;
	private long oneframetime;
	private long beforetime;
	private long sleeptime;
	private long Vtime;
	private int overtimecnt;
	private int overtimemax;
	private boolean overtimeflag;
	
	/** 
	 * コンストラクタ
	 * @param fpsmax int fpsを入力
	 * @param otmax int 誤差を入力
	 *  */
	public FpsManager(int fpsmax,int otmax){
			if (fpsmax<0 || fpsmax>120){
				maxfps=60;
			}else{
				maxfps=fpsmax;
			}
			fpsBuffer = new int[maxfps];
			fpscnt=0;
			beforetime=System.nanoTime();
			Vtime=(long)(Math.floor((double)ONE_SEC_TO_NANO/maxfps));
			overtimecnt=0;
			if (otmax<0 || otmax<maxfps){
				overtimemax=maxfps/2;
						}else{
				overtimemax=otmax;
			}
			overtimeflag=false;
	}
	/** 
	 * 誤差超え確認フラグ取得メソッド
	 * 誤差範囲を超えて画面更新時間がoverしたかを確認するメソッド
	 *  */
	public boolean getovertimeflag(){
		return overtimeflag;
	}
	/**
	 * Fps変更メソッド
	 * Fps値を変更します
	 * @param fpsmax int 変更後のFps
	 * @return int 実際に更新されたFps
	 	*/
	public int resetFpsmax(int fpsmax){
		
		if (fpsmax<0 || fpsmax>120){
			maxfps=60;
		}else{
			maxfps=fpsmax;
		}
		if (maxfps<overtimemax){
			overtimemax=maxfps/2;
		}
		fpsBuffer = new int[maxfps];
		fpscnt=0;
		return maxfps;
	}

	/**　
	 * 誤差変更メソッド
	 * 誤差の値を変更します
	 * @param otmax int 変更後の誤差値
	 * @return int 基本0
	 *  */
	public int resetOtmax(int otmax){
		
		if (otmax<0 || otmax<maxfps){
			overtimemax=maxfps/2;
					}else{
			overtimemax=otmax;
		}
		
	return 0;	
	}
	/**
	 * Fps計算メソッド
	 * 更新することでFpsの誤差などを計算し次の更新までの時間などを計算する
	 *  */
	public long fpsClc(){
		
		if (maxfps <= ++fpscnt){
			fpscnt=0;
		}
		
		oneframetime=System.nanoTime()-beforetime;
		sleeptime=Vtime-oneframetime;
		overtimeflag=false;
		if (sleeptime < ONE_MILLI_TO_NANO){
			if (++overtimecnt>overtimemax){
				overtimecnt=0;
				overtimeflag=true;
			}
			sleeptime = ONE_MILLI_TO_NANO;
		}
		
		int fps = (int)(ONE_SEC_TO_NANO/(oneframetime+sleeptime));
		fpsBuffer[fpscnt]=fps;
		beforetime=System.nanoTime()+sleeptime;
		return sleeptime;
	}
	/** 
	 * Fps取得メソッド
	 * 過去１秒間におけるFpsレートの平均値を取得
	 * @return int １秒間のFpsレート平均値*/
	public int getFps(){
		int allfps=0;
		for (int cnt=0;cnt < maxfps; cnt++){
			allfps+=fpsBuffer[cnt];
		}
		return allfps/maxfps;
		}
	
}
