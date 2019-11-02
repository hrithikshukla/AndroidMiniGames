package com.example.game.Save;

public class TapiocaStats extends Stats {

  // Unique stat of the Tapioca game. Change if desired.
  int totalBubblesPopped;

  /** Default constructor. Initialize as a blank slate. */
  public TapiocaStats() {
    this(0, 0, 0);
  }

  /**
   * Set the stats of the Tapioca game with the provided values.
   *
   * @param highScore - input high score
   * @param totalGames - input total games
   * @param totalBubblesPopped - input total bubbles popped
   */
  public TapiocaStats(int highScore, int totalGames, int totalBubblesPopped) {
    super(highScore, totalGames);
    this.totalBubblesPopped = totalBubblesPopped;
  }

  /** Getter for the total number of bubbles popped tapped. */
  @Override
  int getUniqueStat() {
    return totalBubblesPopped;
  }

  /**
   * Setter for the total number of bubbles popped.
   *
   * @param totalBubblesPopped - number of tiles that were newly popped.
   */
  @Override
  void setUniqueStat(int totalBubblesPopped) {
    this.totalBubblesPopped += totalBubblesPopped;
  }
}
