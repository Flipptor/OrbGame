package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * 
 * @author Viktor Anderling
 *
 * A singleton class for defining fixture of bullets.
 * 
 */
public class BulletFixtureDef extends FixtureDef {
	
	public BulletFixtureDef INSTANCE = new BulletFixtureDef();
	
	private BulletFixtureDef() {
		// TODO set these variables to better values.
		PolygonShape s = new PolygonShape();
		s.setAsBox(2, 1);
		this.shape = s;
		this.shape.setRadius(1f);
		this.density = 0;
		this.friction = 0;
		this.restitution = 0;
	}
}
