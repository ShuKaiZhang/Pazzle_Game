package comp1110.ass2;

import org.junit.Test;
import comp1110.ass2.gui.Pieces;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alex on 28/9/17.
 */
// As I comment before All method in Pieces that included:
    // flip, flipSt, rotate, rotateSt, movepieces, transformation, location.
    // because flip implies flipst, and flipSt implies transformation, so do rotate and rotateSt.
    // there only transformation, location, movepieces needed to be tested.

public class TransformationTest {
    @Test
    public void rotatepieceTest() {
        for ( int i = 0; i < ORIGINPIECES.length; i++){
            assertTrue("Every Piece match the B rotate", Pieces.transformation(ORIGINPIECES[i],'B').equals( ROTATEPIECES[i]));
        }
    }
    @Test
    public void flipPieceTest() {
        for (int i = 0; i < ORIGINPIECES.length; i++) {

            assertTrue("Every Piece match the B rotate " + FLIPPIECES[i], Pieces.transformation(ORIGINPIECES[i], 'E').equals(FLIPPIECES[i]));
        }
    }


    final static String[] ORIGINPIECES = {
            "ALU", "KBM", "LW", "BMV", "UL", "BMV", "LW", "BKV", "UL", "BKV", "ULC", "VM", "ULC", "BMV", "CLW", "BKV"
    };

    final static String[] ROTATEPIECES = {
            "CLA", "BMV", "LU", "MVK", "AL", "MVK", "LU", "MBK", "AL", "MBK", "ALW", "KV", "ALW", "MVK", "WLU", "MBK"
    };

    final static String[] FLIPPIECES = {
            "CLW", "MBK", "LU", "BKV", "WL", "BKV", "LU", "BMV", "WL", "BMV", "WLA", "VK", "WLA", "BKV", "ALU", "BMV"

    };
}