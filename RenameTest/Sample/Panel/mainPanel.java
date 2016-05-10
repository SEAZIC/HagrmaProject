package Sample.Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
import BaseSystem.ImageTool.ImageServer;
import BaseSystem.MathPack.patternDif.Patterndif;
import BaseSystem.PanelSystem.BaseFrameWork_Panel;
import BaseSystem.maintools.MessagePack;
import MERIOCreater.Panel.merioCreator;
import MERIOGame.Panels.merioPanel;
import ObjectEngin.MapTool.MapReader;
import Sample.Frame.mainFrame;
import ShootingGame.Panels.MirrorPanel;

public class mainPanel extends JPanel implements ActionListener, BaseFrameWork_Panel {

    private ImageServer IS;
    private JButton start;
    private JButton stop;

    private BaseFrameWork_Frame parent;

    public mainPanel(mainFrame mainFrame) {
        parent = mainFrame;
        System.out.println("" + mainFrame.getHeight());

        setLayout(null);
        setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
//		IS = new ImageSever();
//		IS.setdefault("appClientModule");
//		IS.createImage("たいとる", "title.jpg");
        start = new JButton("Start");
        start.setBounds(mainFrame.getWidth() / 2 - 50, mainFrame.getHeight() / 2 - 10, 100, 20);
        start.addActionListener(this);
        stop = new JButton("stop");
        stop.setBounds(mainFrame.getWidth() / 2 - 50, mainFrame.getHeight() / 2 + 20, 100, 20);
        stop.addActionListener(this);
        this.add(start);
        this.add(stop);
        ArrayList<String> list = new ArrayList<String>();
        for (int cnt = 0; cnt < 10; cnt++) {
            list.add("" + (char) (48 + cnt));
        }
        setBackground(Color.black);
    }

    public void panelrun() {

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(start)) {
            System.out.println("すたーと");
            MessagePack AMP = new MessagePack();
            AMP.setMeta_A("Chiled_Change");
//			AMP.setPresent(new MirrorPanel());
            AMP.setPresent(new merioPanel());
//			AMP.setPresent(new merioCreator());

            this.parent.setmessage(AMP);
        }
        if (e.getSource().equals(stop)) {
        }
    }

    public void setParent(BaseFrameWork_Frame parent) {
        this.parent = parent;

    }
}
