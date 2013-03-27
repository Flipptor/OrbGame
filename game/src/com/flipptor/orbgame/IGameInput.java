package com.flipptor.orbgame;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Viktor Anderling
 *
 * An interface describing an input system for a player.
 * 
 */
public interface IGameInput {
	
	/**
	 * @return A vector containing movement information with direction
	 * and acceleration.
	 */
	public Vector2 getMovementVector();
	
	/**
	 * @return True if player is dashing, false otherwise.
	 */
	public boolean isDashing();
	
	/**
	 * @return A vector containing the dashing direction.
	 * Returns a null vector (null in the sense of both values are zero) if
	 * the player is not dashing.
	 */
	public Vector2 getDashVector();
	
	/**
	 * @return True if player is shooting, false otherwise.
	 */
	public boolean isShooting();
	
	/**
	 * @return A vector containing the shooting direction.
	 * Returns a null vector (null in the sense of both values are zero) if
	 * the player is not shooting.
	 */
	public boolean getShotVector();
	
}
