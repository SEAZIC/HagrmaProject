package MonitaringFrame;

import java.awt.Graphics;

import javax.swing.JPanel;

import ObjectEngin.ComonObject.BaseObject;

public class MonitorPanel extends JPanel{
	BaseObject target;
	int size;
	public void setTarget(BaseObject target) {
		this.target = target;
		size = 2;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(target != null){
		g.drawLine(this.getWidth()/2, this.getHeight()/2,
				this.getWidth()/2+((target.getBoundBox().x-target.getOldBox().x)*size),
				this.getHeight()/2+((target.getBoundBox().y-target.getOldBox().y)*size));
		g.drawString(""+(target.getBoundBox().x-target.getOldBox().x), 0, 20);
		g.drawString(""+(target.getBoundBox().y-target.getOldBox().y), 0, 40);
		}

	}

}
