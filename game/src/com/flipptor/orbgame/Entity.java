package com.flipptor.orbgame;

import box2dLight.PointLight;

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
	
	private Body body;
	private PointLight light;
	
	public Entity(World world, BodyDef bodyDef, PointLight light) {
		body = world.createBody(bodyDef);
		body.setUserData(this);
		this.light = light;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void move(float dX, float dY) {
		float newX = body.getPosition().x + dX;
		float newY = body.getPosition().y + dY;
		setPosition(newX, newY);
	}
	
	public void setPosition(float x, float y) {
		body.setTransform(x, y, 0);
		light.setPosition(x, y);
	}
}
