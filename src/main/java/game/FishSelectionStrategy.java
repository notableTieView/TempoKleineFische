package game;

import java.util.Map;
import java.util.Set;

public interface FishSelectionStrategy {

  Integer selectFish(
      Map<Integer, Integer> positions,
      Set<Integer> finishedFish,
      Set<Integer> killedFish,
      double draw
  );


  static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Map.Entry<T, E> entry : map.entrySet()) {
      if (value.equals(entry.getValue())) {
        return entry.getKey();
      }
    }
    throw new IllegalStateException();
  }

}
