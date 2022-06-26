package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Game starts
        System.out.print("Enter the cells: > ");
        Scanner sc = new Scanner(System.in);
        String initialState = "";
        boolean valid = false;

        // takes the initial board state
        while (!valid) {
            valid = true;
            initialState = sc.nextLine();
            if (initialState.length() != 9) {
                valid = false;
            }
        }
        // creating board instance
        Board board = new Board(initialState);
        board.displayBoard();

        int[] coordinates = new int[2];

        valid = false;
        // let the user make a move
        while (!valid) {
            valid = true;
            System.out.print("Enter the coordinates: > ");
            String input = sc.nextLine();
            // takes the coordinate to input the mark
            try {
                coordinates = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                valid = false;
                continue;
            }
            // checks if coordinates are between 1 and 3
            if (coordinates[0] > 3
                    || coordinates[0] < 1
                    || coordinates[1] > 3
                    || coordinates[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                valid = false;
                continue;
            }
            // checks if the selected cell is occupied or not
            char[][] board_cells = board.getBoard_cells();
            int row_index = coordinates[0] - 1;
            int col_index = coordinates[1] - 1;
            if (board_cells[row_index][col_index] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                valid = false;
                continue;
            }

            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < initialState.length(); i++) {
                if (initialState.charAt(i) == 'X') xCount++;
                else if (initialState.charAt(i) == 'O') oCount++;
            }
            // whether to put X or O on the selected cell
            if (xCount <= oCount) {
                board.updateCell(row_index, col_index, 'X');
            } else {
                board.updateCell(row_index, col_index, 'O');
            }
        }

        board.displayBoard();
        board.displayResult();
    }
}
