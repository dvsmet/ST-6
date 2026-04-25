package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ProgramTest {

  @Test
  void staticWritersStartNull() {
    assertNull(Program.fileWriter);
    assertNull(Program.printWriter);
  }
}
