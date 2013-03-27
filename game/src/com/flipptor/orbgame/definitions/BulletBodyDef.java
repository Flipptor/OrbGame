package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * 
 * @author Viktor Anderling
 *
 * A body definition for a bullet body.
 * 
 */
public class BulletBodyDef extends BodyDef {
	
	public BulletBodyDef(Vector2 position) {
		super();
		this.type = BodyType.DynamicBody;
		this.allowSleep = false;
		this.fixedRotation = true;
		this.linearDamping = 0f;
		this.bullet = true;
		this.position.set(position);
	}
}
