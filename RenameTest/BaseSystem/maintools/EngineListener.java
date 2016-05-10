package BaseSystem.maintools;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutorService;

public class EngineListener implements WindowListener{

	ExecutorService ES;

	public void setES(ExecutorService eS) {
		ES = eS;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("と時る");
//		if(ES != null && !ES.isShutdown()){
//			ES.shutdown();
//			System.out.println("シャットダウン！");
//		}
		e.getWindow().dispose();
		if(ES != null && !ES.isShutdown()){
			ES.shutdown();
			System.out.println("シャットダウン！");
		}else if(ES.isShutdown()){
			System.out.println("シャットダウンなう");
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("と時た");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
