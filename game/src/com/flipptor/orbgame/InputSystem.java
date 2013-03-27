package com.flipptor.orbgame;

import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author Viktor Anderling
 *
 * An interface describing an input system for a player.
 * 
 */
public interface InputSystem {
	
	public Vector3 getMovementVector();
	
	public boolean isDashing();
	
	public Vector3 getDashVector();
	
	public boolean isShooting();
	
	public boolean getShotVector();
	
}
