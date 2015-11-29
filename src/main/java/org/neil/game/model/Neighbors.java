package org.neil.game.model;

import org.neil.game.model.Position;

import java.util.stream.Stream;

/**
 * Provides a stream that contains a single instance of each neighbor of a position
 * <p>
 * Created by neilsharpe on 11/3/15.
 */
public interface Neighbors {

  Stream<Position> of(Position position);
}
