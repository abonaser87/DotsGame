package com.anodev.cdots;

/**
 * Created by abdullah on 3/4/17.
 */
public interface PlayServices {
  public void signIn();

  public void signOut();

  public void rateGame();

  public void unlockAchievement(String achievementId);

  public void submitScore(int highScore, String leaderboardId);

  public void showAchievement();

  public void showScore(String leaderboardId);

  public boolean isSignedIn();
}

