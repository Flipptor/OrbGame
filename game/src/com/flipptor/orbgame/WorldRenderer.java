package com.flipptor.orbgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class WorldRenderer {
	World world;
	OrthographicCamera camera;
	OrbGame game;
	
	public WorldRenderer(OrbGame game){
		this.game = game;
		world = new World(new Vector2(0,0), false);
		
		
	}
}
