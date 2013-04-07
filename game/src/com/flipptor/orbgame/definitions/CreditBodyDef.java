package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * 
 * @author Viktor Anderling
 *
 * A body definition for a credit's body.
 * 
 */
public class CreditBodyDef extends BodyDef{

	public CreditBodyDef(Vector2 position) {
		super();
		this.type = BodyType.DynamicBody;
		this.allowSleep = false;
		this.fixedRotation = true;
		this.linearDamping = 1f;
		this.position.set(position);
	}
}
