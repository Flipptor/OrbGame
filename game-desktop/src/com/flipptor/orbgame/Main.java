package com.flipptor.orbgame;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static double height = screenSize.getHeight();
	private static double width = screenSize.getWidth();
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "game";
		cfg.useGL20 = true;
		cfg.width = (int) width;
		cfg.height = (int) height;
		
		
		new LwjglApplication(new OrbGame(), cfg);
	}
}
