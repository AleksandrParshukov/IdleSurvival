package com.pcpc.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pcpc.game.IdleSurvivalMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = IdleSurvivalMain.HEIGHT;
		config.width = IdleSurvivalMain.WIDTH;
		config.title = IdleSurvivalMain.TITLE;
		new LwjglApplication(new IdleSurvivalMain(), config);
	}
}
