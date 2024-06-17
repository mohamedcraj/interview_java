package com.interview;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author mohamedcraj
 * Date: 16/6/2024
 */
@Slf4j
public class TicTacToeV2 {

    public static final int MAX_BOARD_SIZE = 9;
    public static final int MAX_BOARD_ROW_COL = 3;
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
                    identifyWinner(line);
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

    static void identifyWinner(String gameBoard) {
        ResultTuple<Character, Boolean> isWinnerAlreadyIdentified = null;
        char[][] currentBoard = getBoard(gameBoard);
        for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
            assert currentBoard != null;
            isWinnerAlreadyIdentified = isWinnerAtHorizontal(currentBoard[i]);
            if (isWinnerAlreadyIdentified.getResult()) {
                System.out.println("Winner found at Row [" + i + "] is - [" + isWinnerAlreadyIdentified.getPlayer() + "]");
                break;
            }
        }

        if (!isWinnerAlreadyIdentified.getResult()) {
            for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
                isWinnerAlreadyIdentified = isWinnerAtVertical(currentBoard, i);
                if (isWinnerAlreadyIdentified.getResult()) {
                    System.out.println("Winner found at Column [" + i + "] - [" + isWinnerAlreadyIdentified.getPlayer() + "]");
                    break;
                }
            }
        }

        if (!isWinnerAlreadyIdentified.getResult()) {
            char[] leftDiagonal = new char[3];
            for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
                leftDiagonal[i] = currentBoard[i][i];
            }

            isWinnerAlreadyIdentified = isWinnerAtDiagonal(leftDiagonal);
            if (isWinnerAlreadyIdentified.getResult()) {
                System.out.println("Winner found at Left diagonal - [" + isWinnerAlreadyIdentified.getPlayer() + "]");
            }
        }

        if (!isWinnerAlreadyIdentified.getResult()) {
            char[] rightDiagonal = new char[3];
            int j = MAX_BOARD_ROW_COL - 1;
            for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
                rightDiagonal[i] = currentBoard[i][j];
                j--;
            }

            isWinnerAlreadyIdentified = isWinnerAtDiagonal(rightDiagonal);
            if (isWinnerAlreadyIdentified.getResult()) {
                System.out.println("Winner found at Right diagonal - [" + isWinnerAlreadyIdentified.getPlayer() + "]");
            }
        }

        if (!isWinnerAlreadyIdentified.getResult()) {
            System.out.println("No Winner found at Row / Column / Left Diagonal / Right Diagonal - Match Draw - [D]");
        }
        System.out.println("-------------");
    }

    private static char[][] getBoard(String gameCurrentState) {
        char[] boardArray = gameCurrentState.toCharArray();

        if (boardArray.length > MAX_BOARD_SIZE) return null;
        char[][] board = new char[3][3];
        int val = 0;
        for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
            for (int j = 0; j < MAX_BOARD_ROW_COL; j++) {
                board[i][j] = boardArray[val];
                val++;
            }
        }
        printBoard(board);
        return board;
    }

    public static void printBoard(char[][] currentBoard) {
        for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
            System.out.print("|");
            for (int j = 0; j < MAX_BOARD_ROW_COL; j++) {
                System.out.print(currentBoard[i][j] + "|");
            }
            System.out.println();
        }
    }

    static ResultTuple<Character, Boolean> isWinnerAtHorizontal(char[] currentBoardRow) {
        for (int i = 0; i < MAX_BOARD_ROW_COL; i++) {
            boolean result = currentBoardRow[0] == currentBoardRow[i];
            if (!result) {
                return new ResultTuple<>(currentBoardRow[0], false);
            }
        }
        return new ResultTuple<>(currentBoardRow[0], true);
    }

    static ResultTuple<Character, Boolean> isWinnerAtVertical(char[][] currentBoard, int col) {
        char comparisonCell = currentBoard[0][col];
        for (int j = 0; j < MAX_BOARD_ROW_COL; j++) {
            boolean result = comparisonCell == currentBoard[j][col];
            if (!result) {
                return new ResultTuple<>(comparisonCell, false);
            }
        }
        return new ResultTuple<>(comparisonCell, true);
    }

    static ResultTuple<Character, Boolean> isWinnerAtDiagonal(char[] diagonal) {
        char intVal = diagonal[0];
        for (char c : diagonal) {
            if (intVal != c) {
                return new ResultTuple<>(intVal, false);
            }
        }
        return new ResultTuple<>(intVal, true);
    }

    @Getter
    static class ResultTuple<T1, T2> {
        private final T1 player;
        private final T2 result;

        public ResultTuple(T1 player, T2 result) {
            this.player = player;
            this.result = result;
        }

    }


    private static Character getNextPlayer(String gameBoard) {

        char[] inputGameCharacters = gameBoard.toCharArray();
        if (inputGameCharacters.length == 0 || inputGameCharacters.length > MAX_BOARD_SIZE) return null;

        //total X
        int total_x_played_rounds = 0;
        for (char inputGameCharacter : inputGameCharacters) {
            if (inputGameCharacter == PLAYER_X) {
                total_x_played_rounds = total_x_played_rounds + 1;
            }
        }

        //total O
        int total_o_played_rounds = 0;
        for (char inputGameCharacter : inputGameCharacters) {
            if (inputGameCharacter == PLAYER_O) {
                total_o_played_rounds = total_o_played_rounds + 1;
            }
        }

        if (total_o_played_rounds > total_x_played_rounds) {
            return PLAYER_O;
        } else {
            return PLAYER_X;
        }
    }
}
