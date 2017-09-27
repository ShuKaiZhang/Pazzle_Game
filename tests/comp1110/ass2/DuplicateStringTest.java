package comp1110.ass2;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.junit.Assert;
import org.junit.Test;
import comp1110.ass2.gui.Pieces;
import comp1110.ass2.StepsGame;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alex on 27/9/17.
 */
public class DuplicateStringTest {
    @Test
    public void duplicatedclosechar(){
        for (int i = 0; i<CLOSEDUPLICATE.length; i++){
            String str = StepsGame.duplicateString(CLOSEDUPLICATE[i]);
            assertTrue("passed close duplicate String ",str.equals("ABCD") );
        }
    }
    @Test
    public void randomduplicate(){
        for (int i =0; i<RANDOMDUPLICATE.length; i++){
            String str = StepsGame.duplicateString(RANDOMDUPLICATE[i]);
            assertTrue("passed random duplicate String " , str.length()==8);
        }
    }

    final static String[] CLOSEDUPLICATE ={
            "AABCD",
            "ABCCCCD",
            "AAAAAAAAAAAAAAABCD",
            "AAAAAABBBBCCCCCCCDD"
    };

    final static String[] RANDOMDUPLICATE={
            "ABCDA 012",
            "AAAABBCBABDABDB 100101010122",
            "BBBBBBABBABBABABCCCBCBCCBCCBDDDDD       11 1 1 1 1 1 020 02010 10 02 10 "

    };
}
