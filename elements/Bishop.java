package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Bishop extends Piece {

    public Bishop(int color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int currentRow = getPosition().getRow();
        int currentCol = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn()-'a';

        // Vérifie si le mouvement est diagonal
        if (Math.abs(newRow - currentRow) != Math.abs(newCol - currentCol)) {
            return false;
        }

        // Vérifie si le chemin est dégagé (pas de pièces entre la position actuelle et la nouvelle position)
        int rowDirection = (newRow - currentRow) / Math.abs(newRow - currentRow);
        int colDirection = (newCol - currentCol) / Math.abs(newCol - currentCol);

        int checkRow = currentRow + rowDirection;
        int checkCol = currentCol + colDirection;

        while (checkRow != newRow && checkCol != newCol) {
            if (board[checkRow][checkCol].getElement() != null) {
                // Il y a une pièce sur le chemin
                return false;
            }
            checkRow += rowDirection;
            checkCol += colDirection;
        }

        // Vérifie si la case de destination est vide ou a une pièce de couleur opposée
        return board[newRow][newCol].isEmpty() || board[newRow][newCol].getElement().getColor() != getColor();
    }

    @Override
    public String toString() {
        return "B";
    }

}
