package game.strategy;

import game.FishSelectionStrategy;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class FirstFishSelectionStrategy implements FishSelectionStrategy {

  @Override
  public Integer selectFish(Map<Integer, Integer> positions, Set<Integer> finishedFish, Set<Integer> killedFish, double draw) {
    final Integer minPos = Collections.max(positions.values());
    return FishSelectionStrategy.getKeyByValue(positions, minPos);
  }
}
