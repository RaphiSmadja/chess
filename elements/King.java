package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class King extends Piece {

    public King(int color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        return false;
    }

    @Override
    public String toString() {
        return "K";
    }
}
