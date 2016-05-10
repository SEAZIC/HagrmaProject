package BaseSystem.TextEdit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;

/** テキストリーダー
 * テキストファイルをメモリ上に保存するクラス
 * データは {@link ArrayList<String>}で保存される*/
public class TextReader {
	private String filename;
	private ArrayList<String> TextData;
	private boolean spacedelete;
	public static char resocePath = '/';
	/** コンストラクタ */
	public TextReader() {
		TextData = new ArrayList<String>();
	}
	/** コンストラクタ
	 * 読み込むファイルを指定してその場で読み込む
	 * @param fileName String ファイル名*/
	public TextReader(String fileName){
		this();
		this.filename = fileName;
		readFile(fileName);
	}
	/** ファイルリードメソッド
	 * ファイルを読み込む
	 * @param fileName String ファイル名*/
	public void readFile(String filename){
		System.out.println(filename);
		filename  =filename.replace(File.separatorChar, resocePath);
//		System.out.println(getClass().getClassLoader().getResource(filename).toString());
		JLabel jl = new JLabel();
		if(getClass().getClassLoader().getResource(filename) != null){
			try {
				InputStreamReader ISR = new InputStreamReader(getClass().getClassLoader().getResource(filename).openStream());
				BufferedReader br = new BufferedReader(ISR);

				String str;
				TextData = new ArrayList<String>();
				try {
					while((str = br.readLine()) != null){
						//					if(spacedelete){
						//						System.out.println("repsp");
						//						str.replaceAll(" | ","");
						//					}
						TextData.add(str);
						System.out.println(str);
					}
					br.close();
					ISR.close();
				} catch (IOException e) {
					System.out.println("IOException!?");
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			filename  =filename.replace(resocePath,File.separatorChar);
			InputStreamReader ISR;
			try {
				ISR = new FileReader(new File(filename));
				BufferedReader br = new BufferedReader(ISR);

				String str;
				TextData = new ArrayList<String>();
				while((str = br.readLine()) != null){
					//					if(spacedelete){
					//						System.out.println("repsp");
					//						str.replaceAll(" | ","");
					//					}
					TextData.add(str);
					System.out.println(str);
				}
				br.close();
				ISR.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * テキストデータ取得メソッド
	 * テキストデータを取得します
	 *  */
	public ArrayList<String> getTextData() {
		return TextData;
	}
	/**テキスト読み取り確認
	 *  テキストがあるかないかを確認するメソッド*/
	public boolean isTextFound(){
		if(TextData.isEmpty() || TextData.size() == 0){
			return false;
		}
		return true;
	}
	@Deprecated
	/**作成中なの，ごめんね️ */
	public void setSpacedelete(boolean spacedelete) {
		this.spacedelete = spacedelete;
	}
}
