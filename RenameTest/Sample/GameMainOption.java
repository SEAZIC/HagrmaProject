package Sample;

import BaseSystem.maintools.DefaultGameMainOption;
import BaseSystem.maintools.GameEngin;
import Sample.Frame.mainFrame;

public class GameMainOption extends DefaultGameMainOption{
//	GameEngin GE;
	private boolean memo;
	public GameMainOption(GameEngin GE) {
//		this.GE = GE;
		super(GE);
	}
	public void init(){
		getGE().createFrame(new mainFrame());
		memo = true;
	}
	public boolean memoricheck(){
		return memo;
	}
	
}
