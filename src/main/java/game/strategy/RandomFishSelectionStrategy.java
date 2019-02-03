package game.strategy;

import game.FishSelectionStrategy;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomFishSelectionStrategy implements FishSelectionStrategy {

  @Override
  public Integer selectFish(Map<Integer, Integer> positions, Set<Integer> finishedFish, Set<Integer> killedFish, double draw) {
    Random random = new Random();
    final int randomPos = random.nextInt(positions.size());
    return new LinkedList<>(positions.keySet()).get(randomPos);
  }
}
