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
import ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri.HomingHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveHagrma;

public class TestEnemy3 extends BaseObject implements ColligionHagrmaInf{

	private Color color;

	public TestEnemy3(BaseObject target) {
		super();
		name = "testEnemy2";
		BoundBox.setLocation(200, 500);
		BoundBox.setSize(30,40);
//		MoveHagrma MH = new MoveHagrma("100sin(t)+100cos(t)sin(t)", "100cos(t)+100cos^2(t)", 0.09, 0.0, 6.28318, true);
		HomingHagrma HM = new HomingHagrma(target, 6, 0.2);
		addHagrma(new CollionHagrma());
		addHagrma(HM);
//		addHagrma(MH);
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
	}

	@Override
	public void DownPushed() {
	}
	@Override
	public void addHagrma(Hagrma hagrma) {
		// TODO Auto-generated method stub
		super.addHagrma(hagrma);
		
	}
}
