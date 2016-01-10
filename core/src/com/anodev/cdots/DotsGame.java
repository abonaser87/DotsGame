package com.anodev.cdots;

import com.badlogic.gdx.Game;


public class DotsGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
