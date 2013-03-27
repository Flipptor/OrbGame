package com.flipptor.orbgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.flipptor.orbgame.screens.GameScreen;
import com.flipptor.orbgame.screens.SplashScreen;

public class OrbGame extends Game {
	
	private Screen guideScreen;
	private Screen ingameMenu;
	private Screen gameOverScreen;
	private Screen mainMenuScreen;
	private Screen highscoreScreen;
	
	protected Screen splashScreen;
	protected Screen gameScreen;
	
	
	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}
}
