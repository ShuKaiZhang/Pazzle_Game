package comp1110.ass2;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.junit.Assert;
import org.junit.Test;
import comp1110.ass2.StepsGame;
import java.util.Random;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alex on 28/9/17.
 */
public class CharAtoHTest {

    @Test
    public void rejectnumTest() {
        assertFalse("it reject the char that not in the A-H ", StepsGame.charAtoH('a'));
        assertFalse("it reject the symbor that not in the A-H", StepsGame.charAtoH('['));
        assertFalse("it reject the number that not in the A-H", StepsGame.charAtoH('1'));
        assertFalse("it reject the char that not in the A-H ", StepsGame.charAtoH('L'));
    }
    @Test
    public void charAH(){
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('A'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('B'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('C'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('D'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('E'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('F'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('G'));
        assertTrue("it return true when char is A-H", StepsGame.charAtoH('H'));
    }
}
