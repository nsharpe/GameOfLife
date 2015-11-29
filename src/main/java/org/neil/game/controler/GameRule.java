package org.neil.game.controler;

import org.neil.game.model.Position;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/21/15.
 */
public interface GameRule {
  Stream<Position> nextStep(Collection<Position> positionStream);
}
