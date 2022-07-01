package tictactoe;

import java.util.Random;

public class AI {

    public void input(String mode, Board board, char turn) {
        switch (mode) {
            case "easy" -> {
                System.out.println("Making move level \"easy\"");
                easyMove(board, turn);
            }
            case "medium" -> {
                System.out.println("Making move level \"medium\"");
                mediumMove(board, turn);
            }
            case "hard" -> {
                System.out.println("Making move level \"hard\"");
                hardMove(board, turn);
            }
        }
    }

    public void easyMove(Board board, char turn) {
        char[][] board_cells = board.getBoard_cells();
        Random rand = new Random();
        boolean valid = false;
        while (!valid) {
            valid = true;
            int row_index = rand.nextInt(3);
            int col_index = rand.nextInt(3);
            if (board_cells[row_index][col_index] != ' ') {
                valid = false;
                continue;
            }
            board.updateCell(row_index, col_index, turn);
        }
    }

    public void mediumMove(Board board, char turn) {
        char[][] board_cells = board.getBoard_cells();

        // checks if there is any winning move
        for (int row_index = 0; row_index < board.getRow(); row_index++) {
            for (int col_index = 0; col_index < board.getCol(); col_index++) {
                if (board_cells[row_index][col_index] == ' ') {
                    board.updateCell(row_index, col_index, turn);
                    if (!(board.result().equals(turn + " wins"))) {
                        board.updateCell(row_index, col_index, ' ');
                    } else {
                        // System.out.println("winning move");
                        return;
                    }
                }
            }
        }

        char oppTurn = 'X';
        if (turn == 'X') oppTurn = 'O';

        // checks if opponent has a winning move
        // and tries to stop opponent from winning
        for (int row_index = 0; row_index < board.getRow(); row_index++) {
            for (int col_index = 0; col_index < board.getCol(); col_index++) {
                if (board_cells[row_index][col_index] == ' ') {
                    board.updateCell(row_index, col_index, oppTurn);
                    if (!(board.result().equals(oppTurn + " wins"))) {
                        board.updateCell(row_index, col_index, ' ');
                    } else {
                        board.updateCell(row_index, col_index, turn);
                        return;
                    }
                }
            }
        }

        easyMove(board, turn);
    }

    public void hardMove(Board board, char turn) {
        int[] bestMove = new int[2];
        char[][] board_cells = board.getBoard_cells();
        int bestScore = Integer.MIN_VALUE;
        int score;
        for (int row_index = 0; row_index < board.getRow(); row_index++) {
            for (int col_index = 0; col_index < board.getCol(); col_index++) {
                if (board_cells[row_index][col_index] == ' ') {
                    board.updateCell(row_index, col_index, turn);
                    score = minimax(board, turn, false);
                    board.updateCell(row_index, col_index, ' ');
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row_index, col_index};
                    }
                }
            }
        }
        board.updateCell(bestMove[0], bestMove[1], turn);
    }

    int minimax(Board board, char turn, boolean isMaximizing) {
        char[][] board_cells = board.getBoard_cells();

        int score;

        char oppTurn = 'X';
        if (turn == 'X') oppTurn = 'O';

        if (board.result().equals(turn + " wins")) return 1;
        else if (board.result().equals(oppTurn + " wins")) return -1;
        else if (board.result().equals("Draw")) return 0;

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int row_index = 0; row_index < board.getRow(); row_index++) {
                for (int col_index = 0; col_index < board.getCol(); col_index++) {
                    if (board_cells[row_index][col_index] == ' ') {
                        board.updateCell(row_index, col_index, turn);
                        score = minimax(board, turn, false);
                        board.updateCell(row_index, col_index, ' ');
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int row_index = 0; row_index < board.getRow(); row_index++) {
                for (int col_index = 0; col_index < board.getCol(); col_index++) {
                    if (board_cells[row_index][col_index] == ' ') {
                        board.updateCell(row_index, col_index, oppTurn);
                        score = minimax(board, turn, true);
                        board.updateCell(row_index, col_index, ' ');
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
