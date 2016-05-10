package MonitaringFrame;

import javax.swing.JFrame;

import ObjectEngin.ComonObject.BaseObject;

public class Monitor extends JFrame{
	MonitorPanel MP;
	public Monitor() {
		// TODO Auto-generated constructor stub
		setBounds(20, 20, 80, 80);
		setVisible(true);
		MP = new MonitorPanel();
		MP.setBounds(20, 20, 80, 80);
		add(MP);
	}
	public void setObject(BaseObject obj){
		
		MP.setTarget(obj);
	}
	
}
