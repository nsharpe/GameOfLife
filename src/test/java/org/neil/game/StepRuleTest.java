package org.neil.game;

import org.junit.Test;
import org.neil.game.model.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class StepRuleTest {
  public final StepRule conway = StepRule.CONWAYS_GAME_OF_LIFE;

  @Test
  public void testNoNeighbor(){
    Set<Position> positions = init(Position.of(0,0));
    assertEquals(0,conway.nextStep(positions).count());
  }

  @Test
  public void testTwoNeighborStayAlive(){
    Set<Position> positions = init(Position.of(0,0));
    positions.add(Position.of(1,1));
    positions.add(Position.of(-1,-1));
    positions = conway.nextStep(positions).collect(Collectors.toSet());
    assertEquals(1,positions.size());
    assertTrue(positions.contains(Position.of(0,0)));
  }

  @Test
  public void testBornThreeNeighbor(){
    Set<Position> positions = init(Position.of(1,0));
    positions.add(Position.of(0,1));
    positions.add(Position.of(-1,-1));

    positions = conway.nextStep(positions).collect(Collectors.toSet());
    assertEquals(1,positions.size());
    assertTrue(positions.contains(Position.of(0,0)));
  }

  @Test
  public void testStayAliveThreeNeighbor(){
    Set<Position> positions = init(Position.of(1,0));
    positions.add(Position.of(0,1));
    positions.add(Position.of(-1,-1));
    positions.add(Position.of(1,0));

    positions = conway.nextStep(positions).collect(Collectors.toSet());
    assertEquals(1,positions.size());
    assertTrue(positions.contains(Position.of(0,0)));
  }

  private static Set<Position> init(Position position){
    Set<Position> toReturn = new HashSet<>();
    toReturn.add(position);
    return toReturn;
  }
}
