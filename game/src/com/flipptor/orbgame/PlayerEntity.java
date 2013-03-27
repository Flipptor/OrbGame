package com.flipptor.orbgame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerEntity extends Entity {
	
	float health = 3;
	IGameInput input;
	Vector2 force;
	private static final float MULTIPLIER = 10;
	
	public PlayerEntity(World world, BodyDef bodyDef) {
		super(world, bodyDef);
		// TODO Auto-generated constructor stub
		input = new GameInput();
	}
	
	public void update() {
		force = input.getMovementVector();
		force.set(force.x*MULTIPLIER, force.y*MULTIPLIER);
		this.body.applyForceToCenter(force);
	}
}
