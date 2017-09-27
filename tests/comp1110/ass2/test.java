package comp1110.ass2;

import comp1110.ass2.gui.Pieces;
import org.junit.Test;


import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Kai on 27/9/17.
 */

public class test {
    @Test
    public void test_location() {
        for (int i = 0; i < GOOD_PAIRS.length; i++) {
            assertEquals("The answer is not true",GOOD_PAIRS_KEY[i], Pieces.location(GOOD_PAIRS[i]));
        }
    }

    @Test
    public void test_rotateSt() {
        for (int i = 0; i < ROTATE.length; i++) {
            assertEquals("The answer is not true",ROTATE_KEY[i], Pieces.rotateSt(ROTATE[i]));
        }
    }



    final static String[] GOOD_PAIRS_KEY = {
            "GPbcQtkvlu",
            "ENYaODxowny",
            "ULAKBYNEDO",
            "EPGFQCLUBK",
            "RdTJSGPbFQH",
            "WLAKBMpgrqfV",
            "PYNODbRGFQ",
            "WLUMBKpgrqfV",
            "ULAKBENWDM",
            "PYNODALUKBM",
            "PYNODbRGFQc",
            "PYNODbRGFQc",
            "YNEDOWLUMBK",
            "YNEDObRGFQ",
            "YPEDObRGFQc",
            "WLUMBKpgrqfV",
            "TIRcSJxoeny",
            "xoenyGPHQF",
            "GPHQFYNCOD",
            "RbPQFYNCOD",
            "YNCODdTIHSe",
            "dTIHSevmxnw",
            "RdTJScxoeny",
            "xoenyGPHQF",
            "YNCODtkalu",
            "YNEDObRGFQ",
            "YNEDOWLUMBK",
            "YNEDObRGFQ",
            "YNEDOWLUMBK"
    };
    final static String[] ROTATE = {
            "ALU",
            "KBM",
            "LW",
            "BMV",
            "UL",
            "BMV",
            "LW",
            "BKV",
            "UL",
            "BKV",
            "ULC",
            "VM",
            "ULC",
            "BMV",
            "CLW",
            "BKV"
    };

    final static String[] ROTATE_KEY = {
            "CLA",
            "BMV",
            "LU",
            "MVK",
            "AL",
            "MVK",
            "LU",
            "MBK",
            "AL",
            "MBK",
            "ALW",
            "KV",
            "ALW",
            "MVK",
            "WLU",
            "MBK"
    };
}
