package MERIOGame.Sample.TestKarakuri;

import java.util.LinkedList;

import ObjectEngin.ObjectManager;
import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;

public class TestKarakuri extends Karakuri{

	LinkedList<Hagrma> hagrmas;
	
	@Override
	public void setObjM(ObjectManager objM) {
		// TODO Auto-generated method stub
		super.setObjM(objM);
	}
	
	public void setHagrmas() {
		
	}
	
	@Override
	public String call() {
		
		hagrmas = objM.getHagrmas(TestHagrma.class);
		if(!hagrmas.isEmpty()){
			for(int cnt = 0;cnt < hagrmas.size();cnt++){
				if(hagrmas.get(cnt) instanceof TestHagrma){
					((TestHagrma)(hagrmas.get(cnt))).getParent().destroy();
				}
			}
			hagrmas.removeAll(hagrmas);
		}
		return null;
	}
	
}
