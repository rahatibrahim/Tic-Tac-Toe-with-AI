package tictactoe;

public class Board {
    final int row = 3;
    final int col = 3;
    char[][] board_cells = new char[row][col];

    public Board(String state) {
        int c = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (state.charAt(c) == '_') {
                    board_cells[i][j] = ' ';
                } else {
                    board_cells[i][j] = state.charAt(c);
                }
                c++;
            }
        }
    }

    public void displayBoard() {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n",
                this.board_cells[0][0], this.board_cells[0][1], this.board_cells[0][2]);
        System.out.printf("| %s %s %s |\n",
                this.board_cells[1][0], this.board_cells[1][1], this.board_cells[1][2]);
        System.out.printf("| %s %s %s |\n",
                this.board_cells[2][0], this.board_cells[2][1], this.board_cells[2][2]);
        System.out.println("---------");
    }

    public String result() {
        // checks rows and columns
        for (int i = 0; i < 3; i++) {
            if (board_cells[i][0] != ' '
                    && board_cells[i][0] == board_cells[i][1]
                    && board_cells[i][1] == board_cells[i][2])
                return board_cells[i][0] + " wins";
            else if (board_cells[0][i] != ' '
                    && board_cells[0][i] == board_cells[1][i]
                    && board_cells[1][i] == board_cells[2][i])
                return board_cells[0][i] + " wins";
        }
        // checks 2 diagonals
        if (board_cells[0][0] != ' '
                && board_cells[0][0] == board_cells[1][1]
                && board_cells[1][1] == board_cells[2][2])
            return board_cells[0][0] + " wins";
        else if (board_cells[0][2] != ' '
                && board_cells[0][2] == board_cells[1][1]
                && board_cells[1][1] == board_cells[2][0])
            return board_cells[0][2] + " wins";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board_cells[i][j] == ' ') return "Game not finished";
            }
        }

        return "Draw";
    }

    public void displayResult() {
        System.out.println(result());
    }

    public void updateCell(int row, int col, char value) {
        this.board_cells[row][col] = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char[][] getBoard_cells() {
        return board_cells;
    }

    public void setBoard_cells(char[][] board_cells) {
        this.board_cells = board_cells;
    }
}
