package org.neil.game.model;

import org.junit.Test;
import org.neil.game.model.Position;

import static org.junit.Assert.assertEquals;
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
  public void testMove(){
    assertEquals(Position.of(1,2),Position.of(1,1).move(Position.of(0,1)));
  }

  @Test
  public void testInvert(){
    assertEquals(Position.of(-1,1),Position.of(1,-1).invert());
  }

  @Test
  public void testNotEquals(){
    assertFalse(Position.of(0,1).equals(Position.of(0,0)));
    assertFalse(Position.of(0,1).equals(Position.of(1,0)));
  }

}
