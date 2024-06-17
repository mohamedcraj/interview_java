package com.interview.v2;

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
    public void testHorizontalRowOneWinner() {
        String string = "XXXOXOXOX";
        Assertions.assertEquals("Winner found at Row [0] is - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testHorizontalRowTwoWinner() {
        String string = "OXOXXXXOX";
        Assertions.assertEquals("Winner found at Row [1] is - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testHorizontalRowThreeWinner() {
        String string = "OXOXOXXXX";
        Assertions.assertEquals("Winner found at Row [2] is - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testVerticalColumnOneWinner() {
        String string = "OOXOXOOXX";
        Assertions.assertEquals("Winner found at Column [0] - [O]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testVerticalColumnTwoWinner() {
        String string = "XOXOOXXOO";
        Assertions.assertEquals("Winner found at Column [1] - [O]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testVerticalColumnThreeWinner() {
        String string = "OOXXOXOXX";
        Assertions.assertEquals("Winner found at Column [2] - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testLeftDiagonalWinner() {
        String string = "XXO=XO=OX";
        Assertions.assertEquals("Winner found at Left diagonal - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testRightDiagonalWinner() {
        String string = "XOXOXOXXO";
        Assertions.assertEquals("Winner found at Right diagonal - [X]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testNoWinner() {
        String string = "XOOOXXOXO";
        Assertions.assertEquals("No Winner found at Row / Column / Left Diagonal / Right Diagonal - Match Draw - [D]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testNextPlayerO() {
        String string = "XOX=OX=O=";
        Assertions.assertEquals("Winner found at Column [1] - [O]", TicTacToe.identifyWinner(string));
    }

    @Test
    public void testNextPlayerX() {
        String string = "XOX=OX=O=";
        Assertions.assertEquals("Winner found at Column [1] - [O]", TicTacToe.identifyWinner(string));
    }

}
