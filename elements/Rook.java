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

        if (isThreateningKing(new Position((char) ('a' + newCol), newRow))) {
            // Le roi adverse est menacé
            // Vous pouvez réagir en conséquence, par exemple en signalant un échec
            return true;
        }

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
            if (board[newRow][newCol].getElement() != null && board[currentRow][currentCol].getElement().getColor() == board[newRow][newCol].getElement().getColor()) {
                return false;
            }
            // Le mouvement est valide
            return true;
        }

        return false; // Déplacement invalide
    }

    private boolean isThreateningKing(Position kingPosition) {
        int newRow = kingPosition.getRow();
        int newCol = kingPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        // Vérifie si la tour menace le roi (en ligne droite)
        return newRow == currentRow || newCol == currentCol;
    }
    @Override
    public String toString() {
        return "R";
    }
}
