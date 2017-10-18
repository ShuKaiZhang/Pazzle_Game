package comp1110.ass2;

import comp1110.ass2.gui.Pieces;

import java.util.*;


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
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        boolean result;
        boolean eachformed = true;
        if (placement == null) {
            return false;
        }
        int pieceAmount = placement.length() / 3;

        boolean completePieces = placement.length() % 3 == 0;
        boolean piecesValid = pieceAmount > 0 && pieceAmount < 9 && completePieces;

        for (int i = 0; i < pieceAmount; i = i + 3) {
            String piece = placement.substring(i, i + 3);
            if (!isPiecePlacementWellFormed(piece)) {
                eachformed = false;
            }
        }
        boolean noDuplicate = true;
        for (int i = 0; i < pieceAmount; i++) {
            String piece1 = placement.substring(3 * i, 3 * (i + 1));
            for (int j = i + 1; j < pieceAmount; j++) {
                String piece2 = placement.substring(3 * j, 3 * (j + 1));
                if (piece1.charAt(0) ==piece2.charAt(0)) {
                    noDuplicate = false;
                }
            }
        }

        result = piecesValid && eachformed && noDuplicate;
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
    // FIXME Task 5: determine whether a placement sequence is valid




    public static boolean checkAround (char x, char j){
        boolean result = false;
        int a;
        int b;
        if(x<='Y'){
            a = (int)x - 65;
        }else {a = (int)x - 97+25;}
        if(j<='Y'){
            b = (int)j - 65;
        }else {b = (int)j - 97+25;}
        int[] notValid = { (char)(a-10) , (char)(a-1), (char)(a+1), (char)(a+10)};
        for (int i =0 ; i< 4; i++){
            if (notValid[(i)]==b){
                return false;
            }else result = true;
        }
        return  result;
    }



    public static boolean checkDup (String placement){
        boolean result = true;
        String locate = Pieces.location(placement.substring(0,3))[1];
        for (int i =3; i< placement.length();i=i+3){
            String piece = placement.substring(i,i+3);
            String location1 = Pieces.location(piece)[0];
            String location2 = Pieces.location(piece)[1];
            for (int j=0; j<location1.length();j++){
                for(int x = 0; x< locate.length();x++){
                    if (!checkAround(locate.charAt(x),location1.charAt(j))){
                        return false;
                    }
                }
            }locate+=location2;
        }
        return result;
    }

    public static String firstLevel = "ACEGILNPRTUWYbdgikmoprtvx";
    public static String secondLevel = "BDFHJKMOQSVXacefhjlnqsuwy";
    public static boolean checklevel (char c, String a){
        boolean result = true;
        for (int i =0; i< a.length();i++){
            if (c==a.charAt(i)){
                if(a.equals(firstLevel)){
                    firstLevel=a.replace(a.charAt(i),' ');
                }else{
                    secondLevel=a.replace(a.charAt(i),' ');
                }
                return true;
            }else {
                result = false;}
        }
        return result;
    }

    public static boolean isPlacementSequenceValid(String placement) {
        boolean result;
        firstLevel = "ACEGILNPRTUWYbdgikmoprtvx";
        secondLevel = "BDFHJKMOQSVXacefhjlnqsuwy";
        if (isPlacementWellFormed(placement)){
            String[] coord = Pieces.location(placement);
            for (int a = 0;a<coord[0].length();a++ ){
                if (!checklevel((coord[0].charAt(a)),firstLevel)){
                    return false;
                }
            }
            for (int b = 0;b<coord[1].length();b++ ) {
                if (!checklevel((coord[1].charAt(b)), secondLevel)) {
                    return false;
                }
            }
        }else return false;
        if (checkDup(placement)){
            result = true;
        }else return false;
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
    static ArrayList<String> reset(String objective, String onBoard){
        ArrayList<String> result = new ArrayList<>();
        if (objective.length()==0) {
            result.add(onBoard);
        }
        for (int i = 0;i< objective.length();i=i+3){
            String piece = objective.substring(i,i+3);
            if (!isPlacementSequenceValid(onBoard + piece)){
                break;
            } else { // StringBuilder https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
                List<String> next = reset(new StringBuilder(objective).delete(i,i+3).toString(),
                        onBoard + objective.substring(i,i+3));
                result.addAll(next);
            }
        }
        return result;
    }
    // FIXME Task 6: determine the correct order of piece placements
    static Set<String> getViablePiecePlacements(String placement, String objective) {
        Set<String> result = new HashSet<>();
        ArrayList<String> list = reset(objective,"");
        int length = placement.length();
        for (String i: list){
            String piece=i.substring(0,3);
            System.out.println("piece: " +piece);

            if (length == 0){
                result.add(piece);
            }
            else if(i.substring(0,length).equals(placement)
                    && length!=24) {
                result.add(i.substring(length,length+3));
            }
        }return result;
    }
    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static String  getCandidatePiece (String placement){
        String result = "ABCDEFGH";
        for(int i =0; i<placement.length();i=i+3){
            String shape = placement.substring(i,i+1);
            result=result.replace(shape,"");
        }
        return result;
    }
    static String[] getCandidateLocation (String placement){ //3 piece -> 18 //  32
        String[] result = new String[2];
        String location0 = Pieces.location(placement)[0];
        String location1 = Pieces.location(placement)[1];
        result[0] = firstLevel;
        result[1] = secondLevel;

        for(int i =0; i <location0.length();i++){
            result[0] =result[0].replace(location0.charAt(i)+"","");
        }
        for(int i =0; i <location1.length();i++){
            result[1] =result[1].replace(location1.charAt(i)+"","");

        }
        return  result;
    }
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        String [] result =new String[2];
        String candidatePiece = getCandidatePiece(placement);
        for(int i =0;i<=candidatePiece.length();i++){
        }
        return result;
    }
}