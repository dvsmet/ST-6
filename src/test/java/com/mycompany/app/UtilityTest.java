package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

  @Test
  void printCharArray() {
    char[] b = new char[9];
    b[0] = 'X';
    b[1] = 'O';
    b[2] = ' ';
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream old = System.out;
    System.setOut(new PrintStream(buf));
    try {
      Utility.print(b);
    } finally {
      System.setOut(old);
    }
    String s = new String(buf.toByteArray());
    assertTrue(s.contains("X-"));
  }

  @Test
  void printIntArray() {
    int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream old = System.out;
    System.setOut(new PrintStream(buf));
    try {
      Utility.print(a);
    } finally {
      System.setOut(old);
    }
    String s = new String(buf.toByteArray());
    assertTrue(s.length() > 0);
  }

  @Test
  void printEmptyMoveList() {
    ArrayList<Integer> moves = new ArrayList<Integer>();
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream old = System.out;
    System.setOut(new PrintStream(buf));
    try {
      Utility.print(moves);
    } finally {
      System.setOut(old);
    }
    assertTrue(new String(buf.toByteArray()).length() > 0);
  }

  @Test
  void printMoveList() {
    ArrayList<Integer> moves = new ArrayList<Integer>();
    moves.add(0);
    moves.add(1);
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream old = System.out;
    System.setOut(new PrintStream(buf));
    try {
      Utility.print(moves);
    } finally {
      System.setOut(old);
    }
    String s = new String(buf.toByteArray());
    assertTrue(s.contains("0-"));
  }
}
