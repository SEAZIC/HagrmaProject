package BaseSystem.ImageTool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <PRE>
 *  イメージサーバー
 * ロードしたイメージを保管する
 * 多重ロードの防止と簡易的な画像のロードを提供する
 * 画像はstaticで保存されるため，１度ロードしておけばどこからでも画像を使用できる
 * デフォルトルートの設定などはインスタンスごとに設定
 * </PRE>
 */
public class ImageServer {

    private String defaultroot;
    private String FS = File.separator;
    public static String resocePath = "/";
    private static HashMap<String, BufferedImage> SeverImage;
    private boolean overwrite;
    private boolean autorename;

    /**
     * コンストラクタ 初期化
     */
    public ImageServer() {
        // TODO Auto-generated constructor stub
        if (SeverImage == null) {
            SeverImage = new HashMap<String, BufferedImage>();
        }
        defaultroot = "img";
        overwrite = false;
        setAutorename(false);
    }

    /**
     * <
     * PRE>
     * デフォルトルートの設定
     * </PRE>
     */
    public void setdefault(String root) {
        defaultroot = root;
    }

    /**
     * <PRE>
     * イメージのロード
     * @param settingname String ファイルを呼び出すときのキー値
     * @param FileName String ファイルの名前
     * @return String キー値
     * </PRE>
     */
    public String createImage(String settingname, String FileName) {
//
//		System.out.println(getClass().getClassLoader().getResource(defaultroot+resocePath+FileName));
//		
        BufferedImage bi;
        if (getClass().getClassLoader().getResource(defaultroot + resocePath + FileName) != null) {
            bi = openImageFile(getClass().getClassLoader().getResource(defaultroot + resocePath + FileName));
        } else {
            bi = openImageFile(new File(defaultroot + FS + FileName));
        }
//		System.out.println((bi != null)+defaultroot+resocePath+FileName);
        return createImage(settingname, bi);
    }

    /**
     * <PRE>
     * イメージのロード
     * @param settingname ファイルを呼び出すときのキー値
     * @param image ファイルのイメージ
     * @return String キー値
     * </PRE>
     */
    public String createImage(String settingname, BufferedImage image) {
        String returnstring = "";
        if (!overwrite && SeverImage.containsKey(settingname)) {
            if (isAutorename()) {
                String[] s = settingname.split("_");
                int num;
                if (s.length > 1) {
                    num = Integer.parseInt(s[1]) + 1;
                } else {
                    num = 0;
                }
                returnstring = s[0] + "_" + num;
                return createImage(returnstring, image);
            } else {
                return settingname;
            }
        }
        returnstring = settingname;
        SeverImage.put(settingname, image);
        return returnstring;
    }

    /**
     * <
     * PRE>
     * イメージのロード
     * @param 画像のファイル名
     * @return イメージファイル
     * </PRE>
     */
    public BufferedImage openImageFile(File file) {

        BufferedImage i = null;
        Image img = new ImageIcon(file.getPath()).getImage();
        if (img.getWidth(null) > 0) {
            i = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        } else {
            return null;
        }
        Graphics g = i.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return i;
    }

    /**
     * <PRE>
     * イメージのロード
     * @param 画像のファイル名
     * @return イメージファイル
     * </PRE>
     */
    public BufferedImage openImageFile(URL url) {

        BufferedImage i = null;
        Image img = new ImageIcon(url).getImage();
        if (img.getWidth(null) > 0) {
            i = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        } else {
            return null;
        }
        Graphics g = i.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
//		try {
//			i = ImageIO.read(url);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        return i;
    }

    /**
     * <PRE>
     * 画像書き出しメソッド
     * 保存している画像を書き出すためのメソッドで，書き出し先に名前の被りがある場合は自動的に数字番号がふられる
     * @param Filename String 書き出し先のファイル名
     * @param image {@link BufferedImage} 保存する画像
     * @param format String 書き出す拡張子
     * </PRE>
     */
    public void saveImage(String Filename, BufferedImage image, String format) {
        File outputfile = new File(defaultroot + FS + Filename + "." + format);
        int number = 1;
        while (true) {
            if (outputfile.exists()) {
                outputfile = new File(defaultroot + FS + Filename + number + "." + format);
            } else {
                try {
                    outputfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            number++;
        }
        if (outputfile.canWrite()) {
            try {
                OutputStream out = new FileOutputStream(outputfile);//ファイルとアプリを繋ぐ
                ImageIO.write(image, format, out);//指定の形式で出力
                out.close();
            } catch (IOException e) {
                //例外処理
            }
        }
    }

    /**
     * <PRE>
     * 画像書き出しメソッド
     * 保存している画像を書き出すためのメソッドで，書き出し先に名前の被りがある場合は自動的に数字番号がふられる
     * @param Filename String 書き出し先のファイル名
     * @param image String 保存する画像の名前
     * @param format String 書き出す拡張子
     * </PRE>
     */
    public void saveImage(String Filename, String imagename, String format) {
        BufferedImage saveimage = getImage(imagename);
        if (saveimage != null) {
            this.saveImage(Filename, saveimage, format);
        }
    }

    /**
     * <PRE>
     * 画像取り出しメソッド
     * 保存するときにつけた名前をキーにして保存した画像を取得します
     * </PRE>
     */
    public BufferedImage getImage(String S) {
        //		System.out.println(SeverImage.containsKey(S));
        return SeverImage.get(S);
    }

    /**
     * <PRE>
     * 上書き設定確認メソッド
     * @return boolean 上書きがいいならtrue
     * </PRE>
     */
    public boolean isOverrite() {
        return overwrite;
    }

    /**
     * <
     * PRE>
     * 上書き設定
     * @param overrite boolean 上書きをしていいかどうか
     * </PRE>
     */
    public void setOverrite(boolean overrite) {
        this.overwrite = overrite;
    }

    public boolean isAutorename() {
        return autorename;
    }

    public void setAutorename(boolean autorename) {
        this.autorename = autorename;
    }
}
