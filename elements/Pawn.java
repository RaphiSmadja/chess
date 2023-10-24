package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Pawn extends Piece {

    public Pawn(int color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        if (getColor() == 0) { // Blanc
            // Avancer d'une case
            if (rowDiff == 1 && colDiff == 0 && board[newRow][newCol].isEmpty()) {
                return true;
            }

            // Avancer de deux cases depuis la ligne de départ
            if (rowDiff == 2 && colDiff == 0 && currentRow == 1 && board[newRow][newCol].isEmpty()) {
                // Vérifier si la case intermédiaire est vide
                return board[currentRow + 1][currentCol].isEmpty();
            }

            // Prise en diagonale
            if (rowDiff == 1 && colDiff == 1 && !board[newRow][newCol].isEmpty()) {
                return board[newRow][newCol].getElement().getColor() == 1;
            }
        } else { // Noir
            // Avancer d'une case
            if (rowDiff == 1 && colDiff == 0 && board[newRow][newCol].isEmpty()) {
                return true;
            }

            // Avancer de deux cases depuis la ligne de départ
            if (rowDiff == 2 && colDiff == 0 && currentRow == 6 && board[newRow][newCol].isEmpty()) {
                // Vérifier si la case intermédiaire est vide
                return board[currentRow - 1][currentCol].isEmpty();
            }

            // Prise en diagonale
            if (rowDiff == 1 && colDiff == 1 && !board[newRow][newCol].isEmpty()) {
                return board[newRow][newCol].getElement().getColor() == 0;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "P";
    }
}
