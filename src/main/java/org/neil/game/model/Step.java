package org.neil.game.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class Step {

  private Set<Position> positions;
  private Neighbors neighbors;

  public Step(Set<Position> positions,Neighbors neighbors){
    if(positions == null || neighbors == null){
      throw new NullPointerException();
    }
    this.positions = Collections.unmodifiableSet(new HashSet<>(positions));
    this.neighbors = neighbors;
  }

  public Set<Position> getPositions() {
    return positions;
  }

  public Set<ConnectedPosition> connectedPositions(){
    return ConnectedPosition.of(positions,neighbors);
  }
}
