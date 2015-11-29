package org.neil.game;

import org.neil.game.model.Position;

import java.util.Collection;
import java.util.Set;

/**
 * Provides an engine for a given Cellular Automata.
 *
 * Every time a frame of significance of a given Cellular Automata is processed the next frame
 * is sent to all instances of {@link Processable} registered to this engine.
 *
 * Created by neilsharpe on 11/21/15.
 */
public interface GameEngine {
  /**
   * Request to
   *
   * @param toProcess
   */
  void addListener(Processable toProcess);

  void setPositions(Set<Position> positions);

  Set<Position> getPositions();

  void start();
  void stop();

  interface Processable{
    void process(Collection<Position> toProcess);
  }
}
