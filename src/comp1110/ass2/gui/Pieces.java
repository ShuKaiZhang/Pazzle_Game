package comp1110.ass2.gui;

/**
 * Created by Alex on 29/8/17.
 */
public enum Pieces {
    AA(new String[] {"ALU"}),
    AE(new String[] {"KBM"}),
    BA(new String[] {"LW"}),
    BE(new String[] {"BKV"}),
    CA(new String[] {"UL"}),
    CE(new String[] {"BKV"}),
    DA(new String[] {"LW"}),
    DE(new String[] {"BMV"}),
    EA(new String[] {"UL"}),
    EE(new String[] {"BMV"}),
    FA(new String[] {"ULC"}),
    FE(new String[] {"KV"}),
    GA(new String[] {"ULC"}),
    GE(new String[] {"BKV"}),
    HA(new String[] {"CLW"}),
    HE(new String[] {"BMV"});

    private final String[] original;

    Pieces(String[] original) {
        this.original= original;
    }

    static String movepiece(String str,char a) {
        String result = "";


        for (int i = 0; i<str.length();i++){
            if (a <= 'Y') {
                int c = (a-'L');
                if( (char) (str.charAt(i) + c )<='Y'){
                result = result + (char) (str.charAt(i) + c );}
                if( (char) (str.charAt(i) + c )>'Y'){
                    result = result + (char) (str.charAt(i) + c+7 );}
                System.out.println(">Y");
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

    String finalmove(Pieces piec, char location){
         String a = this.original[0];
         String result = movepiece(a,location);
         return result;
    }


    public static void main(String[] args) {
        System.out.println("Start: ");
        System.out.println();
        System.out.println(movepiece("ALU",'B'));
        String asd ="AACBAFCAIDAL";
        for (int i =0; i< asd.length(); i=i+3){
            String s = asd.substring(i,i+3);
            System.out.println(s);
        }
        System.out.println();
        System.out.println("end ");

    }


}
