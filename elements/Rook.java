package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Rook extends Piece {

    public Rook(int color, Position position) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn() - 'a';

        int rowDiff = newRow - this.getPosition().getRow();
        int colDiff = newCol - this.getPosition().getColumn() - 'a';

        if (board[rowDiff][colDiff].isEmpty()){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "R";
    }
}
