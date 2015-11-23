package org.neil.game;

import java.util.Collection;

/**
 * Provides an engine for a Cellular Automata.
 *
 * Every time a frame of the Cellular Automata is processed the next frame
 * is sent to all instances of {@link Processable} registered to this engine.
 *
 * Created by neilsharpe on 11/21/15.
 */
public interface GameEngine {
  void addListener(Processable toProcess);

  void start();
  void stop();

  interface Processable{
    void process(Collection<Position> toProcess);
  }
}
