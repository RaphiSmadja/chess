package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class King extends Piece {

    public King(int color, Position position) {
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

        // Vérifie si le mouvement est d'une seule case dans n'importe quelle direction
        if (Math.abs(newRow - currentRow) <= 1 && Math.abs(newCol - currentCol) <= 1) {

            // Vérifie si la case de destination est vide ou a une pièce de couleur opposée
            return board[newRow][newCol].isEmpty() || board[newRow][newCol].getElement().getColor() != getColor();
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

        // Vérifie si le roi menace le roi adverse (en déplacement d'une case)
        return rowDiff <= 1 && colDiff <= 1;
    }

    @Override
    public String toString() {
        return "K";
    }
}
