package BaseSystem.TextEdit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JLabel;

public class TextWriter {

    private String filename;
    private ArrayList<String> TextData;
    public static char resocePath = '/';

    /**
     * コンストラクタ
     */
    public TextWriter() {
        TextData = new ArrayList<String>();
    }

    /**
     * <PRE>
     * コンストラクタ
     * 読み込むファイルを指定してその場で読み込む
     * @param fileName String ファイル名
     * </PRE>
     */
    public TextWriter(String fileName, ArrayList<String> data) {
        this();
        this.filename = fileName;
        writeFile(fileName, data);
    }

    public void writeFile(String fileName, ArrayList<String> data) {
        System.out.println(fileName);
        filename = fileName;

        filename = filename.replace(resocePath, File.separatorChar);
        FileWriter OSW;
        BufferedWriter bw;
        File writefile = new File(filename);
        if (!writefile.exists()) {
            try {
                writefile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Can't create new file!!!");
            }
        } else {
            System.out.println("overwrite!!!!!");
        }
        try {
            OSW = new FileWriter(writefile);
            bw = new BufferedWriter(OSW);

            for (String S : data) {
                bw.write(S);
                bw.newLine();
            }

            bw.close();
            OSW.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
