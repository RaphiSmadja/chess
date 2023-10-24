package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Queen extends Piece {

    public Queen(int color, Position position) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int currentRow = getPosition().getRow();
        int currentCol = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn();

        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);

        // Vérifie le mouvement en ligne droite (comme la Tour)
        if ((rowDiff == 0 && colDiff > 0) || (rowDiff > 0 && colDiff == 0)) {
            return isStraightPathClear(currentRow, currentCol, newRow, newCol, board);
        }

        // Vérifie le mouvement en diagonale (comme le Fou)
        if (rowDiff == colDiff) {
            return isDiagonalPathClear(currentRow, currentCol, newRow, newCol, board);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Q";
    }
    private boolean isStraightPathClear(int startRow, int startCol, int endRow, int endCol, Cell[][] board) {
        int rowDirection = (endRow - startRow) / Math.abs(endRow - startRow);
        int colDirection = (endCol - startCol) / Math.abs(endCol - startCol);

        int checkRow = startRow + rowDirection;
        int checkCol = startCol + colDirection;

        while (checkRow != endRow || checkCol != endCol) {
            if (board[checkRow][checkCol].getElement() != null) {
                // Il y a une pièce sur le chemin
                return false;
            }
            checkRow += rowDirection;
            checkCol += colDirection;
        }

        return true;
    }
    private boolean isDiagonalPathClear(int startRow, int startCol, int endRow, int endCol, Cell[][] board) {
        int rowDirection = (endRow - startRow) / Math.abs(endRow - startRow);
        int colDirection = (endCol - startCol) / Math.abs(endCol - startCol);

        int checkRow = startRow + rowDirection;
        int checkCol = startCol + colDirection;

        while (checkRow != endRow && checkCol != endCol) {
            if (board[checkRow][checkCol].getElement() != null) {
                // Il y a une pièce sur le chemin
                return false;
            }
            checkRow += rowDirection;
            checkCol += colDirection;
        }

        return true;
    }
}

