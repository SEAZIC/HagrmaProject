package ShootingGame.Panels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
import BaseSystem.PanelSystem.BaseFrameWork_Panel;
import MonitaringFrame.Monitor;
import MonitaringFrame.MonitorKarakri;
import ObjectEngin.ObjectManager;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravKarakri;
import ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri.HomingKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.MapKarakuri.MapKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveKarakuri;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputKarakuri;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.MapReader;
import ObjectEngin.dynamicObj.DynaObjCreater;
import ObjectEngin.dynamicObj.DynaObjReader;
import ShootingGame.BulletHagrma.BulletKarakuri;
import ShootingGame.DynamicObjectManager.ShootingEnemyCreater;
import ShootingGame.DynamicObjectManager.ShootingEnemyManager;
import ShootingGame.DynamicObjectManager.ShootingEnemyReader;
import ShootingGame.GameObject.Myfighter;
import ShootingGame.GameObject.RangeObject;
import ShootingGame.StateHagrma.StateKarakuri;

public class MirrorPanel extends JPanel implements BaseFrameWork_Panel,WindowListener{

	private ObjectManager OM;
	private int[][] map;

	private KeyinputKarakuri KIK;
	
	public MirrorPanel() {
		setOpaque(false);
		OM = new ObjectManager();
		
		KIK = new KeyinputKarakuri();
		MoveKarakuri movekarakuri = new MoveKarakuri();
		StateKarakuri statekarakuri = new StateKarakuri();
		HomingKarakuri homingkarakuri = new HomingKarakuri();
		BulletKarakuri bulletkarakuri = new BulletKarakuri();
		CollKarakuri collkarakuri = new CollKarakuri();
		
		OM.addKarakuri(KIK);
		OM.addKarakuri(movekarakuri);
		OM.addKarakuri(statekarakuri);
		OM.addKarakuri(homingkarakuri);
		OM.addKarakuri(bulletkarakuri);
		OM.addKarakuri(collkarakuri);
		
		String[] S = new String[]{"","Enemy1","myfighter","bullet"};
		boolean[][] b = new boolean[][]{
		{false,false,false,false},
		{false,false,false,false},
		{false,false,false,false},
		{false,true,true,false}
		};
		collkarakuri.setTagMap(S, b);
		ShootingEnemyCreater shootingenemycreater = new ShootingEnemyCreater();
		shootingenemycreater.setCreate(OM, new ShootingEnemyManager(), 1, new ShootingEnemyReader("Res"+File.separator+"data"+File.separator+"shootingGame"+File.separator+"stage1"+File.separator+"enemyCreate"+File.separator+"enemy.txt"));
		
		OM.addObjct(new Myfighter());
		
	}

	@Override
	public void setParent(BaseFrameWork_Frame parent) {
		if(parent instanceof JFrame){
			Rectangle screen = new Rectangle(((JFrame) parent).getX(),((JFrame) parent).getY(), 400, 640);
//			setBounds(0, 0,((JFrame)parent).getWidth(), ((JFrame)parent).getHeight());
			setBounds(screen);
			((JFrame) parent).setBounds(screen);
			RangeObject rangeObj = new RangeObject();
			rangeObj.setBoundBox(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
			OM.addObjct(rangeObj);
			OM.setOutRange(rangeObj.getBoundBox());
			((JFrame)parent).revalidate();
			((JFrame)parent).addKeyListener(KIK);
			((JFrame) parent).addWindowListener(this);
			((JFrame)parent).requestFocusInWindow();
		}
		
	}

	@Override
	public void panelrun() {
		OM.running();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		OM.show(g);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		OM.shutdown();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
