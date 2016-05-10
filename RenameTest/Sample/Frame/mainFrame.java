package Sample.Frame;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BaseSystem.FrameSystem.BaseFrameWork_Frame;
import BaseSystem.PanelSystem.BaseFrameWork_Panel;
import BaseSystem.maintools.MessagePack;
import Sample.Panel.mainPanel;

public class mainFrame extends JFrame implements BaseFrameWork_Frame {

    String FrameName;
    BaseFrameWork_Panel MainPanel;
    MessagePack MP;

    public mainFrame() {
        // TODO Auto-generated constructor stub
        setVisible(true);
        setTitle("よこすくろーる");
        setBounds(100, 100, 640, 480);
        FrameName = "main";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel MainPanel = new mainPanel(this);
        this.MainPanel = MainPanel;
        getContentPane().add(MainPanel);

    }

    public void run() {

        MainPanel.panelrun();
    }

    @Override
    public MessagePack getmessage() {
        // TODO Auto-generated method stub
        return MP;
    }

    @Override
    public void setmessage(MessagePack param) {
        if (param.getMeta_A().equalsIgnoreCase("FrameNameSet")) {
            if (param.getPresent() instanceof String[]) {
            }
        }
        if (param.getMeta_A().equalsIgnoreCase("Chiled_Change")) {
            if (param.getPresent() instanceof JPanel) {
                PanelChenge((JPanel) param.getPresent());
            }
        }
    }

    private void PanelChenge(JPanel panel) {
        if (MainPanel instanceof KeyListener) {
            KeyListener KL = (KeyListener) MainPanel;
            this.removeKeyListener(KL);
        }
        this.remove((JPanel) MainPanel);
        if (panel instanceof BaseFrameWork_Panel) {
            MainPanel = (BaseFrameWork_Panel) panel;
            MainPanel.setParent(this);
        }
        if (MainPanel instanceof JPanel) {
            this.add((JPanel) MainPanel);
        }
        this.revalidate();
    }

    @Override
    public void setFrameName(String S) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeMessage() {
        // TODO Auto-generated method stub
        MP = null;
    }

    @Override
    public String getFrameName() {
        // TODO Auto-generated method stub
        return FrameName;
    }

    public void getChain(MessagePack MP) {
        this.MP = MP;
    }

}
