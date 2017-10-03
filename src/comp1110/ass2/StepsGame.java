package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {
    
    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        boolean result = false;
        char first = piecePlacement.charAt(0);
        char second = piecePlacement.charAt(1);
        char third = piecePlacement.charAt(2);
        boolean firstInRange = first >= 'A' && first <= 'H';
        boolean secondInRange = second >= 'A' && second <= 'H';
        boolean thirdInRange = (third >= 'A' && third <= 'Y') || (third >= 'a' && third <= 'y');
        result = firstInRange && secondInRange && thirdInRange;
        return result;
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
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        if (placement == null){
            return false;
        }
        boolean result = false;
        int length = placement.length();
        int pieceNum = length / 3;
        boolean exactlyN = length % 3 == 0 && pieceNum >= 1 && pieceNum <= 8;
        boolean eachWellForm = true;
        for (int i = 0; i < pieceNum; i++){
            String piece = placement.substring(3*i, 3*(i+1));
            if (!isPiecePlacementWellFormed(piece)){
                eachWellForm = false;
            }
        }
        boolean noMoreThanOnce = true;
        for (int i = 0; i < pieceNum - 1; i++){
            String piece1 = placement.substring(3*i, 3*(i+1));
            for (int j = i+1; j < pieceNum; j++){
                String piece2 = placement.substring(3*j, 3*(j+1));
                if (piece1.charAt(0) == piece2.charAt(0)){
                    noMoreThanOnce = false;
                }
            }
        }
        result = exactlyN && eachWellForm && noMoreThanOnce;
        return result;
    }
    
    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        boolean result = false;
        boolean wellformed = isPlacementWellFormed(placement);
        boolean eachPieceValid = false;
        if (wellformed){
            Board board = new Board();
            eachPieceValid = board.putAll(placement);
        }else {
            eachPieceValid = false;
        }
        result = wellformed && eachPieceValid;
        return result;
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
        Set<String> result = new HashSet<>();
        ArrayList<String> pieces = getPieces(placement, objective);
        System.out.println(placement);
        System.out.println(objective);
        System.out.println(pieces.toString());
        for (String piece : pieces){
            String nextPlacement = placement + piece;
            ArrayList<String> missingPieces = getMissingPieces(pieces, piece);
            if (!conflict(nextPlacement, missingPieces)){
                result.add(piece);
            }
        }
        return result;
    }
    
    private static boolean conflict(String placement, ArrayList<String> missingPieces) {
        boolean result = false;
        for (String piece : missingPieces){
            Board b = new Board();
            b.putAll(placement);
            if (!b.putOne(piece)){
                result = true;
            }
        }
        return result;
    }
    
    private static ArrayList<String> getMissingPieces(ArrayList<String> pieces, String presentpiece) {
        ArrayList<String> result = new ArrayList<>();
        for (String piece : pieces){
            if (!piece.equals(presentpiece)) {
                result.add(piece);
            }
        }
        return result;
    }
    
    private static ArrayList<String> getPieces(String placement, String objective) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < placement.length() / 3; i++){
            String piece = placement.substring(3*i, 3*(i+1));
            System.out.println("remove : " + piece);
            objective = objective.replace(piece, "");
        }
        System.out.println("after process : " + objective);
        int pieceNum = objective.length() / 3;
        for (int i = 0; i < pieceNum; i++){
            String piece = objective.substring(3*i, 3*(i+1));
            result.add(piece);
        }
        return result;
    }
    
    /**
     * Return an array of all unique (unordered) solutions to the game, given a
     * starting placement.   A given unique solution may have more than one than
     * one placement sequence, however, only a single (unordered) solution should
     * be returned for each such case.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a unique unordered solution to
     * the game given the starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }
}

