package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Bishop extends Element{

    public Bishop(Position position, int color) {
        super(position, color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        return false;
    }

    @Override
    public String toString() {
        return "B";
    }

}
