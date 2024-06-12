package com.interview;

public class TicTacToe {
    private final static int maxRoundPerGame = 9;

    public static void main(String[] args) {

        String gameInput = "XOX=OX=O=";
        boolean isGameOver = isGameOver(gameInput.toCharArray());
        System.out.println(isGameOver);


        gameInput = "XOX=OX=O ";
        isGameOver = isGameOver(gameInput.toCharArray());
        System.out.println(isGameOver);

        gameInput = "XOX=OX=O";
        isGameOver = isGameOver(gameInput.toCharArray());
        System.out.println(isGameOver);
    }

    public static boolean isGameOver(char[] inputGameCharacters) {

        int i = 0;
//        if (inputGameCharacters.length == maxRoundPerGame) {
//            return true;
//        } else if (inputGameCharacters.length > maxRoundPerGame) {
//            return false;
//        } else {
//            return
//            //check horizontal
//            return (inputGameCharacters[i] != ' ' && inputGameCharacters[i] == inputGameCharacters[i + 1] && inputGameCharacters[i] == inputGameCharacters[i + 2])
//                    //check vertical
//                    || (inputGameCharacters[i] != ' ' && inputGameCharacters[i] == inputGameCharacters[i + 3] && inputGameCharacters[i] == inputGameCharacters[i + 6])
//                    //check diagonal
//                    || (inputGameCharacters[i] != ' ' && inputGameCharacters[i] == inputGameCharacters[i + 4] && inputGameCharacters[i] == inputGameCharacters[i + 8]);
//
//        }

    }

    public static String getWinner() {
        return null;
    }
}
