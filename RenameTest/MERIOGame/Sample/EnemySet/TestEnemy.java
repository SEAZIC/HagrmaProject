package MERIOGame.Sample.EnemySet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.CameraKarakuri.TargetHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.ColligionHagrmaInf;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravHagrma;

public class TestEnemy extends BaseObject implements ColligionHagrmaInf{
	private GravHagrma gv;
	private Color color;

	public TestEnemy() {
		super();
		name = "testEnemy";
		BoundBox.setLocation(720, 20);
		BoundBox.setSize(30,40);
		gv = new GravHagrma();
		gv.setGrav(new Point(0,1),0.3,1);
		addHagrma(gv);
		addHagrma(new CollionHagrma());
		color = new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		super.show(g);
		g.setColor(color);
		g.fillRect(BoundBox.x, BoundBox.y, BoundBox.width, BoundBox.height);
	}
	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		super.reverse();
//		gv.setEnable(false);
		gv.setSpeed(0);
	}
	@Override
	public void boxrefresh() {
		// TODO Auto-generated method stub
		super.boxrefresh();
//		gv.setSpeed(0);
	}

	@Override
	public void RightPushed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LeftPushed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpPushed() {
		// TODO Auto-generated method stub
		gv.setSpeed(0);
//		keyH.setJump(false);
	}

	@Override
	public void DownPushed() {
		// TODO Auto-generated method stub
		gv.setSpeed(0);
	}
	@Override
	public void addHagrma(Hagrma hagrma) {
		// TODO Auto-generated method stub
		super.addHagrma(hagrma);
		
	}
}
