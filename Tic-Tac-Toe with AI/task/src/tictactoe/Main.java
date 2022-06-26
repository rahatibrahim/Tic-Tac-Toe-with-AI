package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Board board = new Board();
    static AI ai = new AI();
    public static void main(String[] args) {
        // Game starts
        int moves = 0;
        // let the user make a move
        while (moves < 9) {
            board.displayBoard();
            System.out.print("Enter the coordinates: > ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (takeInput(input)) {
                board.displayBoard();
                moves++;
            } else {
                continue;
            }
            if (moves == 9) break;
            // ai makes a move
            ai.aiInput("easy", board);
            moves++;

            if (moves > 4 && !board.result().equals("Game not finished")) break;
        }
        board.displayBoard();
        board.displayResult();
    }

    public static boolean takeInput(String input) {
        int[] coordinates;

        // takes the coordinate to input the mark
        try {
            coordinates = Arrays.stream(input.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        // checks if coordinates are between 1 and 3
        if (coordinates[0] > 3
                || coordinates[0] < 1
                || coordinates[1] > 3
                || coordinates[1] < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        // checks if the selected cell is occupied or not
        char[][] board_cells = board.getBoard_cells();
        int row_index = coordinates[0] - 1;
        int col_index = coordinates[1] - 1;
        if (board_cells[row_index][col_index] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board.updateCell(row_index, col_index, 'X');
        return true;
    }
}
