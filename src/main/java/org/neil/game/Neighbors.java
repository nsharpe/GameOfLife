package org.neil.game;

import java.util.Set;

/**
 * Created by neilsharpe on 11/3/15.
 */
public interface Neighbors {
  Set<Position> of(Position position);
}
