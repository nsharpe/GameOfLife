package org.neil.org.neil.game.ui;

import org.neil.game.model.Position;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Created by neilsharpe on 11/3/15.
 */
public class Cell {
  Integer x;
  Integer y;
  Boolean isAlive;

  public Cell(Integer x, Integer y, Boolean isAlive) {
    this.x = x;
    this.y = y;
    this.isAlive = isAlive;
  }

  public Position toPosition() {
    return Position.of(x, y);
  }

  public Boolean isAlive() {
    return isAlive;
  }

  public static Stream<Cell> createGrid(Integer size) {
    return IntStream.range(0, size)
            .mapToObj(x -> createRow(size, x)).flatMap(Function.identity());
  }

  public static Stream<Cell> createRow(Integer size, Integer row) {
    return IntStream.range(0, size).mapToObj(x -> Cell.of(row, x));
  }

  public static Cell of(Integer x, Integer y) {
    return new Cell(x, y, false);
  }
}
