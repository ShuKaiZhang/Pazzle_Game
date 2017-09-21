package comp1110.ass2.gui;

/**
 * Created by Alex on 29/8/17.
 */
public enum Pieces {
    AA(new String[] {"ALU",}),AB(new String[] {"ACL"}),AC(new String[] {"CLW"}),AD(new String[] {"ULW"}),
    AE(new String[] {"KBM"}),AF(new String[] {"BMV"}),AG(new String[] {"KVM"}),AH(new String[] {"BKV"}),
    BA(new String[] {"LW" }),BB(new String[] {"LU" }),BC(new String[] {"AL"} ),BD(new String[] {"CL" }),
    BE(new String[] {"BKV"}),BF(new String[] {"KBM"}),BG(new String[] {"BMV"}),BH(new String[] {"KMV"}),
    CA(new String[] {"UL" }),CB(new String[] {"AL" }),CC(new String[] {"LC" }),CD(new String[] {"LW"}),
    CE(new String[] {"BKV"}), //AE (new String[] {"KBM"}),AE(new String[] {"KBM"}),AE(new String[] {"KBM"}),
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
    private final String[] original;

    Pieces(String[] original) {
        this.original= original;
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
/*
    public static String onepieceslocation (String placement) {
        String pieces = placement.substring(0,2);
        char location = placement.charAt(2);
        String relatedlocation = Pieces.valueOf(pieces).original[0];
        return movepiece(relatedlocation,location);

    }
    **/
    public static String location (String placement) {
        String result = "";
        for (int i =0; i <placement.length(); i = i +3){
            String pieces = placement.substring(i,i+2);
            char location = placement.charAt(i+2);
            String asd =  Pieces.valueOf(pieces).original[0];
            System.out.println(asd+ "  asd");
            result = result + movepiece(asd,location);
            System.out.println(result + "  result");
        }
        return  result;
    }



}
