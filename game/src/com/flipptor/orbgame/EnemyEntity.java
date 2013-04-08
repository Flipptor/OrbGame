package com.flipptor.orbgame;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.definitions.EnemyBodyDef;

public class EnemyEntity extends Entity {

	public EnemyEntity(World world, Vector2 position, RayHandler rayHandler) {
		super(world, new EnemyBodyDef(position), new PointLight(
				rayHandler, Settings.numberOfRays, Color.RED, 50, position.x, position.y));
		// TODO Auto-generated constructor stub
	}

}
