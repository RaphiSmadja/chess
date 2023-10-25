package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Bishop extends Piece {

    public Bishop(int color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        if (isThreateningKing(new Position((char) ('a' + newCol), newRow))) {
            // Le roi adverse est menacé
            // Vous pouvez réagir en conséquence, par exemple en signalant un échec
            return true;
        }

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

    private boolean isThreateningKing(Position kingPosition) {
        int newRow = kingPosition.getRow();
        int newCol = kingPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Vérifie si le fou menace le roi (en diagonale)
        return rowDiff == colDiff;
    }

    @Override
    public String toString() {
        return "B";
    }

}
