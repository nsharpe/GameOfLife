package org.neil.game.controler;

import org.neil.game.model.DirectNeighbors;
import org.neil.game.model.Neighbors;
import org.neil.game.model.Position;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class StepRule implements GameRule {

  private static final Long[] CONWAY_LIVE = {2l};
  private static final Long[] CONWAY_BORN = {3l};
  public static final StepRule CONWAYS_GAME_OF_LIFE = new StepRule(CONWAY_LIVE,CONWAY_BORN);

  public final Set<Long> stayAliveCount;
  public final Set<Long> birthCount;
  public Neighbors neighbors = new DirectNeighbors();

  public StepRule(Long[] stayAliveCount, Long[] birthCount) {
    this(toSet(stayAliveCount), toSet(birthCount));
  }

  private static Set<Long> toSet(Long[] l){
    return new HashSet<>(Arrays.asList(l));
  }

  public StepRule(Set<Long> stayAliveCount, Set<Long> birthCount) {
    if(stayAliveCount==null || birthCount == null){
      throw new NullPointerException();
    }
    this.stayAliveCount = stayAliveCount;
    this.birthCount = birthCount;
    stayAliveCount.addAll(birthCount);
  }

  public StepRule(Collection<Integer> stayAliveCount, Collection<Integer> birthCount) {
    if(stayAliveCount==null || birthCount == null){
      throw new NullPointerException();
    }
    this.stayAliveCount = stayAliveCount.stream().map(x->x.longValue()).collect(Collectors.toSet());
    this.birthCount = birthCount.stream().map(x->x.longValue()).collect(Collectors.toSet());;
    stayAliveCount.addAll(birthCount);
  }

  public Stream<Position> nextStep(Collection<Position> cellPositions){
    Map<Position,Long> neighborCount = neighborCount(cellPositions);

    return neighborCount.entrySet().stream()
            .filter( x -> isAlive(x.getValue(),cellPositions.contains(x.getKey())))
            .map( x -> x.getKey());
  }

  public Map<Position,Long> neighborCount(Collection<Position> positions){
    return positions.stream()
            .flatMap(x->neighbors.of(x))
            .collect(Collectors.groupingBy(identity(),counting()));
  }

  public boolean isAlive(Long neighborCount, Boolean wasAlive){
    return  birthCount.contains(neighborCount) ||
            (wasAlive && stayAliveCount.contains(neighborCount));
  }
}
