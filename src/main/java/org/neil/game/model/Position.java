package org.neil.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A discrete point of reference of a map of any number of dimensions.
 * <p>
 *   It is intended to be the location of a currently living cell.
 * </p>
 *
 * Created by neilsharpe on 11/3/15.
 */
public class Position {
  public final List<Integer> position;

  private Position(List<Integer> position) {
    this.position = Collections.unmodifiableList(position);
  }

  public  Integer axis(Long l){
    return axis(l.intValue());
  }

  public Integer axis(Integer i){
    return position.get(i);
  }

  public Integer numOfAxis(){
    return position.size();
  }

  public static Position of(Stream<Integer> pos){
    return of(pos.collect(Collectors.toList()));
  }
  public static Position of(List<Integer> pos){
    return new Position(new ArrayList<>(pos));
  }
  public static Position of(Integer ... pos){
    return of(Arrays.asList(pos));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position position1 = (Position) o;
    return Objects.equals(position, position1.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position);
  }

  @Override
  public String toString(){
    return position.toString();
  }
}
