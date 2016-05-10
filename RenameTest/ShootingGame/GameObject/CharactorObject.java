package ShootingGame.GameObject;

import java.awt.Graphics;

import ObjectEngin.ComonObject.BaseObject;

public class CharactorObject extends BaseObject{

	private int HP;
	public CharactorObject() {
		super();
		setHP(0);
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		super.show(g);
		g.drawString("HP:"+HP, (int)BoundBox.getX(), (int)BoundBox.getY()-5);
	}
	public void damage(int damage){
		HP -= damage;
	}
}
