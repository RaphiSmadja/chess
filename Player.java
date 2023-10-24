package A1D;

import A1D.elements.King;
import A1D.elements.Piece;

public class Player {
    private String name;
    private int color;

    private Position kingPosition;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Position getKingPosition() {
        return kingPosition;
    }

    public void setKingPosition(Position kingPosition) {
        this.kingPosition = kingPosition;
    }
}
