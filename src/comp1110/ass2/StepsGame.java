package comp1110.ass2;

import comp1110.ass2.gui.Pieces;

import java.time.temporal.ValueRange;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame{
    /**
     *
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static  boolean charAtoH (char c) {
        if (c>='A' && c<= 'H') {
            return true;
        }else   {
            return false  ;
        }
    }
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        if (piecePlacement.length() ==3) {
            if ((charAtoH(piecePlacement.charAt(0))) && (charAtoH(piecePlacement.charAt(1)))){
                if ((piecePlacement.charAt(2))>='A' && (piecePlacement.charAt(2) <= 'Y')) {
                    return  true;
                }
                if ((piecePlacement.charAt(2))>='a' && (piecePlacement.charAt(2) <= 'y')) {
                    return  true;
                }
            }
        }
        return false;
    }


    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
     *  - each piece placement is well-formed
     *  - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    static String duplicateString (String input){
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if(!result.contains(String.valueOf(input.charAt(i)))) {
                result += String.valueOf(input.charAt(i));
            }
        }
        return result;
    }
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        if (placement == "" || placement== null) {
            return false;
        }
        if (placement.length()%3 !=0) {
            return false;
        }
        int a = 0;
        ArrayList<String> placements = new ArrayList<>();
        ArrayList<String> shapes  = new ArrayList<>();


        for (int i = 0; i < placement.length(); i= i+3){
            placements.add(placement.substring(i,i+3));
            shapes.add(placement.substring(i,i+3).substring(0,1));
        }
        String shapeset = shapes.toString();
        String dupstr =duplicateString(shapeset).replace(", ", "");
        String shapestr = shapeset.replace(", ","");
        System.out.println(dupstr.equals(shapestr));
        System.out.println(dupstr);
        System.out.println(shapestr);
        if (dupstr.equals(shapestr)) {
            a = a + 10;
        }else {
            a= a+3;
        }

        for (int i = 0; i < (placement.length()) / 3; i++) {
            if ((isPiecePlacementWellFormed(placements.get(i)))) {
                a = a +10;
            }else{
                a = a +3 ;
            }
        }
        if(a % 10 ==0) {
            return true;
        }else{
            return false;
        }
    }


    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    // FIXME Task 5: determine whether a placement sequence is valid

    static boolean isPlacementSequenceValid(String placement) {
        boolean result = false;

        /*
         String location = Pieces.location(placement);
        for (int i =0; i < location.length();i++){
            if (location.charAt(i)<'A' || location.charAt(i)>'y'){
                return false;
            }
            if (location.charAt(i)>'Y' && location.charAt(i)< 'a'){
                return false;
            }

        }
         */
        return isPlacementWellFormed(placement);

    }

    /**
     * Given a string describing a placement of pieces and a string describing
     * an (unordered) objective, return a set of all possible next viable
     * piece placements.   A viable piece placement must be a piece that is
     * not already placed (ie not in the placement string), and which will not
     * obstruct any other unplaced piece.
     *
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return An set of viable piece placements
     */

    static Set<String> getViablePiecePlacements(String placement, String objective) {
        // FIXME Task 6: determine the correct order of piece placements
        return null;
    }

    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }
}
