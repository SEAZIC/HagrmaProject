package BaseSystem.MathPack.patternDif;

public class Log {

	private Patterndif pd;
	public Log(Patterndif pd) {
		this.pd = pd;
	}
	public double Func(double x){
//		System.out.println("Sin");
		double d = Math.log(pd.Func(x));
//		System.out.println("Sin "+x + "d = "+d+" x = "+pd.Func(x));
		return d;
	}
}
