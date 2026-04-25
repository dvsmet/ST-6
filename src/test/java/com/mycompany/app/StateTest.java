package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateTest {

  @Test
  void allStatesExist() {
    for (State s : State.values()) {
      assertNotNull(s);
      assertTrue(s.name().length() > 0);
    }
  }
}
