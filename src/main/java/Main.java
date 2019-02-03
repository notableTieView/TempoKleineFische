import game.FishSelectionStrategy;
import game.Game;
import game.Status;
import game.strategy.*;

public class Main {

  private static final int REPETITIONS = 1000000;

  private static FishSelectionStrategy[] strategies = {
      new LastDrawRelevantFishSelectionStrategy(),
      new LastWinRelevantFishSelectionStrategy(),
      new FirstFishSelectionStrategy(),
      new RandomFishSelectionStrategy(),
      new LastFishSelectionStrategy()
  };

  private static String stringRatio(int value) {
    return String.format("%.2f", 1.0 * value / Main.REPETITIONS);
  }


  public static void main(String[] args) {
    for (FishSelectionStrategy strategy : strategies) {
      int fishWins = 0;
      int boatWins = 0;
      int draws = 0;

      for (int i = 0; i < REPETITIONS; i++) {
        Game game = new Game(strategy);
        Status status = Status.GAME;
        while (Status.GAME == status) {
          status = game.move();
        }
        switch (status) {
          case FISH_WIN:
            fishWins++;
            break;
          case BOAT_WIN:
            boatWins++;
            break;
          case DRAW:
            draws++;
            break;
          default:
            throw new IllegalStateException();
        }
      }
      System.out.println(
          "Fish: " + stringRatio(fishWins) +
              " Boat: " + stringRatio(boatWins) +
              " Draw: " + stringRatio(draws) +
              "  -> " + strategy.getClass().getSimpleName()
      );
    }
  }
}
