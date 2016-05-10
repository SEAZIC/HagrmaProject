package MERIOGame.Panels;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
import BaseSystem.PanelSystem.BaseFrameWork_Panel;
import MERIOGame.Sample.TestObj;
import MERIOGame.Sample.TestObj2;
import MERIOGame.Sample.DynamicObjectManager.DynamicObjectMap;
import MERIOGame.Sample.EnemySet.TestEnemy2;
import MERIOGame.Sample.EnemySet.TestEnemy3;
import MERIOGame.Sample.TestKarakuri.TestHagrma;
import MERIOGame.Sample.TestKarakuri.TestKarakuri;
import MERIOGame.Sample.maptool.CustomTrrigerAction;
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

public class merioPanel extends JPanel implements BaseFrameWork_Panel,WindowListener{

	private MapClass MC;
	private ObjectManager OM;
	private int[][] map;

	private KeyinputKarakuri KIK;
	
	public merioPanel() {
		CustomTrrigerAction CTA = new CustomTrrigerAction();
		MC = new MapClass("Res"+File.separator+"data"+File.separator+"stage1"+File.separator+"map2.txt",CTA);
		map = MC.getIntmaps();
		for(int cntx = 0;cntx < map.length;cntx++){
			for(int cnty = 0;cnty < map[cntx].length;cnty++){
				System.out.print(""+map[cntx][cnty]);
			}
			System.out.println("");
		}
		OM = new ObjectManager();
		Monitor M = new Monitor();
		TestObj TO =new TestObj();
		TestObj2 TO2 = new TestObj2();
		M.setObject(TO);
		
		MapKarakuri MK = new MapKarakuri();
		GravKarakri GK = new GravKarakri();
		MonitorKarakri MonitorK = new MonitorKarakri();
		CollKarakuri colK = new CollKarakuri();
		KIK = new KeyinputKarakuri();
		
		MonitorK.setMonitor(M);
		
		CollionHagrma Colhag = new CollionHagrma();
		Colhag.setTag("player");
		TO.addHagrma(Colhag);
		TO2.addHagrma(new CollionHagrma());

		OM.addKarakuri(new MoveKarakuri());
		OM.addKarakuri(new HomingKarakuri());
		OM.addKarakuri(MK);
		OM.addKarakuri(GK);
		OM.addKarakuri(KIK);
		OM.addKarakuri(MonitorK);
		OM.addKarakuri(colK);
		
		OM.addObjct(MC);
		OM.addObjct(TO);
//		OM.addObjct(TO2);
		TestEnemy2 copy;
//		OM.addObjct(copy = new TestEnemy2());
//		OM.addObjct(new TestEnemy3(copy));
		DynaObjCreater DOC = new DynaObjCreater();
//		DOC.setCreate(OM, new DynamicObjectMap(), MC,"Res"+File.separator+"data"+File.separator+"stage1"+File.separator+"EnemySet"+File.separator+"enemy.txt");
	}

	@Override
	public void setParent(BaseFrameWork_Frame parent) {
		if(parent instanceof JFrame){
			setBounds(0, 0,((JFrame)parent).getWidth(), ((JFrame)parent).getHeight());
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
