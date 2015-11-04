package org.neil.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toSet;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class StepRule {

  private static final Long[] CONWAY_LIVE = {2l};
  private static final Long[] CONWAY_BORN = {3l};
  public static final StepRule CONWAYS_GAME_OF_LIFE = new StepRule(CONWAY_LIVE,CONWAY_BORN);

  public final Set<Long> stayAliveCount;
  public final Set<Long> birthCount;
  DirectNeighbors neighbors = new DirectNeighbors();

  public StepRule(Long[] stayAliveCount, Long[] birthCount) {
    this(set(stayAliveCount),set(birthCount));
  }

  private static Set<Long> set(Long[] l){
    return new HashSet<>(Arrays.asList(l));
  }

  public StepRule(Set<Long> stayAliveCount, Set<Long> birthCount) {
    if(stayAliveCount==null || birthCount == null){
      throw new NullPointerException();
    }
    this.stayAliveCount = stayAliveCount;
    this.birthCount = birthCount;
  }

  public Set<Position> next(Set<Position> positions){
    Map<Position,Long> neighborCount = neighborCount(positions);
    Set<Position> toReturn = stayAlive(positions,neighborCount);
    toReturn.addAll(bornCells(neighborCount));
    return toReturn;
  }

  public Map<Position,Long> neighborCount(Set<Position> positions){
    return positions.stream()
            .flatMap(x->neighbors.of(x))
            .collect(Collectors.groupingBy(identity(),counting()));
  }

  public Set<Position> stayAlive(Set<Position> positions,Map<Position,Long> neighbors){
    return positions.stream().filter(x->stayAliveCount.contains( neighbors.get(x))).collect(toSet());
  }

  public Set<Position> bornCells(Map<Position,Long> neighbors){
    return neighbors.entrySet().stream().filter(x->birthCount.contains(x.getValue())).map(x->x.getKey()).collect(toSet());
  }
}
