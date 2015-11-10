package org.neil.game;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by neilsharpe on 11/4/15.
 */
public class ConwayGameTest {
  @Test
  public void testBigGame(){
    Set<Position> toTest = IntStream.range(0,1_000_000)
            .filter( x -> x %4 != 0)
            .mapToObj(x -> Position.of(0,x))
            .collect(Collectors.toSet());
    System.out.println("Starting test of position size = "+toTest.size());
    DateTime start = DateTime.now();

    Stream<Position> result = StepRule.CONWAYS_GAME_OF_LIFE.next(toTest);

    DateTime end = DateTime.now();
    Interval totalTime = new Interval(start,end);
    assertEquals(toTest.size(),result.count());
    System.out.println("Test end total time  = " + totalTime.toDurationMillis() / 1000 + " seconds");
  }
}
