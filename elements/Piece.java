package A1D.elements;

import A1D.Cell;
import A1D.Position;

public abstract class Piece {
    protected Position position;
    protected int color;

    public Piece(int color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Position newPosition, Cell[][] board);

    public abstract String toString();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
