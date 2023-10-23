package A1D;

import A1D.elements.Piece;

public class Cell {
    private Position position = null;
    private boolean isEmpty;
    private Piece piece;

    public Cell(boolean isEmpty, Piece piece, Position position) {
        this.isEmpty = isEmpty;
        this.piece = piece;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Piece getElement() {
        return piece;
    }

    public void setElement(Piece piece) {
        this.piece = piece;
    }
}
