package MERIOCreater.Panel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
import BaseSystem.PanelSystem.BaseFrameWork_Panel;
import BaseSystem.TextEdit.TextReader;
import MERIOCreater.Panel.Object.BlockInspectorObject;
import MERIOCreater.Panel.Object.carsolObject;
import MERIOCreater.subPanel.SubPanel;
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
import ObjectEngin.HagrmaSys.CameraKarakuri.RangeHagrma;
import ObjectEngin.HagrmaSys.CameraKarakuri.TargetHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravHagrma;
import ObjectEngin.HagrmaSys.ComonKarakuri.GravKarakuri.GravKarakri;
import ObjectEngin.HagrmaSys.ComonKarakuri.HomingKarakuri.HomingKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.MapKarakuri.MapKarakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.MoveKarakuri.MoveKarakuri;
import ObjectEngin.HagrmaSys.KeyInputKarakuri.KeyinputKarakuri;
import ObjectEngin.HagrmaSys.MouseKarakuri.MouseKarakuri;
import ObjectEngin.MapTool.MapClass;
import ObjectEngin.MapTool.MapReader;
import ObjectEngin.dynamicObj.DynaObjCreater;

public class merioCreator extends JPanel implements BaseFrameWork_Panel,WindowListener{

	private MapClass MC;
	private ObjectManager OM;
	private int[][] map;
	
	private KeyinputKarakuri KIK;
	private MouseKarakuri MK;
	private carsolObject carsol;
	
	private JFrame subpanelframe;
	private SubPanel subpanel;
	public merioCreator() {
//		subpanel = new SubPanel();

		int chips = 24;
		Point world_pos = new Point(0, 0);
		CustomTrrigerAction CTA = new CustomTrrigerAction();
		MC = new MapClass("Res"+TextReader.resocePath+"data"+TextReader.resocePath+"stage1"+TextReader.resocePath+"map2.txt",CTA);

		OM = new ObjectManager();
		
		carsol = new carsolObject();
		carsol.setBoundBox(new Rectangle(chips-1, chips-1, chips+2, chips+2));
		carsol.setPos_ChipSize(world_pos, chips);
		carsol.setMC(MC);

		BlockInspectorObject BIO;
		BIO = new BlockInspectorObject();
		carsol.addChiled(BIO);
		
		OM.addObjct(MC);
		OM.addObjct(carsol);
		MC.chengeChipsize(chips);
		MC.addHagrma(new RangeHagrma());
		
		BIO.setMC(MC);
		
		MK = new MouseKarakuri();
		
		KIK = new KeyinputKarakuri();
		
		OM.addKarakuri(MK);
		OM.addKarakuri(KIK);
		
		this.addMouseListener(MK);
		this.addMouseWheelListener(MK);
		this.addMouseMotionListener(MK);
		
		this.setOpaque(true);
		
		subpanelframe = new JFrame("パレット");
//		subpanelframe.setUndecorated(true);
		subpanel = new SubPanel();
		subpanel.setParent(subpanelframe);
		subpanel.setCSpbject(carsol);
		
		subpanelframe.add(subpanel);
		subpanelframe.setVisible(true);
		
		subpanelframe.addMouseMotionListener(subpanel);
		subpanelframe.addMouseListener(subpanel);
	}
	public void setChipsizeChenge(int size){
		carsol.setChipsize(size);
		MC.chengeChipsize(size);
	}
	@Override
	public void setParent(BaseFrameWork_Frame parent) {
		if(parent instanceof JFrame){
			setBounds(0, 0,(int)(((JFrame)parent).getWidth()), (int)(((JFrame)parent).getHeight()));
			subpanelframe.setBounds(0, 0,(int)((((JFrame)parent).getWidth())*0.2), (int)(((JFrame)parent).getHeight()));
			subpanel.setBounds(subpanelframe.getBounds());
			subpanel.setMapchips(MC.getMaps());
//			subpanel.setBounds((int)(((JFrame)parent).getWidth()*0.8), (int)(((JFrame)parent).getHeight()*0.8),(int)(((JFrame)parent).getWidth()*0.2), (int)(((JFrame)parent).getHeight()*0.2));
//			((JFrame) parent).add(subpanel);
			((JFrame)parent).revalidate();
			((JFrame) parent).addWindowListener(this);
			((JFrame) parent).addKeyListener(KIK);
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
		subpanelframe.dispose();
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
