package comp1110.ass2.gui;

/**
 * Created by Alex on 29/8/17.
 */
public enum Pieces {
    AA(new String[] {"ALU"}),AB(new String[] {"ALU"}),AC(new String[] {"ALU"}),AD(new String[] {"ALU"}),
    AE(new String[] {"KBM"}),
    BA(new String[] {"LW" }),
    BE(new String[] {"BKV"}),
    CA(new String[] {"UL" }),
    CE(new String[] {"BKV"}),
    DA(new String[] {"LW" }),
    DB(new String[] {"LU" }),
    DE(new String[] {"BMV"}),
    EA(new String[] {"UL" }),
    EE(new String[] {"BMV"}),
    FA(new String[] {"ULC"}),
    FE(new String[] {"KV" }),
    GA(new String[] {"ULC"}),
    GE(new String[] {"BKV"}),
    HA(new String[] {"CLW"}),
    HE(new String[] {"BMV"});
/*
    AA("ALU"), AB("ACL"), AC("CLW"), AD("ULW"), AE("BKM"), AF("BMV"), AG("KVM"), AH("BKV"),
    BA("LW"), BB("LU"), BC("AL"), BD("LC"), BE("BKV"),BF("KBM"),BG("BMV"),BH("KMV"),
    CA("LU"),CB("AL"),CC("LC"),CD("LW"),CE("BKV"),CF("KBM"),CG("BMV"),CH("KMV"),
    DA("LW"),DB("LU"),DC("AL"),DD("LC"),DE("BKV"),DF("BMV"),DG("BKV"),DH("KBM"),
    EA("LU"),EB("AL"),EC("LC"),ED("BM"),EE("BMV"),EF("KVM"),EG("BKV"),EH("KBM"),
    FA("ULC"),FB("ALW"),FC("ULC"),FD("ALW"),FE("KV"),FF("KB"),FG("BM"),FH("VM"),
    GA("BMV"),GB("KVM"),GC("BKV"),GD("KBM"),GE("ALW"),GF("ULC"),GG("ALW"),GH("ULC"),
    HA("BKV"),HB("KBM"),HC("BMV"),HD("KVM"),HE("ALU"),HF("ALC"),HG("CLW"),HH("ULW");
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

    public static void main(String[] args) {
        System.out.println("Start: ");
        String a = "AAlBBBC=EE";
        String b = "AAlBBB=CEE";
        for (int i =0; i < a.length();i++){
            if (a.charAt(i)<'A' || a.charAt(i)>'y'){
                System.out.println(a.charAt(i) + " 1 False");
            }
            if (a.charAt(i)>'Y' && a.charAt(i)< 'a'){
                System.out.println(a.charAt(i) + "  False");
            }
        }
        System.out.println("end ");
        location("AAl");
    }


}
