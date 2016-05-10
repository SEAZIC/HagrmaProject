package BaseSystem.MathPack;

public class Vec2 {
	private float x;
	private float y;
	public Vec2(float x,float y){
		this.setX(x);
		this.setY(y);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setXY(float x,float y){
		setX(x);
		setY(y);
	}
	public float getlength(){
		return (float) Math.sqrt((x*x)+(y*y));
	}
	public Vec2 nomarized(){
		float leng = getlength();
		if(leng < 0.001)
			leng = 1.0f;
		return new Vec2(x/leng, y/leng);
	}
	public boolean iserror(){
		if(x == 0.0 && y == 0.0)
			return true;
		return false;
	}
}
