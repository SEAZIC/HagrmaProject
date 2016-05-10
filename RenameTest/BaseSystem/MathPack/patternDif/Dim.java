package BaseSystem.MathPack.patternDif;

public class Dim {

	private boolean natural;
	private double dim;
	private Patterndif p;
	public Dim() {
		dim = 1;
		natural = true;
	}
	public void setP(Patterndif p) {
		this.p = p;
		natural = false;
	}
	public void setDim(double dim) {
		this.dim = dim;
		natural = true;
	}
	public void addDim(double dim) {
		this.dim *= dim;
	}
	public double getdim(double d){
		if(natural)
			return dim;
		else
			return dim*p.Func(d);
	}
}
