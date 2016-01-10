package com.anodev.cdots.desktop;

import com.anodev.cdots.DotsGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 960;
		config.width = 540;
		new LwjglApplication(new DotsGame(), config);
	}
}
