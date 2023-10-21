package A1D;

import A1D.elements.Element;

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
        Element element = new Element();
        int col = 0;
        // ASCII table
        for (int i = 0; i < 8; i++) {
            col = 96;
            for (int j = 0; j < 8; j++) {
                col++;
                if (i == 0 || i == 1 || i == 6 || i == 7) {
                    board[i][j] = new Cell(false, element, new Position((char) col, i));
                } else {
                    board[i][j] = new Cell(true, null, new Position((char) col, i));
                }
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print("[");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getElement());
            }
            System.out.println("]");
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
        return false;
    }

    private void updateBoard(String move) {

    }

    private void switchPlayer() {

    }

}
