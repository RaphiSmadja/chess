package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Rook extends Element{

    public Rook(Position position, int color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        return false;
    }

    @Override
    public String toString() {
        return "R";
    }
}
