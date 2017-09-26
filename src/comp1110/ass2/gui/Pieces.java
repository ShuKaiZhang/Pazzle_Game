package comp1110.ass2.gui;

import java.util.ArrayList;

/**
 * Created by Alex on 29/8/17.
 */
public enum Pieces {
    A(new String[]{"ALU", "KBM"}),
    B(new String[]{"LW" , "BMV"}),
    C(new String[]{"UL" , "BMV"}),
    D(new String[]{"LW" , "BKV"}),
    E(new String[]{"UL" , "BKV"}),
    F(new String[]{"ULC", "VM" }),
    G(new String[]{"ULC", "BMV"}),
    H(new String[]{"CLW", "BKV"});

    private final String[] original;

    Pieces(String[] original) {
        this.original= original;
    }

    public static char rotate(char  piece) {
        char result = ' ';
        if (piece=='A') {result = 'C';}
        if (piece=='B') {result = 'M';}
        if (piece=='C') {result = 'W';}
        if (piece=='M') {result = 'V';}
        if (piece=='W') {result = 'U';}
        if (piece=='V') {result = 'K';}
        if (piece=='U') {result = 'A';}
        if (piece=='K') {result = 'B';}
        if (piece=='L') {result = 'L';}

        return result;
    }

    public static char flip(char piece) {
        char result = '.';
        if (piece=='A') {result = 'C';}
        if (piece=='B') {result = 'B';}
        if (piece=='C') {result = 'A';}
        if (piece=='M') {result = 'K';}
        if (piece=='W') {result = 'U';}
        if (piece=='V') {result = 'V';}
        if (piece=='U') {result = 'W';}
        if (piece=='K') {result = 'M';}
        if (piece=='L') {result = 'L';}
        return result;
    }



    public static void main(String[] args) {
        String a = "ALU";
        String  result = "";
        for (int i = 0; i < a.length(); i++){
            result = result + flip(a.charAt(i));
        }
        System.out.println(rotate('A'));
        System.out.println(location("ABl"));
    }

    public static String flipSt (String str){
        String result = "";
        for (int i = 0; i < str.length(); i++){
            result = result + flip(str.charAt(i));
        }
        return  result;
    }
    public static String rotateSt (String str){
        String result = "";
        for (int i = 0; i < str.length(); i++){
            result = result + rotate(str.charAt(i));
        }
        return  result;
    }


    public static String movepiece(String str,char a) {
        String result = "";

        for (int i = 0; i<str.length();i++){
            if (a <= 'Y') {
                int c = (a-'L');
                if( (char) (str.charAt(i) + c )<='Y'){
                result = result + (char) (str.charAt(i) + c );}
                if( (char) (str.charAt(i) + c )>'Y'){
                    result = result + (char) (str.charAt(i) + c+7 );}
            }if (a > 'Y') {
                int c = (a-'L')-7;
                if( (char) (str.charAt(i) + c )<='Y'){
                    result = result + (char) (str.charAt(i) + c );}
                if( (char) (str.charAt(i) + c )>'Y'){
                    result = result + (char) (str.charAt(i) + c+7 );}
            }
        }
        return result;
    }


    public static String transformation (String trans,char a) { // ALM, B
        String result = "";

        if (a=='A') {
            result = trans;
        }
        if (a=='B') {
            result = rotateSt(trans);
        }
        if (a=='C') {
            result = rotateSt(rotateSt(trans));
        }
        if (a=='D') {
            result =rotateSt(rotateSt(rotateSt(trans)));
        }
        if (a=='E') {
            result = flipSt(trans);
        }
        if (a=='F') {
            result = rotateSt(flipSt(trans));
        }
        if (a=='G') {
            result = rotateSt(rotateSt(flipSt(trans)));
        }
        if (a=='H') {
            result = rotateSt(rotateSt(rotateSt(flipSt(trans))));
        }

        return result;
    }

    public static String location (String placement) {
        String result = "";
        for (int i =0; i <placement.length(); i = i +3){
            String originpiece   = placement.substring(i,i+1); // AAL
            String origin  =  Pieces.valueOf(originpiece).original[0]; // ALC
            String sda =  transformation(origin,placement.charAt(i+1));
            char location = placement.charAt(i+2);
            result = result + movepiece(sda,location);
        }
        return result;
    }
}


/**
 AA(new String[] {"ALU","XYZ"}),AB(new String[] {"ACL"}),AC(new String[] {"CLW"}),AD(new String[] {"ULW"}),
 AE(new String[] {"KBM"}),  AF(new String[] {"BMV"}),AG(new String[] {"KVM"}),AH(new String[] {"BKV"}),
 BA(new String[] {"LW" }),  BB(new String[] {"LU" }),BC(new String[] {"AL"} ),BD(new String[] {"CL" }),
 BE(new String[] {"BKV"}),  BF(new String[] {"KBM"}),BG(new String[] {"BMV"}),BH(new String[] {"KMV"}),
 CA(new String[] {"UL" }),  CB(new String[] {"AL" }),CC(new String[] {"LC" }),CD(new String[] {"LW"}),
 CE(new String[] {"BKV"}),//AE (new String[]{"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 DA(new String[] {"LW" }),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 DB(new String[] {"LU" }),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 DE(new String[] {"BMV"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 EA(new String[] {"UL" }),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 EE(new String[] {"BMV"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 FA(new String[] {"ULC"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 FE(new String[] {"KV" }),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 GA(new String[] {"ULC"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 GE(new String[] {"BKV"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 HA(new String[] {"CLW"}),//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 HE(new String[] {"BMV"});//AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
 /*
 BG("BMV"),BH("KMV"),
 CA("LU"),CB("AL"),CC("LC"),CD("LW"),CE("BKV"),CF("KBM"),CG("BMV"),CH("KMV"),
 DA("LW"),DB("LU"),DC("AL"),DD("LC"),DE("BMV"),DF("KMV"),DG("BKV"),DH("KBM"),
 EA("LU"),EB("AL"),EC("LC"),ED("BM"),EE("BMV"),EF("KVM"),EG("BKV"),EH("KBM"),
 FA("ULC"),FB("ALW"),FC("ULC"),FD("ALW"),FE("KV"),FF("KB"),FG("BM"),FH("VM"),
 GA("BMV"),GB("KVM"),GC("BKV"),GD("BKV"),GE("ALW"),GF("KBM"),GG("BMV"),GH("KVM"),
 HA("CLW"),HB("ULW"),HC("ULA"),HD("ALC"),HE("ALU"),HF("ALC"),HG("CLW"),HH("ULW");
 **/
/*
    public static String onepieceslocation (String placement) {
        String pieces = placement.substring(0,2);
        char location = placement.charAt(2);
        String relatedlocation = Pieces.valueOf(pieces).original[0];
        return movepiece(relatedlocation,location);

    }
    **/