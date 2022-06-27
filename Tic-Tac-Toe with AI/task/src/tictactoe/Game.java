package tictactoe;

public class Game {

    public void start(String player1, String player2) {
        User user = new User();
        AI ai = new AI();
        Board board = new Board();
        board.displayBoard();

        while (true) {
            if (player1.equals("user")) {
                if (user.input(board, 'X')) {
                    board.displayBoard();
                } else {
                    continue;
                }
            } else {
                // ai makes a move
                ai.input("easy", board, 'X');
                board.displayBoard();
            }

            if (!board.result().equals("Game not finished")) break;

            if (player2.equals("user")) {
                if (user.input(board, 'O')) {
                    board.displayBoard();
                } else {
                    continue;
                }
            } else {
                // ai makes a move
                ai.input("easy", board, 'O');
                board.displayBoard();
            }

            if (!board.result().equals("Game not finished")) break;
        }

        board.displayResult();
        System.out.println();
    }
}
