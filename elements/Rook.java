package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Rook extends Piece {

    public Rook(int color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        int rowDiff = newRow - currentRow;
        int colDiff = newCol - currentCol;

        if (rowDiff == 0 || colDiff == 0) {
            // Vérifiez si le chemin est dégagé horizontalement
            if (rowDiff == 0) {
                int step = Integer.compare(colDiff, 0);
                for (int col = currentCol + step; col != newCol; col += step) {
                    if (!board[currentRow][col].isEmpty()) {
                        return false; // Il y a une pièce sur le chemin
                    }
                }
            }
            // Vérifiez si le chemin est dégagé verticalement
            else if (colDiff == 0) {
                int step = Integer.compare(rowDiff, 0);
                for (int row = currentRow + step; row != newRow; row += step) {
                    if (!board[row][currentCol].isEmpty()) {
                        return false; // Il y a une pièce sur le chemin
                    }
                }
            }

            // Le mouvement est valide
            return true;
        }

        return false; // Déplacement invalide
    }

    @Override
    public String toString() {
        return "R";
    }
}
