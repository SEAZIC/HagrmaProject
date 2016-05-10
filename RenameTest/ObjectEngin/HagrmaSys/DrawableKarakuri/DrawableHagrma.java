package ObjectEngin.HagrmaSys.DrawableKarakuri;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import BaseSystem.ImageTool.ImageServer;
import ObjectEngin.ComonObject.BaseObject;
import ObjectEngin.HagrmaSys.Hagrma;

public class DrawableHagrma extends Hagrma {

    private BufferedImage image;
//	private String Imag;

    private int sizewidth;
    private int sizeheight;

    private boolean setsize;

    public DrawableHagrma() {
        super();
        image = null;
        setName("drawhagrma");
    }

    public void Init(String S) {
        String[] split = S.split(",");
        int splitlength = split.length;
        if (splitlength > 4) {
            splitlength = 4;
        }
        String imagename = "" + this.getClass().getName();
        switch (splitlength) {
            case 4:
                setSize(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
//			setSetsize(true);
            case 3:
            case 2:
                imagename = split[1];
            case 1:
                String filename = split[0];
                setImage(imagename, filename);
                break;

            default:

                break;
        }
    }

    public void setImage(String imagename, String uri) {
        ImageServer IS = new ImageServer();
        IS.setdefault("Res/data");
        IS.setOverrite(false);
//		IS.setAutorename(true);
        String S = IS.createImage(imagename, uri);
        setImage(IS.getImage(S));

        IS = null;
    }

    @Override
    public void setParent(BaseObject parent) {
        super.setParent(parent);
        if (!isSetsize()) {
            setSize((int) parent.getBoundBox().getWidth(), (int) parent.getBoundBox().getHeight());
        }
    }

    public void setSize(int width, int height) {
        setSetsize(true);
        sizewidth = width;
        sizeheight = height;
    }

    public void setSetsize(boolean setsize) {
        this.setsize = setsize;
    }

    public boolean isSetsize() {
        return setsize;
    }

    public void show(Graphics g) {
//		System.out.println("draw!");
        if (image != null) {
            g.drawImage(image, (int) (parent.getBoundBox().getCenterX() - (sizewidth / 2)), (int) (parent.getBoundBox().getCenterY() - (sizeheight / 2)),
                    sizewidth, sizeheight, null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    private void setImage(BufferedImage image) {
        this.image = image;
        if (image == null) {
            System.out.println("画像がない");
        }
    }
}
