package com.flipptor.orbgame;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Viktor Anderling
 *
 * An interface describing an input system for a player.
 * 
 */
public interface GameInput {
	
	public Vector2 getMovementVector();
	
	public boolean isDashing();
	
	public Vector2 getDashVector();
	
	public boolean isShooting();
	
	public boolean getShotVector();
	
}
