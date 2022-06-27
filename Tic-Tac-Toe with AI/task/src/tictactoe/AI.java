package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class AI {
    int row;
    int col;

    public String easyMove() {
        Random rand = new Random();
        row = rand.nextInt(3) + 1;
        col = rand.nextInt(3) + 1;
        return row + " " + col;
    }

    public void input(String mode, Board board, char mark) {
        int[] coordinates;
        char[][] board_cells = board.getBoard_cells();

        if (mode.equals("easy")) {
            boolean valid = false;
            while (!valid) {
                valid = true;
                String input = easyMove();
                coordinates = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int row_index = coordinates[0] - 1;
                int col_index = coordinates[1] - 1;
                if (board_cells[row_index][col_index] != ' ') {
                    valid = false;
                    continue;
                }

                System.out.println("Making move level \"easy\"");
                board.updateCell(row_index, col_index, mark);
            }
        }
    }

}
