package org.neil.game;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by neilsharpe on 11/9/15.
 */
public class RandomGridSetupTest {

  @Test
  public void testChanceToLiveToZero() {
    Set<Position> result = RandomGridSetup.makeMap(Double.valueOf("0"), 3, 3)
            .collect(Collectors.toSet());
    assertTrue(result.isEmpty());
  }

  @Test
  public void testGridSetup() {
    Set<Position> result = RandomGridSetup.makeMap(Double.valueOf("1"), 3, 3)
            .collect(Collectors.toSet());
    assertEquals(9, result.size());
    assertTrue(result.contains(Position.of(0, 0)));
    assertTrue(result.contains(Position.of(0, 1)));
    assertTrue(result.contains(Position.of(0, 2)));

    assertTrue(result.contains(Position.of(1, 0)));
    assertTrue(result.contains(Position.of(1, 1)));
    assertTrue(result.contains(Position.of(1, 2)));

    assertTrue(result.contains(Position.of(2, 0)));
    assertTrue(result.contains(Position.of(2, 1)));
    assertTrue(result.contains(Position.of(2, 2)));
  }
}
