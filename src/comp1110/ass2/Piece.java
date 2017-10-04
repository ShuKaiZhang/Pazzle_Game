package comp1110.ass2;

/**
 * Created by Alex on 29/9/17.
 */
public class Piece {
    char pieceName;
    char orientation;
    char position;

    int pieceX;
    int pieceY;



    Position[][] piece;

    private Position[][] getPiece(char pieceName) {
        Position[][] result = new Position[3][3];
        switch (pieceName){
            case 'A':
                result[0][0] = new Position(false, true);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(true, false);
                result[1][0] = new Position(true, true);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(true, true);
                result[2][0] = new Position(false, true);
                result[2][1] = new Position(true, false);
                result[2][2] = new Position(true, false);
                break;
            case 'B':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, false);
                result[1][0] = new Position(false, false);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(true, true);
                result[2][0] = new Position(false, false);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, true);
                break;
            case 'C':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, false);
                result[1][0] = new Position(false, false);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(true, true);
                result[2][0] = new Position(false, true);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, false);
                break;
            case 'D':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, false);
                result[1][0] = new Position(true, true);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(false, false);
                result[2][0] = new Position(false, false);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, true);
                break;
            case 'E':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, false);
                result[1][0] = new Position(true, true);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(false, false);
                result[2][0] = new Position(false, true);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, false);
                break;
            case 'F':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(false, false);
                result[0][2] = new Position(false, true);
                result[1][0] = new Position(false, false);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(true, true);
                result[2][0] = new Position(false, true);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, false);
                break;
            case 'G':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, true);
                result[1][0] = new Position(false, false);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(true, true);
                result[2][0] = new Position(false, true);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, false);
                break;
            case 'H':
                result[0][0] = new Position(false, false);
                result[0][1] = new Position(true, true);
                result[0][2] = new Position(false, true);
                result[1][0] = new Position(true, true);
                result[1][1] = new Position(false, true);
                result[1][2] = new Position(false, false);
                result[2][0] = new Position(false, false);
                result[2][1] = new Position(true, true);
                result[2][2] = new Position(false, true);
                break;
            default:
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        result[i][j] = null;
                    }
                }
                break;
        }
        return result;
    }

    public Piece(String piecePlacement) {
        pieceName   = piecePlacement.charAt(0);
        orientation = piecePlacement.charAt(1);
        position    = piecePlacement.charAt(2);

        if (position >= 'A' && position <= 'Y') {
            int positionpiece = position - 'A';
            this.pieceX = positionpiece / 10;
            this.pieceY = positionpiece % 10;
        } else {
            int positionpiece = position - 'a' + 25;
            this.pieceX = positionpiece / 10;
            this.pieceY = positionpiece % 10;
        }

        piece = getPiece(pieceName);
        piece = rotate(piece, orientation);
    }

    private Position[][] rotate(Position[][] piece, char orientation) {
        Position[][] result = piece;
        boolean flip = false;
        int rotateTimes = 0;
        if (orientation >= 'E' && orientation <= 'H'){
            flip = true;
            rotateTimes = orientation - 'E';
        }else {
            flip = false;
            rotateTimes = orientation - 'A';
        }
        if (flip){
            result = flipPiece(result);
        }
        for (int i = 0; i < rotateTimes; i++){
            result = rotatePiece(result);
        }
        return result;
    }

    private Position[][] rotatePiece(Position[][] piece) {
        Position[][] result = new Position[3][3];
        for (int row = 0; row<3 ; row++){
            for (int col = 0; col<3;col++){
                if (col ==0 && row ==0) {
                    result[0][2] = piece [row][col];
                }
                if (row ==0 && col ==1) {
                    result[1][2] = piece [row][col];
                }
                if (row ==0 && col ==2) {
                    result[2][2] = piece [row][col];
                }
                if (row ==1 && col ==0) {
                    result[0][1] = piece [row][col];
                }
                if (row ==1 && col ==1) {
                    result[1][1] = piece [row][col];
                }
                if (row ==1 && col ==2) {
                    result[2][1] = piece [row][col];
                }
                if (row ==2 && col ==0) {
                    result[0][0] = piece [row][col];
                }
                if (row ==2 && col ==1) {
                    result[1][0] = piece [row][col];
                }
                if (row ==2 && col ==2) {
                    result[2][0] = piece [row][col];
                }

            }
        }
        return  result;
    }

    private Position[][] flipPiece(Position[][] piece) {
        Position[][] result = new Position[3][3];
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                result[row][col] = new Position(false, false);
                int firstCol = 0;
                int thirdCol = 2;
                if (col == firstCol){
                    result[row][col] = piece[row][thirdCol];
                }
                else if (col == thirdCol){
                    result[row][col] = piece[row][firstCol];
                }
                else {
                    result[row][col] = piece[row][col];
                }
                if (result[row][col].engaged){
                    result[row][col].ontop = !result[row][col].ontop;
                }

            }
        }
        return  result;
    }


    public void print() {
        for(int i = 0; i < piece.length; i++){
            for(int j = 0; j < piece[i].length; j++){
                int v = 0;
                if (piece[i][j].ontop){
                    v = 0;
                }else {
                    v = 1;
                }
                System.out.print(v);
            }
            System.out.println("");
        }

        System.out.println(" ");

        for(int i = 0; i < piece.length; i++){
            for(int j = 0; j < piece[i].length; j++){
                int v = 0;
                if (piece[i][j].engaged){
                    v = 1;
                }else {
                    v = 0;
                }
                System.out.print(v);
            }
            System.out.println("");
        }
    }

}
