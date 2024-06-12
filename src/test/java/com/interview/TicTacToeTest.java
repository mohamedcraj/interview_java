package com.interview;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Author: MohamedSiraj
 * Date: 9/6/2024
 * Time: 5:42 PM
 */
@Slf4j
public class TicTacToeTest {

    @Test
    public void testGameOver() {
        String string = "XXXOOXXOX";
        Assertions.assertTrue(TicTacToe.isGameOver(string));
    }

    @Test
    public void testGameNotOver() {
        String string = "XXXOOXXO";
        Assertions.assertFalse(TicTacToe.isGameOver(string));
    }

    @Test
    public void testInvalidGame() {
        String string = "XXX=OX=O==";
        Assertions.assertFalse(TicTacToe.isGameOver(string));
    }

    @Test
    public void getHorizontalWinner() {
        String string = "XXXOOXOOX";
        Assertions.assertEquals('X', TicTacToe.getWinner(string));
    }

    @Test
    public void getVerticalWinner() {
        String string = "XOX=OX=O=";
        Assertions.assertEquals('O', TicTacToe.getWinner(string));
    }

    @Test
    public void getDiagonalWinner() {
        String string = "XXO=XO=OX";
        Assertions.assertEquals('X', TicTacToe.getWinner(string));
    }

}
