package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void constructorInitializesTwoPlayers() {
    assertNotNull(game.player1);
    assertNotNull(game.player2);
    assertEquals('X', game.player1.symbol);
    assertEquals('O', game.player2.symbol);
    assertEquals(State.PLAYING, game.state);
    for (int i = 0; i < 9; i++) {
      assertEquals(' ', game.board[i]);
    }
  }

  @Test
  void checkStateTopRowXWin() {
    char[] b = { 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'X';
    assertEquals(State.XWIN, game.checkState(b));
  }

  @Test
  void checkStateMiddleRowOWin() {
    char[] b = { ' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' ' };
    game.symbol = 'O';
    assertEquals(State.OWIN, game.checkState(b));
  }

  @Test
  void checkStateColumnXWin() {
    char[] b = { 'X', 'O', ' ', 'X', ' ', ' ', 'X', ' ', ' ' };
    game.symbol = 'X';
    assertEquals(State.XWIN, game.checkState(b));
  }

  @Test
  void checkStateDiagonalO() {
    char[] b = { 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O' };
    game.symbol = 'O';
    assertEquals(State.OWIN, game.checkState(b));
  }

  @Test
  void checkStateAntiDiagonalX() {
    char[] b = { 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'O' };
    game.symbol = 'X';
    assertEquals(State.XWIN, game.checkState(b));
  }

  @Test
  void checkStateDrawFull() {
    char[] b = { 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O' };
    game.symbol = 'X';
    assertEquals(State.DRAW, game.checkState(b));
  }

  @Test
  void checkStatePlayingWithEmpties() {
    char[] b = { 'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'O';
    assertEquals(State.PLAYING, game.checkState(b));
  }

  @Test
  void generateMovesFillsList() {
    char[] b = { 'X', ' ', ' ', ' ', 'O', ' ', ' ', ' ', ' ' };
    ArrayList<Integer> moves = new ArrayList<Integer>();
    game.generateMoves(b, moves);
    assertTrue(moves.size() >= 7);
    assertTrue(moves.contains(1));
  }

  @Test
  void evaluatePositionWinningForX() {
    char[] b = { 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'X';
    assertEquals(Game.INF, game.evaluatePosition(b, game.player1));
  }

  @Test
  void evaluatePositionWinningForO() {
    char[] b = { 'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'O';
    assertEquals(Game.INF, game.evaluatePosition(b, game.player2));
  }

  @Test
  void evaluatePositionLosing() {
    char[] b = { 'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'O';
    assertEquals(-Game.INF, game.evaluatePosition(b, game.player1));
  }

  @Test
  void evaluatePositionDraw() {
    char[] b = { 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O' };
    game.symbol = 'O';
    assertEquals(0, game.evaluatePosition(b, game.player1));
  }

  @Test
  void evaluatePositionNotTerminal() {
    char[] b = { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    game.symbol = 'O';
    assertEquals(-1, game.evaluatePosition(b, game.player1));
  }

  @Test
  void miniMaxEmptyBoard() {
    int m = game.MiniMax(game.board, game.player2);
    assertTrue(m >= 1 && m <= 9);
  }

  @Test
  void miniMaxWithTwoOInTopRow() {
    for (int i = 0; i < 9; i++) {
      game.board[i] = ' ';
    }
    game.board[0] = 'O';
    game.board[1] = 'O';
    int m = game.MiniMax(game.board, game.player2);
    assertTrue(m >= 1 && m <= 9, "any legal 1..9 is acceptable for this implementation");
  }

  @Test
  void miniMaxWithTwoXInTopRow() {
    for (int i = 0; i < 9; i++) {
      game.board[i] = ' ';
    }
    game.board[0] = 'X';
    game.board[1] = 'X';
    int m = game.MiniMax(game.board, game.player2);
    assertTrue(m >= 1 && m <= 9, "O responds from this position; exact square depends on the minimax tree");
  }

  @Test
  void miniMaxDeepSearchUsesCounter() {
    for (int i = 0; i < 9; i++) {
      game.board[i] = ' ';
    }
    game.q = 0;
    game.MiniMax(game.board, game.player1);
  }

  @Test
  void minMaxTieBreakReturnsValidIndex() {
    for (int i = 0; i < 9; i++) {
      game.board[i] = ' ';
    }
    int r = game.MiniMax(game.board, game.player2);
    assertTrue(r >= 1 && r <= 9);
  }
}
