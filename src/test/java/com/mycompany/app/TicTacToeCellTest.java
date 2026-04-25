package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.awt.GridLayout;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeCellTest {

  @Test
  void constructorAndAccessors() {
    TicTacToeCell c = new TicTacToeCell(4, 1, 2);
    assertEquals(4, c.getNum());
    assertEquals(1, c.getCol());
    assertEquals(2, c.getRow());
    assertEquals(' ', c.getMarker());
  }

  @Test
  void setMarker() {
    TicTacToeCell c = new TicTacToeCell(0, 0, 0);
    c.setMarker("X");
    assertEquals('X', c.getMarker());
    assertFalse(c.isEnabled());
  }

  @Test
  void cellLivesInGridLayout() {
    new TicTacToeCell(0, 0, 0);
    new GridLayout(1, 1);
  }
}
