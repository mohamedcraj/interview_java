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
public class TestTicTacToe {

    @Test
    public void testisGameOver() {
        String string = "XXX=OX=O=";
        Assertions.assertTrue(TicTacToe.isGameOver(string.toCharArray()));
    }

    @Test
    public void testisGameNotOver() {
        String string = "XXX=OX=O";
        Assertions.assertFalse(TicTacToe.isGameOver(string.toCharArray()));
    }
}
