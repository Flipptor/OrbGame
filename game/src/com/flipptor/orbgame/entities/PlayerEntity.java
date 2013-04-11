package com.flipptor.orbgame.entities;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.GameInput;
import com.flipptor.orbgame.IGameInput;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.Status;
import com.flipptor.orbgame.definitions.PlayerBodyDef;

public class PlayerEntity extends Entity {
	
	private int credits = 0;
	private Status status;
	private IGameInput input;
	private Vector2 movementForce;
	private Vector2 dashForce;
	private static final float MOVEMENT_MULTIPLIER = 10;
	private float dashMultiplier = 100000;
	
	public PlayerEntity(World world, Vector2 position, RayHandler rayHandler) {
		super(world, new PlayerBodyDef(position), 
				new PointLight(rayHandler, Settings.numberOfRays, 
						Color.GRAY, 50, position.x, position.y));
		this.input = new GameInput();
		this.status = new Status();
	}
	
	public void update() {
		input.update();
		status.update();
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
}
