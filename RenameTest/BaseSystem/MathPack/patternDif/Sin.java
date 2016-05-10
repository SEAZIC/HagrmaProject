package BaseSystem.MathPack.patternDif;

public class Sin {
	private Patterndif pd;
	public Sin(Patterndif pd) {
		this.pd = pd;
	}
	public double Func(double x){
//		System.out.println("Sin");
		double d = Math.sin(pd.Func(x));
//		System.out.println("Sin "+x + "d = "+d+" x = "+pd.Func(x));
		return d;
	}
}
