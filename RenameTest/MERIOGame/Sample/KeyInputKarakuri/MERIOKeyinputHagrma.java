package MERIOGame.Sample.KeyInputKarakuri;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout.SequentialGroup;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravHagrma;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;

public class MERIOKeyinputHagrma extends KeyinputHagrma{

	private GravHagrma GH;

	private boolean[] keyFlag;
	
	private boolean jump;
	
	public MERIOKeyinputHagrma() {
		keyFlag  = new boolean[5];
		jump = false;
	}
	public void keyInputTest(){
		System.out.println("ハグルマで確認");
	}

	public void keyinput(int keycode){
		System.out.println(""+keycode);
		if(keycode == KeyEvent.VK_LEFT){
//			System.out.println("ひだりー");
//			move(-10,0);
			keyFlag[0] = true;
		}
		if(keycode == KeyEvent.VK_RIGHT){
//			System.out.println("右ー");
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
//			System.out.println("うええええええー");
		}
		if(keycode == KeyEvent.VK_DOWN){
//			System.out.println("YO下だYOー");
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
		System.out.println(""+keycode);
		if(keycode == KeyEvent.VK_LEFT){
//			System.out.println("ひだりー");
			
			keyFlag[0] = false;
		}
		if(keycode == KeyEvent.VK_RIGHT){
//			System.out.println("右ー");
//			move(10,0);
			keyFlag[1] = false;
		}
		if(keycode == KeyEvent.VK_UP){
			keyFlag[2] = false;
//			System.out.println("うええええええー");
		}
		if(keycode == KeyEvent.VK_DOWN){
//			System.out.println("YO下だYOー");
		}
//		if(keycode == KeyEvent.VK_SPACE){
//			GH.setEnable(true);
//			GH.setSpeed(-5);
//			parent.getBoundBox().translate(0, -1);
//		}
		if(keycode == KeyEvent.VK_SPACE){
//			System.out.println("ui-");
			keyFlag[4] = false;
//			jump = false;
		}
	}
	public void setGrav(GravHagrma GH){
//		System.out.println("GH");
		this.GH = GH;
	}

	public void hagrmaRun(){
		if(keyFlag[0]){
			move(-3,0);
		}
		if(keyFlag[1]){
			move(3,0);
		}
		if(keyFlag[2]){
//			move(0,-5);
			GravHagrma gravh = null;
			for(Hagrma hag:parent.getHagrmas()){
				if(hag instanceof GravHagrma){
					gravh = (GravHagrma)hag;
					break;
				}
			}
			if(gravh != null){
				gravh.setSpeed(-5);
				parent.getBoundBox().translate(0, -1);
			}
		}
		if(keyFlag[4] && !jump){
//			move(0,-5);
			GravHagrma gravh = null;
			for(Hagrma hag:parent.getHagrmas()){
				if(hag instanceof GravHagrma){
					gravh = (GravHagrma)hag;
					break;
				}
			}
			if(gravh != null){
				jump = true;
				gravh.setSpeed(-9);
				parent.getBoundBox().translate(0, -1);
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
