package com.anodev.cdots;

import com.badlogic.gdx.Game;


public class DotsGame extends Game {
	PlayServices playServices;

	public DotsGame(PlayServices playServices) {
		this.playServices = playServices;
	}
	@Override
	public void create () {
		AssetLoader.load();
		showMainMenu();
	}

	@Override public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

	public void showMainMenu() {
		setScreen(new MainMenu(this));
	}

	public void showDifficultyScreen() {
		setScreen(new DifficultyScreen(this));
	}

	public void showGameScreen(Constants.Difficulty difficulty) {
		setScreen(new GameScreen(this, difficulty));
	}

	public void showLDRBoardScreen() {
		setScreen(new LeaderBoardScreen(this));
	}
}
