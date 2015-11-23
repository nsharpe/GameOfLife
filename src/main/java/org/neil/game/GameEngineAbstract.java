package org.neil.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by neilsharpe on 11/22/15.
 */
public abstract class GameEngineAbstract implements GameEngine {
  private final List<Processable> toProcess =
          Collections.synchronizedList(new ArrayList<>());
  private final GameRule gameRule;

  public GameEngineAbstract(GameRule gameRule) {
    if(gameRule == null){
      throw new NullPointerException("GameRule");
    }
    this.gameRule = gameRule;
  }

  public void process(Collection<Position> positions){
    toProcess.stream().forEach(x->x.process(positions));
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
