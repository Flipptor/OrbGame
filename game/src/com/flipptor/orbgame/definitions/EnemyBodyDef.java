package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * 
 * @author Viktor Anderling
 *
 * A body definition for an enemy's body.
 * 
 */
public class EnemyBodyDef extends BodyDef {
	
	public EnemyBodyDef(Vector2 position) {
		super();
		this.type = BodyType.DynamicBody;
		this.allowSleep = false;
		this.fixedRotation = true;
		this.linearDamping = 1f;
		this.position.set(position);
	}
}
