package comp1110.ass2.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Alex on 23/8/17.
 * All this test file is a draft. we can do whatever we want.
 */

public class Test {
    public static void main(String[] args) {
        boolean a = isvalid3char('a');
        System.out.println(a);
        System.out.println( ! false
        );
    }
    static boolean isvalid3char (char  a) {
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
