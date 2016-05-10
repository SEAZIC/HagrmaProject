package MERIOCreater.Panel.Object;

import java.awt.event.KeyEvent;

import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputHagrma;

public class carsolKeyHagrma extends KeyinputHagrma{

	private StopperObject stopoer;
	private boolean addstop;
	
	public carsolKeyHagrma() {
		super();
		stopoer = new StopperObject();
	}
	public void keyinput(int keycode) {

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
				((carsolObject) parent).movechip(0,1);
			}
		}
		if(keycode == KeyEvent.VK_SPACE){
			if(!addstop)
			if(parent instanceof carsolObject){
				addstop = true;
				stopoer.getBoundBox().setBounds(parent.getBoundBox());
				((carsolObject) parent).getObjM().addReqest(stopoer);
			}
		}
	}
	@Override
	public void Relese(int keycode) {
		// TODO Auto-generated method stub
		super.Relese(keycode);

		if(keycode == KeyEvent.VK_SPACE){
			addstop = false;
			if(parent instanceof carsolObject){
				stopoer.destroy();
				stopoer = new StopperObject();
			}
		}
	}
}
