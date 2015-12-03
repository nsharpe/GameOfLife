package org.neil.game.controler;

import org.neil.game.model.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by neilsharpe on 11/22/15.
 */
public abstract class GameEngineAbstract implements GameEngine {
  private final List<Processable> toProcess =
          Collections.synchronizedList(new ArrayList<>());
  private final GameRule gameRule;
  private volatile Set<Position> positions;

  public GameEngineAbstract(GameRule gameRule) {
    if(gameRule == null){
      throw new NullPointerException("GameRule");
    }
    this.gameRule = gameRule;
  }

  @Override
  public Set<Position> getPositions() {
    return positions;
  }

  @Override
  public void setPositions(Set<Position> positions) {
    this.positions = positions;
    process(positions);
  }

  public void process(Collection<Position> positions){
    toProcess.forEach(x->x.process(positions));
  }

  public GameRule getGameRule() {
    return gameRule;
  }

  @Override
  public void addListener(Processable toProcess) {
    this.toProcess.add(toProcess);
  }

  public List<Processable> getProcesses(){
    return Collections.unmodifiableList(toProcess);
  }
}
