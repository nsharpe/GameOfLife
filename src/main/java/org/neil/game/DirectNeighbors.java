package org.neil.game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class DirectNeighbors {

  public Stream<Position> of(Position position) {
    return getNeighbors( new ArrayList<>(), position)
            .filter(x -> !x.equals(position));
  }

  private Stream<Position> getNeighbors(List<Integer> current, Position original) {
    if (current.size() == original.numOfAxis()) {
      return Stream.of(Position.of(current));
    }

    return getRange(original, current.size())
              .flatMap(x -> getNeighbors(addAxis(current, x), original));
  }


  private Stream<Integer> getRange(Position p, Integer i) {
    Integer value = p.position.get(i);
    return Arrays.asList(value - 1, value, value + 1).stream();
  }

  List<Integer> addAxis(List<Integer> l, Integer toAdd) {
    List<Integer> toReturn = new ArrayList<>(l);
    toReturn.add(toAdd);
    return toReturn;
  }
}
