package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Pawn extends Piece {

    public Pawn(int color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        System.out.println(newPosition);
        return false;
    }

    @Override
    public String toString() {
        return "P";
    }
}
