package BaseSystem.MathPack.patternDif;

public class Tan {

	private Patterndif pd;
	public Tan(Patterndif pd) {
		this.pd = pd;
	}
	public double Func(double x){
//		System.out.println("Sin");
		double d = Math.tan(pd.Func(x));
//		System.out.println("Sin "+x + "d = "+d+" x = "+pd.Func(x));
		return d;
	}
}
