package BaseSystem.MathPack.patternDif;

public class Cos {
	private Patterndif pd;
	public Cos(Patterndif pd) {
		this.pd = pd;
	}
	public double Func(double x){
		return Math.cos(pd.Func(x));
	}
}
