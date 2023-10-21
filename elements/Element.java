package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Element {
    private Position position;
    private int color;

    public Element(Position position, int color) {
        this.position = position;
        this.color = color;
    }

    public Element() {

    }

    public boolean isValidMove(Position newPosition, Cell[][] board) {
        return false;
    }

    public String toString() {
        return "E";
    }
}
