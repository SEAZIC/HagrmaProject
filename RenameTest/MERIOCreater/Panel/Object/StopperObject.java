package MERIOCreater.Panel.Object;

import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.CameraKarakuri.TargetHagrma;

public class StopperObject extends BaseObject{
	public StopperObject() {
		super();
		TargetHagrma havy= new TargetHagrma();
		havy.setHavy(1000);
		addHagrma(havy);
	}
}
