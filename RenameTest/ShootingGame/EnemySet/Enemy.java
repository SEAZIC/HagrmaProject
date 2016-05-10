package ShootingGame.EnemySet;

import java.awt.Graphics;
import java.util.LinkedList;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.ColligionHitInf;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveHagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrmaListner;
import ShootingGame.BulletHagrma.BulletHagrmaListner;
import ShootingGame.GameObject.CharactorObject;
import ShootingGame.GameObject.Myfighter;
import ShootingGame.StateHagrma.StateHagrma;
import ShootingGame.StateHagrma.StateHagrmaListner;

public class Enemy extends CharactorObject implements StateHagrmaListner,OutRangeHagrmaListner,ColligionHitInf,BulletHagrmaListner{
	private MoveHagrma nowmoveHagrma;
	
	public Enemy() {
		super();
		name = "testEnemy";
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		super.show(g);
		g.fillRect((int)BoundBox.getX(), (int)BoundBox.getY(), (int)BoundBox.getWidth(), (int)BoundBox.getHeight());
//		System.out.println(" x "+BoundBox.x+" y "+BoundBox.y+" w "+BoundBox.width+" h "+BoundBox.height);
	}
	@Override
	public void chengedState(StateHagrma shagrma) {
		System.out.println("chengedState "+shagrma.getState());
		if(shagrma.isEmpty())
			this.destroy();
	}
	@Override
	public void outAction() {
		this.destroy();
	}
	@Override
	public void hitAction(BaseObject otherObject,CollionHagrma othercol) {
		System.out.println("呼ばれぬこの思い");
	}
	@Override
	public Class<? extends BaseObject> getParentClass() {
		// TODO Auto-generated method stub
		return this.getClass();
	}
	@Override
	public Class<? extends BaseObject> getTargetClass() {
		// TODO Auto-generated method stub
		return Myfighter.class;
	}
	@Override
	public void damage(int damage) {
		// TODO Auto-generated method stub
		super.damage(damage);
		if(getHP() <= 0)
			destroy();
	}
}
