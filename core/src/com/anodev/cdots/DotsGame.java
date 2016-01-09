package com.anodev.cdots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class DotsGame extends Game {
	
	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		setScreen(new GameScreen());

	}

}
