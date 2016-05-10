package BaseSystem.MathPack.patternDif;

import java.util.ArrayList;

public class Token {

	private double param;
	
	private ArrayList<Dim> paramdim;
	
	private ArrayList<Patterndif> f;
	private ArrayList<Dim> fdim;

	private ArrayList<Dim> vardim;

	private ArrayList<Sin> sin;
	private ArrayList<Dim> sindim;

	private ArrayList<Cos> cos;
	private ArrayList<Dim> cosdim;
	
	private ArrayList<Tan> tan;
	private ArrayList<Dim> tandim;

	private ArrayList<Log> log;
	private ArrayList<Dim> logdim;
	
	

	private Dim alldim;
	
	private boolean endflag;

	public Token() {
		param = 1.0;
		
		paramdim = new ArrayList<Dim>();
		
		endflag = true;
		f = new ArrayList<Patterndif>();
		fdim = new ArrayList<Dim>();

		vardim = new ArrayList<Dim>();

		sin = new ArrayList<Sin>();
		sindim = new ArrayList<Dim>();

		cos = new ArrayList<Cos>();
		cosdim = new ArrayList<Dim>();
		
		tan = new ArrayList<Tan>();
		tandim = new ArrayList<Dim>();
		
		log = new ArrayList<Log>();
		logdim = new ArrayList<Dim>();
		
		alldim = new Dim();
	}
	public double getParam() {
		return param;
	}
	public void addParam(double param) {
		this.param *= param;
	}
	public void addParamdim(Dim dim){
		paramdim.add(dim);
	}
	public void addVardim(Dim dim) {
		vardim.add(dim);
	}
	public void addF(Patterndif pd,Dim dim){
		f.add(pd);
		fdim.add(dim);
	}
	public void addSin(Sin s,Dim dim){
		sin.add(s);
		sindim.add(dim);
	}
	public void addCos(Cos c,Dim dim){
		cos.add(c);
		cosdim.add(dim);
	}

	public void addTan(Tan t,Dim dim){
		tan.add(t);
		tandim.add(dim);
	}

	public void addLog(Log l,Dim dim){
		log.add(l);
		logdim.add(dim);
	}
	public double Func(double x){
		double d = 1.0;
		d *= param;
		if(vardim.size() > 0){
			for(int cnt = 0;cnt < vardim.size();cnt++){
				d *= xxx(x,vardim.get(cnt).getdim(x));
			}
		}
		if(sin.size() > 0)
			for(int cnt = 0;cnt < sin.size();cnt++){
				d *= xxx(sin.get(cnt).Func(x),sindim.get(cnt).getdim(x));
			}
		if(cos.size() > 0)
			for(int cnt = 0;cnt < cos.size();cnt++){
				d *= xxx(cos.get(cnt).Func(x),cosdim.get(cnt).getdim(x));
			}
		if(tan.size() > 0)
			for(int cnt = 0;cnt < tan.size();cnt++){
				d *= xxx(tan.get(cnt).Func(x),tandim.get(cnt).getdim(x));
			}
		if(log.size() > 0)
			for(int cnt = 0;cnt < log.size();cnt++){
				d *= xxx(log.get(cnt).Func(x),logdim.get(cnt).getdim(x));
			}
		
		if(f.size() > 0)
			for(int cnt = 0;cnt < f.size();cnt++){
				d *= xxx(f.get(cnt).Func(x),fdim.get(cnt).getdim(x));
			}
		
		d = xxx(d,alldim.getdim(x));
		return d;
	}

	private double xxx(double d,double Index){
//		if(Index == 0){
//			System.out.println("???");
//			return 1.0;
//		}
//		double result = 1.0; 
//		for(int cnt = 0;cnt < Index;cnt++){
//			result *= d;
//		}
		double result = Math.pow(d, Index);
		return result;
	}
	public Dim getAlldim() {
		return alldim;
	}
	public void setAlldim(Dim alldim) {
		this.alldim = alldim;
	}
	public ArrayList<Patterndif> getF() {
		return f;
	}
	public void setF(ArrayList<Patterndif> f) {
		this.f = f;
	}
	public ArrayList<Dim> getFdim() {
		return fdim;
	}
	public void setFdim(ArrayList<Dim> fdim) {
		this.fdim = fdim;
	}
	public ArrayList<Dim> getVardim() {
		return vardim;
	}
	public void setVardim(ArrayList<Dim> vardim) {
		this.vardim = vardim;
	}
	public ArrayList<Sin> getSin() {
		return sin;
	}
	public void setSin(ArrayList<Sin> sin) {
		this.sin = sin;
	}
	public ArrayList<Dim> getSindim() {
		return sindim;
	}
	public void setSindim(ArrayList<Dim> sindim) {
		this.sindim = sindim;
	}
	public ArrayList<Cos> getCos() {
		return cos;
	}
	public void setCos(ArrayList<Cos> cos) {
		this.cos = cos;
	}
	public ArrayList<Dim> getCosdim() {
		return cosdim;
	}
	public void setCosdim(ArrayList<Dim> cosdim) {
		this.cosdim = cosdim;
	}
	public ArrayList<Tan> getTan() {
		return tan;
	}
	public void setTan(ArrayList<Tan> tan) {
		this.tan = tan;
	}
	public ArrayList<Dim> getTandim() {
		return tandim;
	}
	public void setTandim(ArrayList<Dim> tandim) {
		this.tandim = tandim;
	}
	public ArrayList<Log> getLog() {
		return log;
	}
	public void setLog(ArrayList<Log> log) {
		this.log = log;
	}
	public ArrayList<Dim> getLogdim() {
		return logdim;
	}
	public void setLogdim(ArrayList<Dim> logdim) {
		this.logdim = logdim;
	}
}
