package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Knight extends Piece {

    public Knight(int color, Position position) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        return false;
    }

    @Override
    public String toString() {
        return "N";
    }

}
