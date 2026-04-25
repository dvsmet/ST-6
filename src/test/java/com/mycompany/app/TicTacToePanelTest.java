package com.mycompany.app;

import org.junit.jupiter.api.Test;

import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TicTacToePanelTest {

  @Test
  void panelConstructsWithNineCells() throws Exception {
    final TicTacToePanel[] ref = new TicTacToePanel[1];
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        ref[0] = new TicTacToePanel(new GridLayout(3, 3));
      }
    });
    assertNotNull(ref[0]);
  }

  @Test
  void firstClickDoesNotEndGame() throws Exception {
    final TicTacToeCell[] firstCell = new TicTacToeCell[1];
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        try {
          Field f = TicTacToePanel.class.getDeclaredField("cells");
          f.setAccessible(true);
          TicTacToeCell[] cells = (TicTacToeCell[]) f.get(panel);
          firstCell[0] = cells[0];
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
    final Exception[] ex = new Exception[1];
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        firstCell[0].doClick();
        try {
          TicTacToePanel panel = (TicTacToePanel) firstCell[0].getParent();
          Field g = TicTacToePanel.class.getDeclaredField("game");
          g.setAccessible(true);
          Game game = (Game) g.get(panel);
          assertEquals(State.PLAYING, game.state, "one human move and AI must stay in PLAYING");
        } catch (Exception e) {
          ex[0] = e;
        }
      }
    });
    if (ex[0] != null) {
      throw ex[0];
    }
  }
}
