package org.neil.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class DirectNeighbors {

  public Stream<Position> of(Position position) {
    return getNeighbors(new HashSet<>(),new ArrayList<>(),position).stream().filter(x->!x.equals(position));
  }

  private Set<Position> getNeighbors(Set<Position> neighbors,List<Integer> current,Position original){
    if(current.size()==original.numOfAxis()){
      neighbors.add(Position.of(current));
    }else{
      getRange(original,current.size()).stream().forEach(x-> getNeighbors(neighbors,addAxis(current,x),original));
    }
    return neighbors;
  }


  private List<Integer> getRange(Position p, Integer i){
    Integer value = p.position.get(i);
    return Arrays.asList(value - 1, value, value + 1 );
  }

  List<Integer> addAxis(List<Integer> l, Integer toAdd){
    List<Integer> toReturn = new ArrayList<>(l);
    toReturn.add(toAdd);
    return toReturn;
  }

  List<Integer> getStartingPosition(Position p){
    return p.position.stream().map( x -> x-1).collect(Collectors.toList());
  }
}
