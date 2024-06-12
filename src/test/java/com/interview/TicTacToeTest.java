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
    public void testHorizontalWinner() {
        String string = "XXXOOXOOX";
        Assertions.assertEquals('X', TicTacToe.getWinner(string));
    }

    @Test
    public void testVerticalWinner() {
        String string = "XOX=OX=O=";
        Assertions.assertEquals('O', TicTacToe.getWinner(string));
    }

    @Test
    public void testDiagonalWinner() {
        String string = "XXO=XO=OX";
        Assertions.assertEquals('X', TicTacToe.getWinner(string));
    }

    @Test
    public void testNextPlayerX() {
        String string = "X        ";
        Assertions.assertEquals('X', TicTacToe.getNextPlayer(string));
    }

    @Test
    public void testNextPlayerO() {
        String string = "O        ";
        Assertions.assertEquals('O', TicTacToe.getNextPlayer(string));
    }

}
