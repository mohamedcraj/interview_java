package com.interview.v1;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class TicTacToe {
    private final static int MAX_ROUND_PER_GAME = 9;
    private final static String WINNER = "WINNER";
    private final static String NEXT_PLAYER = "NEXT_PLAYER";
    private final static char PLAYER_X = 'X';
    private final static char PLAYER_O = 'O';

    public static void main(String[] args) {
        String fileName = "src/main/resources/completed_games.txt";
        processGameFileData(fileName, WINNER);

        fileName = "src/main/resources/incomplete_games.txt";
        processGameFileData(fileName, NEXT_PLAYER);
    }

    private static void processGameFileData(String fileName, String type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (type.equalsIgnoreCase(WINNER)) {
                    String winner = String.valueOf(getWinner(line));
                    log.info("Winner from Line {} ==> {}", i, winner.equals("D") ? "Draw" : winner);
                } else if (type.equalsIgnoreCase(NEXT_PLAYER)) {
                    String winner = String.valueOf(getNextPlayer(line));
                    log.info("Next play for Line {} ==> {}", i, winner.equals("D") ? "Draw" : winner);
                } else {
                    throw new RuntimeException("Invalid Type");
                }
            }
        } catch (IOException e) {
            log.error("Exception Occurred: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            log.error("Exception Occurred: {}", e.getMessage());
        }
    }

    public static boolean isGameOver(String string) {
        char[] inputGameCharacters = string.toCharArray();
        if (inputGameCharacters.length > MAX_ROUND_PER_GAME) {
            log.error("InvalidGame");
            return false;
        } else if (inputGameCharacters.length < MAX_ROUND_PER_GAME) {
            return false;
        }
        return true;
    }

    public static char getWinner(String string) {
        char[] inputGameCharacters = string.toCharArray();
        Character winner = getWinner(inputGameCharacters);
        if (winner != null) return winner;

        return 'D';

    }

    private static Character getWinner(char[] inputGameCharacters) {
        //horizontal
        for (int i = 0; i < MAX_ROUND_PER_GAME; i += 3) {
            if (inputGameCharacters[i] != ' ' && inputGameCharacters[i] == inputGameCharacters[i + 1] && inputGameCharacters[i] == inputGameCharacters[i + 2]) {
                return inputGameCharacters[i];
            }
        }

        //vertical
        for (int i = 0; i < 3; i++) {
            if (inputGameCharacters[i] != ' ' && inputGameCharacters[i] == inputGameCharacters[i + 3] && inputGameCharacters[i] == inputGameCharacters[i + 6]) {
                return inputGameCharacters[i];
            }
        }

        //diagonal
        if (inputGameCharacters[0] != ' ' && inputGameCharacters[0] == inputGameCharacters[4] && inputGameCharacters[0] == inputGameCharacters[8]) {
            return inputGameCharacters[0];
        }
        if (inputGameCharacters[2] != ' ' && inputGameCharacters[2] == inputGameCharacters[4] && inputGameCharacters[2] == inputGameCharacters[6]) {
            return inputGameCharacters[2];
        }
        return null;
    }

    public static char getNextPlayer(String string) {
        char[] inputGameCharacters = string.toCharArray();
        Character winner = getNextPlayer(inputGameCharacters);
        if (winner != null) return winner;

        return ' ';

    }

    private static Character getNextPlayer(char[] inputGameCharacters) {
        if (inputGameCharacters.length == 0 || inputGameCharacters.length > MAX_ROUND_PER_GAME) return null;

        //total X
        int total_x_played_rounds = 0;
        for (int i = 0; i < inputGameCharacters.length; i++) {
            if (inputGameCharacters[i] == PLAYER_X) {
                total_x_played_rounds = total_x_played_rounds + 1;
            }
        }

        //total O
        int total_o_played_rounds = 0;
        for (int i = 0; i < inputGameCharacters.length; i++) {
            if (inputGameCharacters[i] == PLAYER_O) {
                total_o_played_rounds = total_o_played_rounds + 1;
            }
        }

        if (total_o_played_rounds > total_x_played_rounds) {
            return PLAYER_X;
        } else {
            return PLAYER_O;
        }
    }

}
