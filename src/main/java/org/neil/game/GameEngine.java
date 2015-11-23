package org.neil.game;

import java.util.Collection;

/**
 * Created by neilsharpe on 11/21/15.
 */
public interface GameEngine {
  void addListener(Processable toProcess);

  interface Processable{
    void process(Collection<Position> toProcess);
  }
}
