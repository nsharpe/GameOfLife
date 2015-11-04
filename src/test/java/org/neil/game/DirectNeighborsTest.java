package org.neil.game;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class DirectNeighborsTest {
  @Test
  public void testHasAllNeighbors(){
    Position start = Position.of(0,0);
    Set<Position> neighbors = new DirectNeighbors().of(start).collect(Collectors.toSet());

    assertEquals(neighbors.size(),8);

    assertFalse(neighbors.contains(Position.of(0,0)));

    assertTrue(neighbors.contains(Position.of(-1,-1)));
    assertTrue(neighbors.contains(Position.of(-1,0)));
    assertTrue(neighbors.contains(Position.of(-1,1)));

    assertTrue(neighbors.contains(Position.of(0,-1)));
    assertTrue(neighbors.contains(Position.of(0,1)));

    assertTrue(neighbors.contains(Position.of(1,-1)));
    assertTrue(neighbors.contains(Position.of(1,0)));
    assertTrue(neighbors.contains(Position.of(1,1)));
  }
}
