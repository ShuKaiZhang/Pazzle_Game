package comp1110.ass2.gui.assets;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 23/8/17.
 */
public class Test {
    public static void main(String[] args) {

    }
    static boolean isvalid3char (String  a) {
        boolean result =false;
        char[] charAtoYandatoy =
                {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
                        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y'};
        if (Arrays.asList(charAtoYandatoy).contains(a)){
            result = true;
        }
        return result;
    }
    static boolean isvalid12char (char a) {
        char[] charAtoH ={'A','B','C','D','E','F','G','H'};
        boolean result =false;
        if (Arrays.asList(charAtoH).contains(a)){
            result = true;
        }
        return result;
    }
}
