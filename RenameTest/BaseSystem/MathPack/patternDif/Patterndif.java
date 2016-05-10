package BaseSystem.MathPack.patternDif;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <PRE>
 * 数式クラス
 * 文字列型の数式を計算できる形に直すクラス
 * </PRE>
 */
public class Patterndif {

    private String pattern;
    private String difpattern;
    private int[] param;
    private int[] dim;

    private int[] difparam;
    private int[] difdim;

    private String variable;
    private ArrayList<Token> calced_token;

    private ArrayList<Boolean> pluspt;

    private static final char milvar = '#';
    private static final char sinvar = '~';
    private static final char cosvar = '&';
    private static final char tanvar = '<';
    private static final char logvar = '>';
    private static final char mathvar = '!';

    /**
     * <PRE>
     * コンストラクタ
     * 数式を解析，分解し保存する
     * デフォルト値として変数はxを使用
     * @param S String 数式
     * </PRE>
     */
    public Patterndif(String S) {
        this(S, 'x');
    }

    /**
     * <
     * PRE>
     * コンストラクタ
     * 数式を解析，分解し保存する
     * 一文字の変数を設定できるタイプ
     * @param S String 数式
     * @param C char 指定する変数，一文字であることが条件
     * </PRE>
     */
    public Patterndif(String S, char C) {
        variable = "" + C;
        pattern = S;
        calced_token = new ArrayList<Token>();
        //		difcalc();
        difcalc();
    }

    private void difcalc() {
        ArrayList<String> param = new ArrayList<String>();
        ArrayList<String> dim = new ArrayList<String>();
        String[] litletoken;

        pluspt = new ArrayList<Boolean>();

        LinkedList<String> s = new LinkedList<String>();
        //		String data = pattern.replaceAll("\\(.*?\\)", ""+milvar);
        String data = replaceNest(pattern, "" + milvar, '(', ')', s);
//		System.out.println("kkkk---"+data);

        data = data.replaceAll("sin", "" + sinvar);
        data = data.replaceAll("cos", "" + cosvar);
        data = data.replaceAll("tan", "" + tanvar);
        data = data.replaceAll("log", "" + logvar);

        LinkedList<Double> d = new LinkedList<Double>();
        data = repraceMath(data, "" + mathvar, d);
        ArrayList<Token> tokens = new ArrayList<Token>();
        Token set = new Token();
        for (int cnt = 0; cnt < data.length(); cnt++) {
//			System.out.println(data.charAt(cnt));
            switch (data.charAt(cnt)) {
                case '-':
                    if (cnt != 0) {
                        tokens.add(set);
                        set = new Token();
                        pluspt.add(true);
                    }
                    set.addParam(-1.0);
                    break;
                case '+':
                    if (cnt != 0) {
                        tokens.add(set);
                        set = new Token();
                        pluspt.add(true);
                    }
                    set.addParam(1.0);
                    break;
                case '/':
                    if (cnt != 0) {
                        tokens.add(set);
                        set = new Token();
                        pluspt.add(false);
                    }
                    if (data.charAt(cnt + 1) == '-') {
                        set.addParam(-1.0);
                        cnt++;
                    } else {
                        set.addParam(1.0);
                    }
                    set.getAlldim().addDim(-1.0);
                    break;
                case '*':
                    if (cnt != 0) {
                        tokens.add(set);
                        set = new Token();
                        pluspt.add(false);
                    }
                    if (data.charAt(cnt + 1) == '-') {
                        set.addParam(-1.0);
                        cnt++;
                    } else {
                        set.addParam(1.0);
                    }
                    set.getAlldim().addDim(1.0);
                    break;
                case milvar:
                    Dim funcdim = new Dim();
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                funcdim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                funcdim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                funcdim.addDim(d.removeFirst());
                            }
                            set.addF(new Patterndif(s.removeFirst(), variable.charAt(0)), funcdim);
                            cnt += 2;

                        } else {
                            set.addF(new Patterndif(s.removeFirst(), variable.charAt(0)), funcdim);
                        }

                    } else {
                        set.addF(new Patterndif(s.removeFirst(), variable.charAt(0)), funcdim);
                    }
                    break;
                case sinvar:
                    Dim sindim = new Dim();
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                sindim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                sindim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                sindim.addDim(d.removeFirst());
                            }
                            set.addSin(
                                    new Sin(new Patterndif(s.removeFirst(), variable.charAt(0))), sindim);
                            cnt += 2;
                        } else {
                            set.addSin(new Sin(new Patterndif(s.removeFirst(), variable.charAt(0))), sindim);
                        }

                    } else {
                        set.addSin(new Sin(new Patterndif(s.removeFirst(), variable.charAt(0))), sindim);
                    }
                    cnt++;
                    break;
                case cosvar:
                    Dim cosdim = new Dim();
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                cosdim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                cosdim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                cosdim.addDim(d.removeFirst());
                            }
                            set.addCos(
                                    new Cos(new Patterndif(s.removeFirst(), variable.charAt(0))), cosdim);
                            cnt += 2;
                        } else {
                            set.addCos(new Cos(new Patterndif(s.removeFirst(), variable.charAt(0))), cosdim);
                        }

                    } else {
                        set.addCos(new Cos(new Patterndif(s.removeFirst(), variable.charAt(0))), cosdim);
                    }
                    cnt++;
                    break;
                case tanvar:
                    Dim tandim = new Dim();
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                tandim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                tandim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                tandim.addDim(d.removeFirst());
                            }
                            set.addTan(
                                    new Tan(new Patterndif(s.removeFirst(), variable.charAt(0))), tandim);
                            cnt += 2;
                        } else {
                            set.addTan(new Tan(new Patterndif(s.removeFirst(), variable.charAt(0))), tandim);
                        }

                    } else {
                        set.addTan(new Tan(new Patterndif(s.removeFirst(), variable.charAt(0))), tandim);
                    }
                    cnt++;
                    break;
                case logvar:
                    Dim logdim = new Dim();
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                logdim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                logdim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                logdim.addDim(d.removeFirst());
                            }
                            set.addLog(
                                    new Log(new Patterndif(s.removeFirst(), variable.charAt(0))), logdim);
                            cnt += 2;
                        } else {
                            set.addLog(new Log(new Patterndif(s.removeFirst(), variable.charAt(0))), logdim);
                        }

                    } else {
                        set.addLog(new Log(new Patterndif(s.removeFirst(), variable.charAt(0))), logdim);
                    }
                    cnt++;
                    break;
                case mathvar:
                    Dim mathdim = new Dim();
                    set.addParam(d.removeFirst());
                    if (cnt + 1 < data.length()) {
                        if (data.charAt(cnt + 1) == '^') {
                            if (data.charAt(cnt + 2) == '-') {
                                cnt++;
                                mathdim.setDim(-1.0);
                            }
                            if (data.charAt(cnt + 2) == milvar) {
                                mathdim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                            }
                            if (data.charAt(cnt + 2) == mathvar) {
                                mathdim.addDim(d.removeFirst());
                            }
//						set.addVardim(mathdim);
                            cnt += 2;
                        }
                    }
                    break;
                default:
                    Dim vardim = new Dim();
                    if (data.charAt(cnt) == variable.charAt(0)) {
                        if (cnt + 1 < data.length()) {
                            if (data.charAt(cnt + 1) == '^') {
                                if (data.charAt(cnt + 2) == '-') {
                                    cnt++;
                                    vardim.setDim(-1.0);
                                }
                                if (data.charAt(cnt + 2) == milvar) {
                                    vardim.setP(new Patterndif(s.removeFirst(), variable.charAt(0)));
                                }
                                if (data.charAt(cnt + 2) == mathvar) {
                                    vardim.addDim(d.removeFirst());
                                }
                                set.addVardim(vardim);
                                cnt += 2;
                            } else {
                                set.addVardim(vardim);
                            }
                        } else {
                            set.addVardim(vardim);
                        }
                        break;
                    }
                    int var = 0;
                    boolean errocheck = true;
                    while (true) {
                        int x = (int) data.charAt(cnt);
                        if ((int) '0' <= x && (int) '9' >= x) {
                            if (errocheck) {
                                errocheck = false;
                            } else {
                                var *= 10;
                            }
                            var += x - 48;
                            System.out.println("var = " + var);
                            cnt++;
                            if (cnt < data.length()) {
                            } else {
                                cnt--;
                                break;
                            }
                        } else {
                            if (!errocheck) {
                                cnt--;
                            } else {
                                System.out.println("compError" + data.charAt(cnt));
                            }
                            break;
                        }
                    }
                    set.addParam(var);
            }
        }
        tokens.add(set);
//		for(int cnt = 0;cnt < tokens.size();cnt++){
//			System.out.println("token-----"+cnt);
//		}
//		if(s.isEmpty() && d.isEmpty())
//			System.out.println("Compleate!!!!!!");
        calced_token = tokens;
//		System.out.println("tokensize = "+calced_token.size());
    }

    /**
     * <PRE>
     * 計算メソッド
     * @param x double xに入れた値で計算できる
     * </PRE>
     */
    public double Func(double x) {
        double y = 0.0;
        double tk = 1.0;
        for (int cnt = 0; cnt < calced_token.size(); cnt++) {
            tk *= calced_token.get(cnt).Func(x);
            if (cnt < pluspt.size() && pluspt.get(cnt)) {
                y += tk;
                tk = 1.0;
            }
        }
        if (!calced_token.isEmpty()) {
            y += tk;
        }
        return y;
    }

    private String replaceNest(String oldS, String newS, char s, char e, LinkedList<String> sub) {
        int nest = 0;
        boolean n = false;
        int sIn = 0;
        int eIn = 0;
        ArrayList<String> substring = new ArrayList<String>();
        for (int cnt = 0; cnt < oldS.length(); cnt++) {
            if (oldS.charAt(cnt) == s) {
                nest++;
                if (nest == 1) {
                    if (sIn != eIn) {
                        substring.add(oldS.substring(sIn, eIn));
                    }
                    sIn = cnt + 1;
                    n = true;
                }
            }
            if (oldS.charAt(cnt) == e) {
                nest--;

                if (nest == 0) {
                    if (sIn != cnt) {
                        sub.add(oldS.substring(sIn, eIn));
//						System.out.println("sub  "+sub.getLast());
                        substring.add(newS);
                    }
                    sIn = cnt + 1;
                    //					eIn = cnt+1;
                    n = false;
                }
            }
            if (cnt == oldS.length() - 1) {
                if (sIn <= eIn) {
                    substring.add(oldS.substring(sIn, eIn + 1));
                }
            } else {

                eIn++;
            }
        }
        String result = "";
        for (int cnt = 0; cnt < substring.size(); cnt++) {
            result += substring.get(cnt);
        }
//		System.out.println("result "+result);
        return result;
    }

    private String repraceMath(String oldS, String newS, LinkedList<Double> d) {
        Pattern mathP = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        Matcher M = mathP.matcher(oldS);
        while (M.find()) {
            d.add(Double.parseDouble(M.group()));
        }

        return oldS.replaceAll("[0-9]+(\\.[0-9]+)?", newS);
    }
}
