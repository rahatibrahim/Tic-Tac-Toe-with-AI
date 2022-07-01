package tictactoe;

public class Game {

    public void start(String player1, String player2) {
        User user = new User();
        AI ai = new AI();
        Board board = new Board();
        board.displayBoard();

        while (true) {
            if (player1.equals("user")) {
                // user makes a move
                boolean flag = false;
                while (!flag) {
                    flag = user.input(board, 'X');
                }
                board.displayBoard();
            } else {
                // ai makes a move
                ai.input(player1, board, 'X');
                board.displayBoard();
            }

            if (!board.result().equals("Game not finished")) break;

            if (player2.equals("user")) {
                // user makes a move
                boolean flag = false;
                while (!flag) {
                    flag = user.input(board, 'O');
                }
                board.displayBoard();
            } else {
                // ai makes a move
                ai.input(player2, board, 'O');
                board.displayBoard();
            }

            if (!board.result().equals("Game not finished")) break;
        }

        board.displayResult();
        System.out.println();
    }
}
