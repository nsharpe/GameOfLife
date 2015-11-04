package org.neil.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class DirectNeighbors implements Neighbors {

  @Override
  public Set<Position> of(Position position) {
    return getNeighbors(new HashSet<>(),getStartingPosition(position),0,position).stream().filter(x->!x.equals(position)).collect(Collectors.toSet());
  }

  Set<Position> getNeighbors(Set<Position> neighbors,List<Integer> current,Integer axis,Position original){
    if(axis.equals(original.position.size())){
      neighbors.add(Position.of(current));
    }else{
      getRange(original,axis).stream().forEach(x->{
        current.set(axis,x);
        getNeighbors(neighbors,current,axis + 1,original);
      });
    }
    return neighbors;
  }


  List<Integer> getRange(Position p, Integer i){
    Integer value = p.position.get(i);
    return Arrays.asList(value - 1, value, value + 1 );
  }

  List<Integer> getStartingPosition(Position p){
    return p.position.stream().map( x -> x-1).collect(Collectors.toList());
  }
}
