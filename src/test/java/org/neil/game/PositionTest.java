package org.neil.game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class PositionTest {
  @Test
  public void testEquals(){
    assertTrue(Position.of(0,0).equals(Position.of(0,0)));
  }

  @Test
  public void testNotEquals(){
    assertFalse(Position.of(0,1).equals(Position.of(0,0)));
    assertFalse(Position.of(0,1).equals(Position.of(1,0)));
  }

}
