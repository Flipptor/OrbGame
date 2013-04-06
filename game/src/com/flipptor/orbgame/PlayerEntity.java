package com.flipptor.orbgame;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerEntity extends Entity {
	
	private float health = 3;
	private IGameInput input;
	private Vector2 movementForce;
	private Vector2 dashForce;
	private static final float MOVEMENT_MULTIPLIER = 10;
	private float dashMultiplier = 100000;
	
	public PlayerEntity(World world, BodyDef bodyDef, RayHandler rayHandler) {
		super(world, bodyDef, new PointLight(rayHandler, 40000, Color.GRAY, 50, 
				bodyDef.position.x, bodyDef.position.y));
		// TODO Auto-generated constructor stub
		input = new GameInput();
		//TODO add light!
	}
	
	public void update() {
		input.update();
		movementForce = input.getMovementVector();
		movementForce.set(movementForce.x*MOVEMENT_MULTIPLIER, 
				movementForce.y*MOVEMENT_MULTIPLIER);
		this.getBody().applyForceToCenter(movementForce);
		if(input.isDashing()) {
			dashForce = input.getDashVector();
			dashForce.set(dashForce.x*dashMultiplier, dashForce.y*dashMultiplier);
			this.getBody().applyForceToCenter(dashForce);
		}
	}
}
