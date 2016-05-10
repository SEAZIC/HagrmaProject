package MERIOCreater.subPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MERIOCreater.Panel.Object.carsolObject;
import ObjectEngin.MapTool.Mapchip;

public class SubPanel extends JPanel implements MouseMotionListener,MouseListener{

	private carsolObject CSpbject;
	private Mapchip[] mapchips;

	private ArrayList<PalletCell<Mapchip>> palCell;

	private Point pos;

	private int max_y;
	private int min_y;

	private int cellsize;

	private JFrame inspector;

	private JFrame parent;

	private Point carsol;
	private Point onpalletcarsol;
	private int carsolIndex;

	private boolean onpallet;
	private boolean selectedpallet;

	public SubPanel() {

		pos = new Point(0, 0);
		palCell = new ArrayList<PalletCell<Mapchip>>();
		min_y = 0;
		max_y = 0;
		cellsize = 20;

		carsolIndex = 0;
		
		carsol = new Point(0,0);
		onpalletcarsol = new Point(0, 0);

		inspector = new JFrame();
		inspector.setUndecorated(true);

		inspector.setBounds(0, 0, 20, 100);
		inspector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inspector.setAlwaysOnTop(true);
		onpallet = false;
		selectedpallet = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(mapchips != null)
			for(PalletCell<Mapchip> pal : palCell){
				g.drawImage(pal.getStockClass().getImage(), pal.getPos().x, pal.getPos().y, pal.getSize().x, pal.getSize().y, null);
			}
		g.setColor(Color.GREEN);
		g.drawRect(carsol.x*(cellsize+2), carsol.y*(cellsize+2), cellsize+2, cellsize+2);
		if(selectedpallet){
			g.setColor(Color.BLUE);
			g.drawRect(onpalletcarsol.x*(cellsize+2), onpalletcarsol.y*(cellsize+2), cellsize+2, cellsize+2);
		}

	}

	public carsolObject getCSpbject() {
		return CSpbject;
	}

	public void setCSpbject(carsolObject cSpbject) {
		CSpbject = cSpbject;
	}

	public Mapchip[] getMapchips() {
		return mapchips;
	}

	public void setMapchips(Mapchip[] mapchips) {
		this.mapchips = mapchips;
		int x_Index_max = (int) (getBounds().getWidth()/(cellsize+2));
		for(int cnt = 0;cnt < mapchips.length;cnt++){
			PalletCell<Mapchip> setPal = new PalletCell<Mapchip>();
			palCell.add(setPal);
			setPal.setStockClass(mapchips[cnt]);
			setPal.setSize(new Point(cellsize,cellsize));
			setPal.setPos(new Point(1+((palCell.size()-1)%x_Index_max)*(cellsize+2), 1+((palCell.size()-1)/x_Index_max)*(cellsize+2)));

		}

		max_y = (mapchips.length+1) * (cellsize+1);
	}
	public void setParent(JFrame parent) {
		this.parent = parent;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x_Index_max = (int) (getBounds().getWidth()/(cellsize+2));
		int x = (e.getX()/(cellsize+2));
		int y = ((e.getY()-parent.getInsets().top)/(cellsize+2));
		int Index = y*x_Index_max + x;
		System.out.println("egetx = "+e.getX()+" x = "+x+" egety = "+e.getY()+" y = "+y);
		if(x < x_Index_max && Index > -1 && Index < palCell.size()){
			carsolIndex = Index;
			carsol.setLocation(x, y);
			inspector.setLocation(parent.getLocation().x+e.getX()+15, parent.getLocation().y+e.getY()+35);

			if(!inspector.isVisible())
				inspector.setVisible(true);
			onpallet = true;
			repaint();
		}else{
			if(inspector.isVisible())
				inspector.setVisible(false);
			onpallet = false;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(onpallet){
			onpalletcarsol.setLocation(carsol);
			selectedpallet = true;
			CSpbject.setWet(true);
			CSpbject.setSetchip(palCell.get(carsolIndex).getStockClass());
		}else{
			selectedpallet = false;
			CSpbject.setWet(false);
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
