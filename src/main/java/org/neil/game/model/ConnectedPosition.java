package org.neil.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class ConnectedPosition {
  private final Set<Position> positions;
  private final Neighbors neighbors;

  private ConnectedPosition(Stream<Position> positions, Neighbors neighbors) {
    this.positions = Collections.unmodifiableSet(positions.collect(Collectors.toSet()));
    this.neighbors = neighbors;
  }

  private ConnectedPosition(Set<Position> positions, Neighbors neighbors) {
    this.positions = Collections.unmodifiableSet(positions);
    this.neighbors = neighbors;
  }

  public Set<Position> getPositions() {
    return positions;
  }

  public boolean contains(Position position) {
    return positions.contains(position);
  }

  /**
   * Returns true if there is at least one Position that is contained to this
   * ConnectedPositions
   *
   * @param connectedPosition
   * @return
   */
  public boolean containsSome(ConnectedPosition connectedPosition) {
    return positions.stream()
            .filter(x -> connectedPosition.contains(x))
            .findFirst().isPresent();
  }

  public ConnectedPosition centralized() {
    Position offset = offset();
    return new ConnectedPosition(
            positions.stream()
                    .map(x -> x.move(offset)),
            neighbors);
  }

  private Position offset() {
    if (positions.isEmpty()) {
      return null;
    }
    Position toReturn = positions.stream().findAny().get();
    for (Position p : positions) {
      Position toCheck = toReturn;
      toReturn = Position.of( IntStream.range(0,p.numOfAxis())
              .mapToObj(x->p.axis(x)<toCheck.axis(x)?p.axis(x):toCheck.axis(x)));
    }
    return toReturn.invert();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConnectedPosition that = (ConnectedPosition) o;
    return Objects.equals(positions, that.positions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(positions);
  }

  public static Set<ConnectedPosition> of(Stream<Position> positions, Neighbors neighbors) {
    return of(positions.collect(Collectors.toSet()), neighbors);
  }

  public static Set<ConnectedPosition> of(Set<Position> positions, Neighbors neighbors) {
    Set<ConnectedPosition> connectedPosition = new HashSet<>();
    positions = new HashSet<>(positions);
    while (!positions.isEmpty()) {
      Set<Position> positions1 =
              connectedPositions(positions.stream()
                              .findFirst().get()
                      , positions
                      , neighbors);
      connectedPosition.add(new ConnectedPosition(positions1, neighbors));
    }
    return connectedPosition;
  }

  private static Set<Position> connectedPositions(Position p,
                                                  Set<Position> allCells,
                                                  Neighbors neighbors) {
    Set<Position> connected = neighbors.of(p)
            .filter(x -> allCells.contains(x))
            .collect(Collectors.toSet());

    allCells.removeAll(connected);

    connected.addAll(connected.stream()
            .flatMap(x -> connectedPositions(x, allCells, neighbors).stream())
            .collect(Collectors.toSet()));

    return connected;
  }
}
