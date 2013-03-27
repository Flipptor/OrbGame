package com.flipptor.orbgame.screens;

import com.badlogic.gdx.Screen;
import com.flipptor.orbgame.OrbGame;
import com.flipptor.orbgame.WorldRenderer;

public class GameScreen implements Screen {
	OrbGame game;
	WorldRenderer renderer;
	
	public GameScreen(OrbGame game) {
		this.game = game;
		renderer = new WorldRenderer(game);
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
