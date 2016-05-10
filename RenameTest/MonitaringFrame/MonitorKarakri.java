package MonitaringFrame;

import javax.swing.JFrame;

import ObjectEngin.HagrmaSys.Karakuri;

public class MonitorKarakri extends Karakuri{

	JFrame monitor;
	
	public void setMonitor(JFrame monitor) {
		this.monitor = monitor;
		priority = 0;
	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		monitor.repaint();
		
		return super.call();
	}
}
