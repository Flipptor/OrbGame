package com.flipptor.orbgame;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class CreditEntity extends Entity {

	public CreditEntity(World world, BodyDef bodyDef, RayHandler rayHandler) {
		super(world, bodyDef, new PointLight(rayHandler, Settings.numberOfRays, 
				Color.GREEN, 50, bodyDef.position.x, bodyDef.position.y));
		// TODO Auto-generated constructor stub
	}

}
