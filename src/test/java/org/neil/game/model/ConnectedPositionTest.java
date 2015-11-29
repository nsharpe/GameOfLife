package org.neil.game.model;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class ConnectedPositionTest {

  @Test
  public void testConnected() {
    Set<ConnectedPosition> connectedPositions = ConnectedPosition.of(
            Stream.of(
                    Position.of(0, 0),
                    Position.of(1, 1),
                    Position.of(2, 2)),
            new DirectNeighbors());
    assertEquals(1,connectedPositions.size());

    ConnectedPosition connectedPosition = connectedPositions.stream()
            .findAny().get();

    assertEquals(3,connectedPosition.getPositions().size());

    assertTrue(connectedPosition.contains(Position.of(0,0)));
    assertTrue(connectedPosition.contains(Position.of(1,1)));
    assertTrue(connectedPosition.contains(Position.of(2,2)));
  }

  @Test
  public void testDisConnected() {
    Set<ConnectedPosition> connectedPositions = ConnectedPosition.of(
            Stream.of(
                    Position.of(0, 0),
                    Position.of(2, 2)),
            new DirectNeighbors());
    assertEquals(2,connectedPositions.size());
    assertTrue(connectedPositions
            .stream()
            .filter(x->x.contains(Position.of(0,0)))
            .findFirst()
            .isPresent());
    assertTrue(connectedPositions
            .stream()
            .filter(x->x.contains(Position.of(2,2)))
            .findFirst()
            .isPresent());
  }

  @Test
  public void testCentralize(){
    Set<ConnectedPosition> connectedPositions = ConnectedPosition.of(
            Stream.of(
                    Position.of(1, 1),
                    Position.of(2, 2)),
            new DirectNeighbors());
    assertEquals(1,connectedPositions.size());

    ConnectedPosition centralized = connectedPositions.stream()
            .findAny().get().centralized();

    assertTrue("Contains the following: "+centralized.getPositions(),centralized.contains(Position.of(0,0)));
    assertTrue(centralized.contains(Position.of(1,1)));
  }
}
