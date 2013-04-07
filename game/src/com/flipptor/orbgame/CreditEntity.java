package com.flipptor.orbgame;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class CreditEntity extends Entity {

	/** The value of the credit */
	private int value;
	
	public CreditEntity(World world, BodyDef bodyDef, RayHandler rayHandler, int value) {
		super(world, bodyDef, new PointLight(rayHandler, Settings.numberOfRays, 
				Color.GREEN, 50, bodyDef.position.x, bodyDef.position.y));
	}
	
	/**
	 * @return The value of this credit.
	 */
	public int getValue() {
		return value;
	}
}
