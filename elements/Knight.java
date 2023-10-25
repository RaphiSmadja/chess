package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Knight extends Piece {

    public Knight(int color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        // Vérifie si le cavalier menace le roi adverse
        if (isThreateningKing(new Position((char) ('a' + newCol), newRow))) {
            // Le roi adverse est menacé
            // Vous pouvez réagir en conséquence, par exemple en signalant un échec
            return true;
        }

        // Vérifie les différents mouvements en L possibles pour un cavalier
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    private boolean isThreateningKing(Position kingPosition) {
        int newRow = kingPosition.getRow();
        int newCol = kingPosition.getColumn() - 'a';
        int currentRow = getPosition().getRow() - 1;
        int currentCol = getPosition().getColumn() - 'a';

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Vérifie si le cavalier menace le roi avec son mouvement en L
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String toString() {
        return "N";
    }

}
