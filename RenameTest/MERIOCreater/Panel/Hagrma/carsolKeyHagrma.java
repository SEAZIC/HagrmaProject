package MERIOCreater.Panel.Hagrma;

import java.awt.event.KeyEvent;

import MERIOCreater.Panel.Object.carsolObject;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputKarakuri;

public class carsolKeyHagrma extends KeyinputHagrma{

	public carsolKeyHagrma() {
		super();
	}
	@Override
	public void keyinput(int keycode) {
		// TODO Auto-generated method stub
		super.keyinput(keycode);
		if(keycode == KeyEvent.VK_LEFT){
			if(parent instanceof carsolObject){
				((carsolObject) parent).movechip(-1, 0);
			}
		}
		if(keycode == KeyEvent.VK_RIGHT){
			if(parent instanceof carsolObject){
				((carsolObject) parent).movechip(1, 0);
			}
		}
		if(keycode == KeyEvent.VK_UP){
			if(parent instanceof carsolObject){
				((carsolObject) parent).movechip(0, -1);
			}
		}
		if(keycode == KeyEvent.VK_DOWN){
			if(parent instanceof carsolObject){
				((carsolObject) parent).movechip(0, 1);
			}
		}
	}
	@Override
	public void Relese(int keycode) {
		// TODO Auto-generated method stub
		super.Relese(keycode);
	}
}
