package org.neil.game;

import org.neil.game.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/9/15.
 */
public class RandomGridSetup {
  private RandomGridSetup() {
  }

  public static Stream<Position> makeMap(Double oddsOfExisting, Integer... axises) {
    if (oddsOfExisting.compareTo(Double.valueOf("1")) > 0) {
      throw new IllegalStateException("Odds cannot exceed 1");
    }
    if (oddsOfExisting.compareTo(Double.valueOf("0")) < 0) {
      throw new IllegalStateException("Odds cannot be less then 0");
    }
    return mapPositions(new ArrayList<>(), axises).filter(x -> isAlive(oddsOfExisting));
  }

  public static Stream<Position> mapPositions(List<Integer> currentSetup, Integer... axises) {
    if (currentSetup.size() == axises.length) {
      return Stream.of(Position.of(currentSetup));
    }
    return IntStream.range(0, axises[currentSetup.size()])
            .mapToObj(x -> x)//This is necessary to make flatMap work.  review at a later date.
            .flatMap(x -> {
              List<Integer> copy = new ArrayList<>(currentSetup);
              copy.add(x);
              return mapPositions(copy, axises);
            });
  }

  private static Boolean isAlive(Double ratio) {
    return Math.random() <= ratio ? Boolean.TRUE : Boolean.FALSE;
  }
}
