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

        // Vérifie si le pion menace le roi adverse
        if (isThreateningKing(new Position((char) ('a' + newCol), newRow))) {
            // Le roi adverse est menacé
            // Vous pouvez réagir en conséquence, par exemple en signalant un échec
            return true;
        }

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

    private boolean isThreateningKing(Position kingPosition) {
        int newRow = kingPosition.getRow();
        int newCol = kingPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Vérifie si le pion menace le roi (en diagonale)
        return rowDiff == 1 && colDiff == 1;
    }

    @Override
    public String toString() {
        return "P";
    }
}
