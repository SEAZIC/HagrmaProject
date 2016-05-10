package MERIOCreater.subPanel;

import java.awt.Point;

public class PalletCell <T>{
	private T stockClass;
	private Point pos;
	private Point size;
	public T getStockClass() {
		return stockClass;
	}
	public void setStockClass(T stockClass) {
		this.stockClass = stockClass;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public Point getSize() {
		return size;
	}
	public void setSize(Point size) {
		this.size = size;
	}
	
}
