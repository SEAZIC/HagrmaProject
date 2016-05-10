package BaseSystem.maintools;

import java.util.concurrent.ExecutorService;

//import Sample.Frame.mainFrame;

public class DefaultGameMainOption {

	private GameEngin GE;
	private EngineListener EL;
	private boolean memo;
	public DefaultGameMainOption(GameEngin GE) {
		this.GE = GE;
		EL = new EngineListener();
	}
	public void init(){
//		GE.createFrame(new mainFrame());
		memo = true;
	}
	public boolean memoricheck(){
		return memo;
	}
	public EngineListener getEL() {
		return EL;
	}
	public void setES(ExecutorService executor) {
		EL.setES(executor);
		GE.setEL(EL);
	}
	public GameEngin getGE() {
		return GE;
	}
}
