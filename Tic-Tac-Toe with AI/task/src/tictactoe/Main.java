package tictactoe;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = List.of("user", "easy", "medium", "hard");

        // game starts
        while (true) {
            System.out.print("Input command: > ");
            String input = sc.nextLine();
            if (input.equals("exit")) break;

            String[] gameStats = input.split(" ");
            if (gameStats.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            } else if (!gameStats[0].equals("start")) {
                System.out.println("Bad parameters!");
                continue;
            } else if (!(list.contains(gameStats[1]))) {
                System.out.println("Bad parameters!");
                continue;
            } else if (!(list.contains(gameStats[2]))) {
                System.out.println("Bad parameters!");
                continue;
            }

            Game game = new Game();
            game.start(gameStats[1], gameStats[2]);
        }
    }
}
