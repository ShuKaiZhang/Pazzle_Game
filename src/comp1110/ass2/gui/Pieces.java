package comp1110.ass2.gui;

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

    public static String[] location (String placement) {
        String[] result = {"", ""};
        for (int i =0; i <placement.length(); i = i +3){
            String originpiece   = placement.substring(i,i+1); // AAL
            String origin1  =  Pieces.valueOf(originpiece).original[0]; // ALC
            String origin2  =  Pieces.valueOf(originpiece).original[1];
            char trans = placement.charAt(i+1);
            char location = placement.charAt(i+2);
            if (trans>='A'&& trans<='D'){
                String sda1 =  transformation(origin1,trans);//1st level
                String sda2 =  transformation(origin2,trans);//2nd level
                result[0] += movepiece(sda1,location);
                result[1] += movepiece(sda2,location);

            }else{
                String sda1 =  transformation(origin2,trans);//1st level
                String sda2 =  transformation(origin1,trans);//2nd level
                result[0] += movepiece(sda1,location);
                result[1] += movepiece(sda2,location);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}