package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class King extends Piece {

    public King(int color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int currentRow = getPosition().getRow();
        int currentCol = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn();

        // Vérifie si le mouvement est d'une seule case dans n'importe quelle direction
        if (Math.abs(newRow - currentRow) <= 1 && Math.abs(newCol - currentCol) <= 1) {

            // Vérifie si la case de destination est vide ou a une pièce de couleur opposée
            return board[newRow][newCol].isEmpty() || board[newRow][newCol].getElement().getColor() != getColor();
        }

        return false;
    }

    @Override
    public String toString() {
        return "K";
    }
}
