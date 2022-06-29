package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class User {

    public boolean input(Board board, char mark) {
        System.out.print("Enter the coordinates: > ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
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
        try {
            if (coordinates[0] > 3
                    || coordinates[0] < 1
                    || coordinates[1] > 3
                    || coordinates[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException o) {
            System.out.println("Must enter two numbers!");
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

        board.updateCell(row_index, col_index, mark);
        return true;
    }
}
