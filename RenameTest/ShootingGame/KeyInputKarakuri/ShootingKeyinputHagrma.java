package ShootingGame.KeyInputKarakuri;

import java.awt.event.KeyEvent;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;
import ShootingGame.GameObject.Myfighter;
public class ShootingKeyinputHagrma extends KeyinputHagrma{

	private boolean[] keyFlag;
	
	private boolean jump;
	
	private int time;
	
	public ShootingKeyinputHagrma() {
		keyFlag  = new boolean[6];
		jump = false;
		time = 0;
	}
	public void keyInputTest(){
		System.out.println("ハグルマで確認");
	}

	public void keyinput(int keycode){
		if(keycode == KeyEvent.VK_LEFT){
//			move(-10,0);
			keyFlag[0] = true;
		}
		if(keycode == KeyEvent.VK_RIGHT){
//			move(10,0);
			keyFlag[1] = true;
		}
		if(keycode == KeyEvent.VK_UP){
//			GravHagrma gravh = null;
//			for(Hagrma hag:parent.getHagrmas()){
//				if(hag instanceof GravHagrma)
//					gravh = (GravHagrma)hag;
				keyFlag[2] = true;
//			}
//			if(gravh != null){
//				gravh.setSpeed(-5);
//				parent.getBoundBox().translate(0, -1);
//			}
		}
		if(keycode == KeyEvent.VK_DOWN){
			keyFlag[3] = true;
		}
		if(keycode == KeyEvent.VK_SPACE && !jump){
//			GH.setEnable(true);
//			GH.setSpeed(-5);
//			parent.getBoundBox().translate(0, -1);
			keyFlag[4] = true;
//			jump = true;
		}

	}
	public void Relese(int keycode){
		if(keycode == KeyEvent.VK_LEFT){
			keyFlag[0] = false;
		}
		if(keycode == KeyEvent.VK_RIGHT){
			keyFlag[1] = false;
		}
		if(keycode == KeyEvent.VK_UP){
			keyFlag[2] = false;
		}
		if(keycode == KeyEvent.VK_DOWN){
			keyFlag[3] = false;
		}
//		if(keycode == KeyEvent.VK_SPACE){
//			GH.setEnable(true);
//			GH.setSpeed(-5);
//			parent.getBoundBox().translate(0, -1);
//		}
		if(keycode == KeyEvent.VK_SPACE){
			keyFlag[4] = false;
			time = 0;
		}
	}
	public void hagrmaRun(){
		parent.boxrefresh();
		if(keyFlag[0]){
			move(-5,0);
		}
		if(keyFlag[1]){
			move(5,0);
		}
		if(keyFlag[2]){
			move(0,-5);
		}
		if(keyFlag[3]){
			move(0,5);
		}
		if(keyFlag[4] && !jump){
////			move(0,-5);
//			GravHagrma gravh = null;
//			for(Hagrma hag:parent.getHagrmas()){
//				if(hag instanceof GravHagrma){
//					gravh = (GravHagrma)hag;
//					break;
//				}
//			}
//			if(gravh != null){
//				jump = true;
//				gravh.setSpeed(-9);
//				parent.getBoundBox().translate(0, -1);
//			}
			if(time > -1){
				time--;
			}
			if(parent instanceof Myfighter){
				if(time < 0){
				((Myfighter) parent).shoot();
				time = 10;
				}
			}
		}
	}
	public void setJump(boolean jump) {
//		System.out.println("?");
		this.jump = jump;
	}
	public void move(int x,int y){
//		parent.moveBox(x, y);
//		parent.setOldBox((Rectangle) parent.getBoundBox().clone());
		parent.getBoundBox().translate(x,y);

	}

}
