package org.neil.game;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/9/15.
 */
public class RandomGridSetup {
  private RandomGridSetup(){}



  public static Stream<Position> makeMap(Double oddsOfExisting, Integer... axises){
    Stream.of(axises)
  }

  private static Boolean isAlive(Double ratio){
    return Math.random() <= ratio ? Boolean.TRUE : Boolean.FALSE;
  }
}
