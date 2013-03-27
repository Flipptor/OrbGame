package com.flipptor.orbgame;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * @author John Eriksson
 *
 * Main framework for an Entity.
 *
 */

public abstract class Entity {
	
	public Body body;
	
	public Entity(World world, BodyDef bodyDef) {
		body = world.createBody(bodyDef);
	}
	
	public Body getBody() {
		return body;
	}	
}
