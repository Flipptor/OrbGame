package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * 
 * @author Viktor Anderling
 *
 * A singleton class for defining fixture of the player.
 * 
 */
public class PlayerFixtureDef extends FixtureDef {

	public PlayerFixtureDef INSTANCE = new PlayerFixtureDef();
	
	private PlayerFixtureDef() {
		// TODO set these variables to better values.
		this.shape = new CircleShape();
		this.shape.setRadius(1f);
		this.density = 0;
		this.friction = 0;
		this.restitution = 0;
	}
}
