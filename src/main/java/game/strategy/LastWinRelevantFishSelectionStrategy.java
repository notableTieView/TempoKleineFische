package game.strategy;

import game.FishSelectionStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class LastWinRelevantFishSelectionStrategy implements FishSelectionStrategy {

  @Override
  public Integer selectFish(Map<Integer, Integer> positions, Set<Integer> finishedFish, Set<Integer> killedFish, double draw) {
    final ArrayList<Integer> positionList = new ArrayList<>(positions.values());
    positionList.sort(Comparator.reverseOrder()); // descending positions
    final int lastRelevantRank = Math.min((int) Math.ceil(draw) - finishedFish.size(), positionList.size() - 1);
    final Integer lastRelevantPos = positionList.get(lastRelevantRank);
    return FishSelectionStrategy.getKeyByValue(positions, lastRelevantPos);
  }
}
