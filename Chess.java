package A1D;

import A1D.elements.*;

import java.util.Random;
import java.util.Scanner;

public class Chess {
    private Cell[][] board;
    private Player[] player;
    private Player currentPlayer;

    public void play() {
        while (true) {
            createPlayers();
            initialiseBoard();
            while (!isCheckMate()) {
                printBoard();
                String move;
                do {
                    move = askMove();
                }
                while (!isValidMove(move));
                updateBoard(move);
                switchPlayer();
            }
        }
    }

    private void createPlayers() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Joueur 1 votre nom : ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1);

        System.out.println("Joueur 2 votre nom : ");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2);

        int choiceP1 = random.nextInt(2);

        if (choiceP1 == 0) {
            player1.setColor(0);
            player2.setColor(1);
            currentPlayer = player1;
            player1.setKingPosition(new Position('e', 1));
            player2.setKingPosition(new Position('e', 8));
        } else {
            player1.setColor(1);
            player2.setColor(0);
            currentPlayer = player2;
            player2.setKingPosition(new Position('e', 1));
            player1.setKingPosition(new Position('e', 8));
        }
        player = new Player[]{player1, player2};

    }

    private void initialiseBoard() {
        board = new Cell[8][8];

        // Initialisation des pièces blanches
        board[0][0] = new Cell(false, new Rook(0, new Position('a', 1)), new Position('a', 1));
        board[0][1] = new Cell(false, new Knight(0, new Position('b', 1)), new Position('b', 1));
        board[0][2] = new Cell(false, new Bishop(0, new Position('c', 1)), new Position('c', 1));
        board[0][3] = new Cell(false, new Queen(0, new Position('d', 1)), new Position('d', 1));
        board[0][4] = new Cell(false, new King(0, new Position('e', 1)), new Position('e', 1));
        board[0][5] = new Cell(false, new Bishop(0, new Position('f', 1)), new Position('f', 1));
        board[0][6] = new Cell(false, new Knight(0, new Position('g', 1)), new Position('g', 1));
        board[0][7] = new Cell(false, new Rook(0, new Position('h', 1)), new Position('h', 1));

        for (int i = 0; i < 8; i++) {
            char col = (char) ('a' + i);
            board[1][i] = new Cell(false, new Pawn(0, new Position(col, 2)), new Position(col, 2));
        }

        // Initialisation des pièces noires
        board[7][0] = new Cell(false, new Rook(1, new Position('a', 8)), new Position('a', 8));
        board[7][1] = new Cell(false, new Knight(1, new Position('b', 8)), new Position('b', 8));
        board[7][2] = new Cell(false, new Bishop(1, new Position('c', 8)), new Position('c', 8));
        board[7][3] = new Cell(false, new Queen(1, new Position('d', 8)), new Position('d', 8));
        board[7][4] = new Cell(false, new King(1, new Position('e', 8)), new Position('e', 8));
        board[7][5] = new Cell(false, new Bishop(1, new Position('f', 8)), new Position('f', 8));
        board[7][6] = new Cell(false, new Knight(1, new Position('g', 8)), new Position('g', 8));
        board[7][7] = new Cell(false, new Rook(1, new Position('h', 8)), new Position('h', 8));

        for (int i = 0; i < 8; i++) {
            char col = (char) ('a' + i);
            board[6][i] = new Cell(false, new Pawn(1, new Position(col, 7)), new Position(col, 7));
        }

        // Remplissez le reste du plateau avec des cases vides
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell(true, null, new Position((char) ('a' + col), row + 1));
            }
        }
    }

    private void printBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getElement() != null) {
                    if (board[i][j].getElement().getColor() == 1) {
                        System.out.print("B");
                    } else {
                        System.out.print("W");
                    }
                    System.out.print(board[i][j].getElement());
                } else {
                    System.out.print("[]");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private String askMove() {
        Scanner input = new Scanner(System.in);
        System.out.print("Coup : ");
        return input.nextLine();
    }

    private boolean isCheckMate() {
        Position kingPosition = currentPlayer.getKingPosition();

        if (isInCheck(kingPosition)) {
            // Le roi du joueur actuel est en échec, vérifions s'il y a des mouvements possibles

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Piece piece = board[row][col].getElement();
                    if (piece != null && piece.getColor() == currentPlayer.getColor()) {
                        Position piecePosition = new Position((char) ('a' + col), row + 1);

                        // Essayez tous les mouvements possibles pour cette pièce
                        for (int destRow = 0; destRow < 8; destRow++) {
                            for (int destCol = 0; destCol < 8; destCol++) {
                                Position destPosition = new Position((char) ('a' + destCol), destRow + 1);

                                if (piece.isValidMove(destPosition, board)) {
                                    // Effectuez le mouvement temporairement
                                    Piece destPiece = board[destRow][destCol].getElement();
                                    board[destRow][destCol].setElement(piece);
                                    board[row][col].setElement(null);

                                    // Vérifiez si le roi n'est plus en échec
                                    if (!isInCheck(kingPosition)) {
                                        // Annulez le mouvement et retournez false car le roi n'est plus en échec
                                        board[row][col].setElement(piece);
                                        board[destRow][destCol].setElement(destPiece);
                                        return false;
                                    }

                                    // Annulez le mouvement
                                    board[row][col].setElement(piece);
                                    board[destRow][destCol].setElement(destPiece);
                                }
                            }
                        }
                    }
                }
            }

            // Si aucun mouvement possible n'a permis de sortir le roi de l'échec, c'est un échec et mat.
            System.out.println("Échec et mat. Le joueur " + currentPlayer.getName() + " a perdu.");
            return true;
        }

        return false;
    }

    private boolean isInCheck(Position kingPosition) {
        int row = kingPosition.getRow() - 1;
        int col = kingPosition.getColumn() - 'a';

        // Parcours des cases pour trouver les pièces de l'adversaire
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board[r][c].getElement();
                if (piece != null && piece.getColor() != currentPlayer.getColor()) {
                    Position piecePosition = new Position((char) ('a' + c), r + 1);

                    // Vérifie si cette pièce peut attaquer la position du roi
                    if (piece.isValidMove(kingPosition, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isValidMove(String move) {
        String[] coup = move.split(" ");

        if (coup.length != 2) {
            return false;
        }

        String fromPosition = coup[0];
        String toPosition = coup[1];

        int fromCol = fromPosition.charAt(0) - 'a';
        int fromRow = Integer.parseInt(fromPosition.substring(1)) - 1;

        int toCol = toPosition.charAt(0) - 'a';
        int toRow = Integer.parseInt(toPosition.substring(1)) - 1;

        if (isValidPosition(fromRow, fromCol) && isValidPosition(toRow, toCol)) {
            Piece piece = board[fromRow][fromCol].getElement();

            // Utilisez la valeur de retour pour vérifier si le mouvement est valide
            boolean isValid = piece.isValidMove(new Position(toPosition.charAt(0), toRow), board);

            return isValid;
        }
        return false;
    }


    private boolean isValidPosition(int row, int col) {
        // vérifie que le mouvement est bien dans le cadre
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    private void updateBoard(String move) {
        // Divisez la chaîne de mouvement en ses composants (position de départ et d'arrivée).
        String[] parts = move.split(" ");
        String fromPosition = parts[0];
        String toPosition = parts[1];

        // Convertissez les positions en indices de tableau (ligne et colonne).
        int fromRow = fromPosition.charAt(1) - '1';
        int fromCol = fromPosition.charAt(0) - 'a';
        int toRow = toPosition.charAt(1) - '1';
        int toCol = toPosition.charAt(0) - 'a';

        // Vérifiez si la case de départ est vide.
        if (board[fromRow][fromCol].isEmpty()) {
            System.out.println("Mouvement invalide : la case de départ est vide.");
        }
        // Obtenez la pièce dans la case de départ.
        Piece piece = board[fromRow][fromCol].getElement();
        piece.setPosition(new Position(toPosition.charAt(0), toRow + 1));
        // Effectuez le mouvement en mettant à jour le plateau.
        board[toRow][toCol].setElement(piece);
        board[toRow][toCol].setEmpty(false);
        board[fromRow][fromCol].setElement(null);
        board[fromRow][fromCol].setEmpty(true);
    }

    private void switchPlayer() {
        if (currentPlayer == player[0]) {
            currentPlayer = player[1];
        } else {
            currentPlayer = player[0];
        }
    }

}
