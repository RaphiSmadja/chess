package A1D.elements;

import A1D.Cell;
import A1D.Position;

public class Queen extends Piece {

    public Queen(int color, Position position) {
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
        int rowDirection, colDirection;
        if (endRow - startRow != 0) {
            rowDirection = (endRow - startRow) / Math.abs(endRow - startRow);
        } else {
            rowDirection = 0;
        }
        if (endCol - startCol != 0) {
            colDirection = (endCol - startCol) / Math.abs(endCol - startCol);
        } else {
            colDirection = 0;
        }
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

