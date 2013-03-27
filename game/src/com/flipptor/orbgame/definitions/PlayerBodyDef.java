package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * 
 * @author Viktor Anderling
 *
 * A body definition for the player's body.
 * 
 */
public class PlayerBodyDef extends BodyDef {
	
	public PlayerBodyDef(Vector2 position) {
		super();
		this.type = BodyType.DynamicBody;
		this.allowSleep = false;
		this.fixedRotation = true;
		this.linearDamping = 0f;
		this.position.set(position);
	}
}
