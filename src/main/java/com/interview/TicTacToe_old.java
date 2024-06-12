package com.interview;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: MohamedSiraj
 * Date: 9/6/2024
 * Time: 5:42 PM
 */
@Slf4j
public class TicTacToe_old {


    /**
     * Coding Challenge: Tic-Tac-Toe Analysis
     * <p>
     * Description:
     * In this challenge, you will analyze two files containing tic-tac-toe game states.
     * The first file (`completed_games.txt`) contains 70 completed games, and the second file (`incomplete_games.txt`) contains 70 incomplete
     * games. Your task is to process these files and provide answers to specific questions regarding game outcomes and potential winning
     * moves.
     * <p>
     * Part 1: Completed Games Analysis
     * - Read the `completed_games.txt` file line by line, representing each line as a tic-tac-toe board state.
     * - Implement a function or method that determines the winner of each game (player 'X', player 'O', or a draw).
     * - Count the number of games won by each player ('X' and 'O') and the number of draws.
     * - Display the results in a user-friendly format.
     * <p>
     * Part 2: Incomplete Games Analysis
     * - Read the `incomplete_games.txt` file line by line, representing each line as an incomplete tic-tac-toe board state.
     * - Implement a function or method that determines which player is next to make a move for each game (player 'X' or player 'O').
     * - For each incomplete game, check if the next player has a chance of winning in the next round. If so, output "Next player can win"
     * for that game; otherwise, output "Next player cannot win".
     * - Display the results for each game in a user-friendly format.
     * <p>
     * Additional Requirements:
     * - Your code should be modular and well-documented.
     * - Ensure proper error handling and input validation during file reading and analysis.
     * - Make your solution efficient and optimized to handle large files with many game states efficiently.
     * <p>
     * Bonus Challenge (Optional):
     * - Implement a user interface that allows users to visualize the tic-tac-toe board states and
     * perform the analyses described above interactively.
     */
    public static void main(String[] args) throws IOException {

        char[] game = new char[9];
        String row1 = "=OXXXX=O=";
        AtomicReference<Character> game1 = getOneGame(row1);

        log.info("game result {}", game1.get());


    }

    public static AtomicReference<Character> getOneGame(String row1) throws IOException {
        HashMap<String, Integer> winnerMap = new HashMap<>();

        HashMap<Character, Integer> intermediateMap = new HashMap<>();

        char[] characters = row1.toCharArray();
        int maxRow = 3;
        int maxWinCount = 3;

        AtomicReference<Character> winner = new AtomicReference<>((char) 0);
        AtomicReference<Boolean> gameOver = new AtomicReference<>(false);

        for (char character : characters) {
            for (int i = 0; i < maxRow; i++) {
                if (intermediateMap.get(character) != null) {
                    intermediateMap.put(character, intermediateMap.get(character) + 1);
                } else {
                    intermediateMap.put(character, 1);
                }

                intermediateMap.forEach((k, v) -> {
                    if (v == maxWinCount) {
                        winner.set(k);
                        gameOver.set(true);
                    }
                });
            }

        }

        return winner;
    }


}
