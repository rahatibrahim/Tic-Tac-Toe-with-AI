package tictactoe;

import java.util.Random;

public class AI {

    public void easyMove(Board board, char mark) {
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
            board.updateCell(row_index, col_index, mark);
        }
    }

    public void mediumMove(Board board, char mark) {
        char[][] board_cells = board.getBoard_cells();

        // checks if there is any winning move
        for (int row_index = 0; row_index < board.getRow(); row_index++) {
            for (int col_index = 0; col_index < board.getCol(); col_index++) {
                if (board_cells[row_index][col_index] == ' ') {
                    board.updateCell(row_index, col_index, mark);
                    if (!(board.result().equals(mark + " wins"))) {
                        board.updateCell(row_index, col_index, ' ');
                    } else {
                        // System.out.println("winning move");
                        return;
                    }
                }
            }
        }

        char oppMark = ' ';
        if (mark == 'X') oppMark = 'O';
        else if (mark == 'O') oppMark = 'X';

        // checks if opponent has a winning move
        // and tries to stop opponent from winning
        for (int row_index = 0; row_index < board.getRow(); row_index++) {
            for (int col_index = 0; col_index < board.getCol(); col_index++) {
                if (board_cells[row_index][col_index] == ' ') {
                    board.updateCell(row_index, col_index, oppMark);
                    if (!(board.result().equals(oppMark + " wins"))) {
                        board.updateCell(row_index, col_index, ' ');
                    } else {
                        board.updateCell(row_index, col_index, mark);
                        return;
                    }
                }
            }
        }

        easyMove(board, mark);
    }

    public void input(String mode, Board board, char mark) {
        if (mode.equals("easy")) {
            System.out.println("Making move level \"easy\"");
            easyMove(board, mark);
        } else if (mode.equals("medium")) {
            System.out.println("Making move level \"medium\"");
            mediumMove(board, mark);
        }
    }
}
