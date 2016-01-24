package com.anodev.cdots;

import com.badlogic.gdx.Game;


public class DotsGame extends Game {
	
	@Override
	public void create () {
		showDifficultyScreen();
	}

	public void showDifficultyScreen() {
		setScreen(new DifficultyScreen(this));
	}

	public void showGameScreen(Constants.Difficulty difficulty) {
		setScreen(new GameScreen(this, difficulty));
	}
}
