package com.interview;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class TicTacToe {
    private final static int maxRoundPerGame = 9;

    public static void main(String[] args) {
        String fileName = "src/main/resources/completed_games.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                String winner = String.valueOf(getWinner(line));
                log.info("Winner from Line {} ==> {}", i, winner.equals("D") ? "Draw" : winner);
            }
        } catch (IOException e) {

        }
    }

    public static boolean isGameOver(String string) {
        char[] inputGameCharacters = string.toCharArray();
        if (inputGameCharacters.length > maxRoundPerGame) {
            log.error("InvalidGame");
            return false;
        }
        for (char gameCharacter : inputGameCharacters) {
            if (gameCharacter == '=') {
                return false;
            }
        }
        return true;
    }

    public static char getWinner(String string) {
        char[] inputGameCharacters = string.toCharArray();
        //horizontal
        for (int i = 0; i < maxRoundPerGame; i += 3) {
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

        return 'D';

    }

}
