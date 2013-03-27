package com.flipptor.orbgame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerEntity extends Entity{
	
	float health = 3;
	
	
	public PlayerEntity(World world, BodyDef bodyDef) {
		super(world, bodyDef);
		// TODO Auto-generated constructor stub
		
	}
	
	public void update(){
		
	}
}
