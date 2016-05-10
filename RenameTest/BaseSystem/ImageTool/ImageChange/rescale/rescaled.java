package BaseSystem.ImageTool.ImageChange.rescale;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

public class rescaled {

    static Color DefaltColor = new Color(0, 0, 0);

    public static BufferedImage createBufferedImage(Image img) {
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics g = bimg.getGraphics();

        g.setColor(DefaltColor);
        g.fillRect(0, 0, img.getWidth(null) - 1, img.getHeight(null) - 1);
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return bimg;
    }

    public static void ClearingColor(Color img) {
        DefaltColor = img;
    }

    public static BufferedImage ClearColor(BufferedImage Bimage, Color clearcolor) {
        if (clearcolor == null) {
            return Bimage;
        }

        BufferedImage copyBimage = new BufferedImage(Bimage.getWidth(), Bimage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int w = Bimage.getWidth();     // Image の幅
        int h = Bimage.getHeight();    // Image の高さ
        int t = clearcolor.getRGB();         // 透明色に変換する色のRGB値
        t &= 0x00ffffff;
        int Gcolor;
        //RGB値を0(透明色)に置換
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                copyBimage.setRGB(x, y, Bimage.getRGB(x, y));
                Gcolor = Bimage.getRGB(x, y);
                Gcolor &= 0x00ffffff;
                if (Gcolor == t) {
                    copyBimage.setRGB(x, y, 0);
                }
            }
        }

        return copyBimage;
    }

    public static BufferedImage rescale(BufferedImage srcImage, int nw, int nh) {
        BufferedImage dstImage = null;
        if (srcImage.getColorModel() instanceof IndexColorModel) {
            dstImage = new BufferedImage(nw, nh, srcImage.getType(), (IndexColorModel) srcImage.getColorModel());
        } else if (srcImage.getType() == 0) {
            dstImage = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        } else {
            dstImage = new BufferedImage(nw, nh, srcImage.getType());
        }

        double sx = (double) nw / srcImage.getWidth();
        double sy = (double) nh / srcImage.getHeight();
        AffineTransform trans = AffineTransform.getScaleInstance(sx, sy);

        if (dstImage.getColorModel().hasAlpha() && dstImage.getColorModel() instanceof IndexColorModel) {
            int transparentPixel = ((IndexColorModel) dstImage.getColorModel()).getTransparentPixel();
            for (int i = 0; i < dstImage.getWidth(); ++i) {
                for (int j = 0; j < dstImage.getHeight(); ++j) {
                    dstImage.setRGB(i, j, transparentPixel);
                }
            }
        }

        Graphics2D g2 = (Graphics2D) dstImage.createGraphics();
        g2.drawImage(srcImage, trans, null);
        g2.dispose();

        return dstImage;
    }
}
