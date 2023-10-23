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

        int choiceP1 = random.nextInt(0, 2);
        player1.setColor(choiceP1);

        if (choiceP1 == 0) {
            currentPlayer = player1;
            player2.setColor(1);
        } else {
            currentPlayer = player2;
            player2.setColor(0);
        }
        player = new Player[]{player1, player2};

    }

    private void initialiseBoard() {
        board = new Cell[8][8];

        board[0][0] = new Cell(false, new Rook(1), new Position('a', 8));
        board[0][7] = new Cell(false, new Rook(1), new Position('h', 8));

        board[0][1] = new Cell(false, new Knight(1), new Position('b', 8));
        board[0][6] = new Cell(false, new Knight(1), new Position('g', 8));

        board[0][2] = new Cell(false, new Bishop(1), new Position('c', 8));
        board[0][5] = new Cell(false, new Bishop(1), new Position('f', 8));

        board[0][3] = new Cell(false, new Queen(1), new Position('d', 8));
        board[0][4] = new Cell(false, new King(1), new Position('d', 8));

        initialisePawns(1, 1);

        board[7][0] = new Cell(false, new Rook(0), new Position('a', 1));
        board[7][7] = new Cell(false, new Rook(0), new Position('h', 1));

        board[7][1] = new Cell(false, new Knight(0), new Position('b', 1));
        board[7][6] = new Cell(false, new Knight(0), new Position('g', 1));

        board[7][2] = new Cell(false, new Bishop(0), new Position('c', 1));
        board[7][5] = new Cell(false, new Bishop(0), new Position('f', 1));

        board[7][3] = new Cell(false, new Queen(0), new Position('d', 1));
        board[7][4] = new Cell(false, new King(0), new Position('d', 1));


        initialisePawns(6, 0);


        int col = 0;

        // ASCII table
        for (int i = 2; i < 6; i++) {
            col = 96;
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(true, null, new Position((char) col, i));
            }
        }
    }

    private void initialisePawns(int line, int color) {
        int charToInt = 96;
        int row = line + 1;
        for (int i = 0; i < 8; i++) {
            board[line][i] = new Cell(false, new Pawn(color), new Position((char) charToInt++, row));
        }
    }

    private void printBoard() {
        for (int i = 0; i < 8; i++) {
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
        return false;
    }

    private boolean isValidMove(String move) {
        String[] coup = move.split("");
        int col = coup[0].charAt(0) - 97;
        int li = Integer.parseInt(coup[1]) - 1;

        if (board[li][col].isEmpty()) {
            return false;
        }
        Piece piece = board[li][col].getElement();
        piece.setPosition(new Position(coup[3].charAt(0), Integer.parseInt(coup[4])));
        piece.isValidMove(piece.getPosition(), board);
        return false;
    }

    private void updateBoard(String move) {

    }

    private void switchPlayer() {

    }

}
