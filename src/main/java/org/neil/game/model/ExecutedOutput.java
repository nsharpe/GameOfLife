package org.neil.game.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class ExecutedOutput {
  private List<Step> steps;

  public ExecutedOutput(List<Step> steps) {
    if(steps == null){
      throw new NullPointerException();
    }
    this.steps = steps;
  }

  public void addStep(Step s){
    steps.add(s);
  }

  public List<Step> getSteps() {
    return Collections.unmodifiableList(steps);
  }
}
