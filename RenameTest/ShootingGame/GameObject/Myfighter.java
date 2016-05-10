package ShootingGame.GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.ColligionHitInf;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.DrawableKarakuri.DrawableHagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrma;
import ObjectEngin.HagrmaSys.OutRangeKarakuri.OutRangeHagrmaListner;
import ShootingGame.EnemySet.Enemy;
import ShootingGame.GameObject.Bullet.BulletObject;
import ShootingGame.GameObject.Bullet.BulletStraight;
import ShootingGame.KeyInputKarakuri.ShootingKeyinputHagrma;

public class Myfighter extends CharactorObject implements OutRangeHagrmaListner,ColligionHitInf{

	private ObjectManager objM;
	public Myfighter() {
		super();
		ShootingKeyinputHagrma keyhag = new ShootingKeyinputHagrma();
		CollionHagrma colh = new CollionHagrma();
		colh.setTag("myfighter");
		addHagrma(keyhag);
		addHagrma(colh);
		addHagrma(new OutRangeHagrma());
		DrawableHagrma dh = new DrawableHagrma();
		addHagrma(dh);
		dh.Init("shootingGame/stage1/enemyImage/myFighter.png,myfighterimage,30,40");
		BoundBox.setLocation(200, 300);
		BoundBox.setSize(30,40);
		setHP(30);
		this.name = "myfighter";
	}
	@Override
	public void addMane(ObjectManager objM) {
		super.addMane(objM);
		this.objM = objM;
	}
	@Override
	public void show(Graphics g) {
		super.show(g);
		g.fillRect(BoundBox.x, BoundBox.y, BoundBox.width, BoundBox.height);
	}
	@Override
	public void outAction() {
		System.out.println("OutRange!");
		this.reverse();

	}
	@Override
	public void hitAction(BaseObject otherObject,CollionHagrma othercol) {
	}
	public void shoot(){
		int sizex = 10;
		int sizey = 10;
		BulletStraight BObj = new BulletStraight();
		Rectangle b = this.getBoundBox();
		BObj.setBoundBox(new Rectangle((int)(b.getCenterX()-(sizex/2)), (int)(b.getY()-(sizey/2)), (int)sizex, (int)sizey));
		BObj.boxrefresh();
		BObj.setTarget(this.getClass(), Enemy.class);
		BObj.init(8.0, -1.6707);
		objM.addReqest(BObj);
	}
}
