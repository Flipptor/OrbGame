package com.flipptor.orbgame.definitions;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class EnemyFixtureDef extends FixtureDef {
	
	public EnemyFixtureDef(float radius) {
		super();
		this.shape = new CircleShape();
		this.density = 0;
		this.friction = 0;
		this.restitution = 0;
		this.shape.setRadius(radius);
	}
}
