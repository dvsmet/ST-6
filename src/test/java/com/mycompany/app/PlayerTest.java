package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @Test
  void setFields() {
    Player p = new Player();
    p.symbol = 'O';
    p.move = 5;
    p.selected = true;
    p.win = false;
    assertEquals('O', p.symbol);
    assertEquals(5, p.move);
    assertTrue(p.selected);
    assertFalse(p.win);
  }
}
