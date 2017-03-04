package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by abdullah on 1/27/17.
 */
public class AssetLoader {
  public static Sound wrong, correct, menu;

  public static void load() {
    wrong = Gdx.audio.newSound(Gdx.files.internal("data/wrong.wav"));
    correct = Gdx.audio.newSound(Gdx.files.internal("data/correct.wav"));
    menu = Gdx.audio.newSound(Gdx.files.internal("data/menu.wav"));
  }

  public static void dispose() {
    // Dispose sounds
    wrong.dispose();
    correct.dispose();
    menu.dispose();
  }
}
