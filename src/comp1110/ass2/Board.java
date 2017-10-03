package comp1110.ass2;

import javafx.geometry.Pos;

/**
 * Created by Alex on 29/9/17.
 */
public class Board {
    Position[][] board = new Position[5][10];

    Board(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                boolean ontop = true;
                if(i%2==0){
                    if(j%2!=0){
                        ontop = true;
                    }else {
                        ontop = false;
                    }
                }else {
                    if(j%2!=0){
                        ontop = false;
                    }else {
                        ontop = true;
                    }
                }
                board[i][j] = new Position(ontop);
            }
        }
    }

    public void print() {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                int v = 0;
                if (board[i][j].engaged){
                    v = 1;
                }else {
                    v = 0;
                }
                System.out.print(v);
            }
            System.out.println("");
        }
    }

    public boolean putAll(String placement) {
        boolean result = true;
        int pieceNum = placement.length() / 3;
        for (int i = 0; i < pieceNum; i++){
            String piece = placement.substring(3*i, 3*(i+1));
            if (!putOne(piece)){
                result = false;
            }
        }
        return result;
    }

    public boolean putOne(String piece) {
        Piece p = new Piece(piece);
        if (offBoard(p)){
            return false;
        }
        if (!bottomOnPegs(p)){
            return false;
        }
        if (obstructed(p)){
            return false;
        }
        if (!upperVacant(p)){
            return false;
        }
        putPiece(p);
        return true;
    }

    private void putPiece(Piece p) {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Position point = p.piece[i][j];
                int pointX = p.pieceX + i - 1;
                int pointY = p.pieceY + j - 1;
                if (point.engaged){

                    board[pointX][pointY].engaged = true;
                }
            }
        }
    }

    private boolean upperVacant(Piece p) {
        boolean result = true;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Position point = p.piece[i][j];
                int pointRow = p.pieceX + i - 1;
                int pointCol = p.pieceY + j - 1;
                boolean pointExist = point.engaged;
                boolean pointOnTop = pointExist && point.ontop;
                boolean pointVacant = pointExist && !board[pointRow][pointCol].engaged;
                if (pointExist && pointOnTop && !pointVacant){
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean obstructed(Piece p) {
        boolean result = false;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Position me = p.piece[i][j];
                int pointX = p.pieceX + i - 1;
                int pointY = p.pieceY + j - 1;
                int[][] pointAround = {{pointX,pointY-1},{pointX,pointY+1},{pointX-1,pointY},{pointX+1,pointY}};
                for (int[] coord : pointAround){
                    if (inRange(coord)){
                        boolean meExist = me.engaged;
                        boolean meOnBottom = meExist && !me.ontop;
                        Position other = board[coord[0]][coord[1]];
                        boolean othersOnTop = other.ontop;
                        boolean othersEngaged = other.engaged;
                        if (meExist && meOnBottom && othersOnTop && othersEngaged){
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean inRange(int[] coord) {
        return coord[0] >= 0 && coord[0] < 4 && coord[1] >= 0 && coord[1] <= 9;
    }

    public boolean bottomOnPegs(Piece p) {
        boolean result = true;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Position point = p.piece[i][j];
                int pointRow = p.pieceX + i - 1;
                int pointCol = p.pieceY + j - 1;
                boolean pointExist = point.engaged;
                boolean pointOnBottom = pointExist && !point.ontop;
                boolean pointOnPeg = pointExist && !board[pointRow][pointCol].ontop;
                if (pointOnBottom && !pointOnPeg){
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean offBoard(Piece p) {
        boolean result = false;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int boardHeight = 4;
                int boardWidth = 9;
                int pointRow = p.pieceX + i - 1;
                int pointCol = p.pieceY + j - 1;
                Position point = p.piece[i][j];
                boolean pointExist = point.engaged;
                boolean pointOnBoard = pointRow >= 0 && pointRow <= boardHeight && pointCol >= 0 && pointCol <= boardWidth;
                if (pointExist && !pointOnBoard){
                    result = true;
                }
            }
        }
        return result;
    }
}
