package com.flipptor.orbgame.entities;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.definitions.EnemyBodyDef;

public class EnemyEntity extends Entity {

	private static final float MOVEMENT_MULTIPLIER = 5;
	private static float maxPlayerDistance = 30;
	
	public EnemyEntity(World world, Vector2 position, RayHandler rayHandler) {
		super(world, new EnemyBodyDef(position), new PointLight(
				rayHandler, Settings.numberOfRays, Color.RED, 25, position.x, position.y));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		Vector2 toPlayerVec = findVectorToPlayer();
		if(toPlayerVec.len() <= maxPlayerDistance) {
			toPlayerVec.nor().scl(MOVEMENT_MULTIPLIER);
			this.getBody().applyForceToCenter(toPlayerVec.x, toPlayerVec.y);
		}
		
	}

	private Vector2 findVectorToPlayer() {
		Vector2 vec = new Vector2(EntityHandler.PLAYER_POSITION);
		return vec.sub(this.getBody().getPosition());
	}

}
