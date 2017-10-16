//package comp1110.ass2;
//
///**
// * Created by Alex on 29/9/17.
// */
//public class Board {
//    PegStates[][] board = new PegStates[5][10];
//
//    Board(){
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board[i].length; j++){
//                boolean ontop = true;
//                if(i%2==0){
//                    if(j%2!=0){
//                        ontop = true;
//                    }else {
//                        ontop = false;
//                    }
//                }else {
//                    if(j%2!=0){
//                        ontop = false;
//                    }else {
//                        ontop = true;
//                    }
//                }
//                board[i][j] = new PegStates(ontop);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Board a = new Board();
//        a.print();
//    }
//    public void print() {
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board[i].length; j++){
//                int v = 0;
//                if (board[i][j].exist){
//                    v = 1;
//                }else {
//                    v = 0;
//                }
//                System.out.print(v);
//            }
//            System.out.println("");
//        }
//    }
//
//    public boolean putAll(String placement) {
//        boolean result = true;
//        int pieceNum = placement.length() / 3;
//        for (int i = 0; i < pieceNum; i++){
//            String piece = placement.substring(3*i, 3*(i+1));
//            if (!putOne(piece)){
//                result = false;
//            }
//        }
//        return result;
//    }
//
//    public boolean putOne(String piece) {
//        Piece p = new Piece(piece);
//        if (offBoard(p)){
//            return false;
//        }
//        if (!bottomOnPegs(p)){
//            return false;
//        }
//        if (obstructed(p)){
//            return false;
//        }
//        if (!upperVacant(p)){
//            return false;
//        }
//        putPiece(p);
//        return true;
//    }
//
//    private void putPiece(Piece p) {
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                for (int h = 0; h<2 ; h++) {
//                    PegStates point = p.piece[i][j][h];
//                    int pointX = p.axisX + i - 1;
//                    int pointY = p.axisY + j - 1;
//                    if (point.exist) {
//                        board[pointX][pointY].exist = true;
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean upperVacant(Piece p) {
//        boolean result = true;
//        for (int i = 0; i < 3; i++){
//            for(int j = 0; j < 3; j++){
//                for (int h = 0; h<2 ; h++) {
//                    PegStates point = p.piece[i][j][h];
//                    int pointRow = p.axisX + i - 1;
//                    int pointCol = p.axisY + j - 1;
//                    boolean pointExist = point.exist;
//                    boolean pointOnTop = pointExist && point.ontop;
//                    boolean pointVacant = pointExist && !board[pointRow][pointCol].exist;
//                    if (pointExist && pointOnTop && !pointVacant) {
//                        result = false;
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    public boolean obstructed(Piece p) {
//        boolean result = false;
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                for (int h = 0; h<2 ; h++) {
//                    PegStates me = p.piece[i][j][h];
//                    int pointX = p.axisX + i - 1;
//                    int pointY = p.axisY + j - 1;
//                    int[][] pointAround = {{pointX, pointY - 1}, {pointX, pointY + 1}, {pointX - 1, pointY}, {pointX + 1, pointY}};
//                    for (int[] coord : pointAround) {
//                        if (inRange(coord)) {
//                            boolean meExist = me.exist;
//                            boolean meOnBottom = meExist && !me.ontop;
//                            PegStates other = board[coord[0]][coord[1]];
//                            boolean othersOnTop = other.ontop;
//                            boolean othersEngaged = other.exist;
//                            if (meExist && meOnBottom && othersOnTop && othersEngaged) {
//                                result = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    private boolean inRange(int[] coord) {
//        return coord[0] >= 0 && coord[0] < 4 && coord[1] >= 0 && coord[1] <= 9;
//    }
//
//    public boolean bottomOnPegs(Piece p) {
//        boolean result = true;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int h = 0; h < 2; h++) {
//                    PegStates point = p.piece[i][j][h];
//                    int pointRow = p.axisX + i - 1;
//                    int pointCol = p.axisY + j - 1;
//                    boolean pointExist = point.exist;
//                    boolean pointOnBottom = pointExist && !point.ontop;
//                    boolean pointOnPeg = pointExist && !board[pointRow][pointCol].ontop;
//                    if (pointOnBottom && !pointOnPeg) {
//                        result = false;
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
//    public boolean offBoard(Piece p) {
//        boolean result = false;
//        for (int i = 0; i < 3; i++){
//            for(int j = 0; j < 3; j++) {
//                for (int h = 0; h < 2; h++) {
//                    int boardHeight = 4;
//                    int boardWidth = 9;
//                    int pointRow = p.axisX + i - 1;
//                    int pointCol = p.axisY + j - 1;
//                    PegStates point = p.piece[i][j][h];
//                    boolean pointExist = point.exist;
//                    boolean pointOnBoard = pointRow >= 0 && pointRow <= boardHeight && pointCol >= 0 && pointCol <= boardWidth;
//                    if (pointExist && !pointOnBoard) {
//                        result = true;
//                    }
//                }
//            }
//
//        }
//        return result;
//    }
//}
