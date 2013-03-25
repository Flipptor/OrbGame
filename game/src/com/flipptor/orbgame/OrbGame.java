package com.flipptor.orbgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flipptor.orbgame.screens.GameScreen;
import com.flipptor.orbgame.screens.SplashScreen;

public class OrbGame extends Game {
	
	private Screen guideScreen;
	private Screen ingameMenu;
	private Screen gameOverScreen;
	private Screen mainMenuScreen;
	
	protected Screen splashScreen;
	protected Screen gameScreen;
	
	private Screen highscoreScreen;
	
	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}
}
