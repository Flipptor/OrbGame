package com.flipptor.orbgame.entities;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.definitions.CreditBodyDef;

public class CreditEntity extends Entity {

	/** The value of the credit */
	private int value = 1;
	
	public CreditEntity(World world, Vector2 position, RayHandler rayHandler, int value) {
		super(world, new CreditBodyDef(position), new PointLight(rayHandler, Settings.numberOfRays, 
				Color.GREEN, 10, position.x, position.y));
	}
	
	/**
	 * @return The value of this credit.
	 */
	public int getValue() {
		return value;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
