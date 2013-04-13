package com.flipptor.orbgame.entities;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.GameInput;
import com.flipptor.orbgame.IGameInput;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.Status;
import com.flipptor.orbgame.definitions.PlayerBodyDef;

public class PlayerEntity extends Entity {
	
	private static final float MAX_SPEED = 20;
	private static final float DASH_TIME = 1f; // in seconds.
	private float dashTimeLeft;
	
	private int credits = 0;
	private Status status;
	private IGameInput input;
	private Vector2 movementForce;
	private Vector2 dashForce;
	private static final float MOVEMENT_MULTIPLIER = 10;
	private float dashMultiplier = 1000;
	
	public PlayerEntity(World world, Vector2 position, RayHandler rayHandler) {
		super(world, new PlayerBodyDef(position), 
				new PointLight(rayHandler, Settings.numberOfRays, 
						Color.GRAY, 10, position.x, position.y));
		this.input = new GameInput();
		this.status = new Status();
	}
	
	public void update() {
		input.update();
		status.update();
		updateMovement();
	}
	
	private void updateMovement() {
		// Update dash timing.
		if(dashTimeLeft != 0) {
			dashTimeLeft -= Gdx.graphics.getDeltaTime();
			if(dashTimeLeft < 0) {
				dashTimeLeft = 0;
			}
		}
		Vector2 speed = this.getBody().getLinearVelocity();
		if(speed.len() > getCurrentMaxSpeed()) {
			this.getBody().setLinearVelocity(speed.limit(getCurrentMaxSpeed()));
		}
		if(input.isDashing()) {
			this.getBody().setLinearVelocity(0, 0); // Stops the entity.
			dashForce = input.getDashVector().scl(dashMultiplier);
			this.getBody().applyForceToCenter(dashForce);
			dashTimeLeft = DASH_TIME;
		}
		movementForce = input.getMovementVector().scl(MOVEMENT_MULTIPLIER);
		this.getBody().applyForceToCenter(movementForce);
	}
	
	private float getCurrentMaxSpeed() {
		return MAX_SPEED + (dashTimeLeft/DASH_TIME)*MAX_SPEED*2;
	}

	/**
	 * Adds a number of credits to the player.
	 * 
	 * @param value The number of credits to be added.
	 */
	public void addCredits(int value) {
		credits += value;
		// TODO maybe increase score here, or maybe in EntityHandler?
	}
	
	/**
	 * Removes a number of credits from the player.
	 * If the players total credits is less than the amount removed, it will be
	 * reduced to zero instead.
	 * 
	 * @param value The number of credits to be removed.
	 */
	public void removeCredits(int value) {
		if(value >= credits) {
			credits = 0;
		} else {
			credits -= value;
		}
	}
	
	/**
	 * @return The players total amount of credits.
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * @return The status of this player.
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * @return true if the player is currently dashing, false otherwise.
	 */
	public boolean isDashing() {
		return dashTimeLeft != 0;
	}
}
