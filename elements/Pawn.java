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

        int rowDiff = Math.abs(newRow - this.getPosition().getRow());
        int colDiff = Math.abs(newCol - (this.getPosition().getColumn() - 'a'));

        // Les pions ont des règles de déplacement spécifiques, en fonction de la couleur et de la direction
        // Vous devez implémenter les règles complètes ici, y compris la première avance, la prise en passant, la promotion, etc.
        // Voici un exemple simplifié pour un pion qui avance d'une case.
        if (rowDiff == 1 && colDiff == 0) {
            // Valide si la case cible est vide
            return board[newRow][newCol].isEmpty();
        }

        return false;
    }

    @Override
    public String toString() {
        return "P";
    }
}
