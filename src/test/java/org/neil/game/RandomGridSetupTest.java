package org.neil.game;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

  @Test
  public void testGameSetup(){
    Set<Position> board = RandomGridSetup.makeMap(Double.valueOf("0.35"), 100, 100)
            .collect(Collectors.toSet());
    System.out.println("Number of positions " + board.size());
    System.out.println("Starting position " + board.toString());

    StepRule rule = StepRule.CONWAYS_GAME_OF_LIFE;

    for(int i = 0; i < 100 && !board.isEmpty(); i++){
      if(i%1000==0){
        System.out.println("Number of positions at iteration " + i + " =" + board.size());
        System.out.println(i+" position " + board.toString());
      }
      board = rule.nextStep(board).collect(Collectors.toSet());
    }
    System.out.println("Result size = " + board.size());
    System.out.println("Result positions = " + board);
  }
}
