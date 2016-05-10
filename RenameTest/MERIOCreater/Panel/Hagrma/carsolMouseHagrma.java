package MERIOCreater.Panel.Hagrma;

import java.awt.event.MouseEvent;

import MERIOCreater.Panel.Object.carsolObject;
import ObjectEngin.HagrmaSys.MouseKarakuri.MouseHagrma;
import ObjectEngin.HagrmaSys.MouseKarakuri.MouseKarakuri;

public class carsolMouseHagrma extends MouseHagrma{
	public carsolMouseHagrma() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public synchronized void mouseEventListner(int eventName, MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseEventListner(eventName, e);
		if(eventName == MouseKarakuri.Moved || eventName == MouseKarakuri.Dragged){
			this.parent.setLocate(e.getX(), e.getY());
		}
		if(eventName == MouseKarakuri.Clicked || eventName == MouseKarakuri.Dragged){
			if(this.parent instanceof carsolObject){
				((carsolObject)(this.getParent())).paintcell();
			}
		}
	}
	
}
