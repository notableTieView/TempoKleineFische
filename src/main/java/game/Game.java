package game;

import java.util.*;

public class Game {

  private static final int START_POS_FISH = 7;
  private static final int START_POS_BOAT = 0;
  private static final int FINAL_POS = 12;

  private static final int NUM_FISH = 5;
  private static final int NUM_FISHERS = 3;
  private static final double DRAW = 2.5;
  private static final int CUBE_SIDES = NUM_FISH +NUM_FISHERS;


  private final Map<Integer, Integer> positions;
  private int currentBoatPos;
  private final Set<Integer> finishedFish;
  private final Set<Integer> killedFish;
  private final FishSelectionStrategy strategy;


  public Game(FishSelectionStrategy strategy) {
    this.strategy = strategy;
    this.positions = new HashMap<>();
    for (int fish = 0; fish < NUM_FISH; fish++) {
      this.positions.put(fish, START_POS_FISH);
    }
    this.currentBoatPos = START_POS_BOAT;

    this.finishedFish = new HashSet<>();
    this.killedFish = new HashSet<>();
  }


  public Status move() {
    int entity = new Random().nextInt(CUBE_SIDES);
    if (entity < NUM_FISH && !killedFish.contains(entity)) {
      // case fish move
      return moveFish(entity);
    } else {
      return moveBoat();
    }
  }


  private Status moveBoat() {
    currentBoatPos++;
    if (currentBoatPos == FINAL_POS) {
      // boat reaches ocean, count killed fish
      if (killedFish.size() > DRAW) {
        return Status.BOAT_WIN;
      } else if (killedFish.size() == DRAW) {
        return Status.DRAW;
      } else {
        return Status.FISH_WIN;
      }
    }
    for (Map.Entry<Integer, Integer> fishWithPos : positions.entrySet()) {
      if (fishWithPos.getValue().equals(currentBoatPos)) {
        killedFish.add(fishWithPos.getKey());
      }
    }
    if (killedFish.size() > DRAW) {
      return Status.BOAT_WIN;
    }
    positions.keySet().removeAll(killedFish);
    if (positions.isEmpty()) {
      if (killedFish.size()== DRAW) {
        return Status.DRAW;
      }
      throw new IllegalStateException("The boat catching a fish can never be a fish win.");

    }
    return Status.GAME;
  }


  private Status moveFish(int fish) {
    Integer pos;
    if (finishedFish.contains(fish)) {
      fish = strategy.selectFish(positions, finishedFish, killedFish, DRAW);
    }
    pos = positions.get(fish);
    pos++;
    if (pos == FINAL_POS) {
      positions.remove(fish);
      finishedFish.add(fish);
      if (finishedFish.size() > DRAW) {
        return Status.FISH_WIN;
      }
      if (positions.isEmpty()) {
        if (finishedFish.size()==DRAW) {
          return Status.DRAW;
        }
        throw new IllegalStateException("A fish reaching finish can never be a boat win.");
      }
    } else {
      positions.put(fish, pos);
    }
    return Status.GAME;
  }


}