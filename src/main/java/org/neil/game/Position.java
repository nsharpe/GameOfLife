package org.neil.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class Position {
  public final List<Integer> position;

  private Position(List<Integer> position) {
    this.position = Collections.unmodifiableList(position);
  }

  public Integer numOfAxis(){
    return position.size();
  }

  public static Position of(List<Integer> pos){
    return new Position(new ArrayList<>(pos));
  }
  public static Position of(Integer ... pos){
    return new Position(Arrays.asList(pos));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Position position1 = (Position) o;

    return position.equals(position1.position);

  }

  @Override
  public int hashCode() {
    return position.hashCode();
  }
}
