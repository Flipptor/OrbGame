package com.flipptor.orbgame;

import java.util.LinkedList;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.definitions.EnemyBodyDef;
import com.flipptor.orbgame.definitions.EnemyFixtures;
import com.flipptor.orbgame.definitions.PlayerBodyDef;
import com.flipptor.orbgame.definitions.PlayerFixtureDef;

public class EntityHandler {
	private static final float WIDTH = Gdx.graphics.getWidth()/10;
	private static final float HEIGHT = Gdx.graphics.getHeight()/10;
	
	private LinkedList<Entity> enemyList, creditList;
	private PlayerEntity player;
	private float dX, dY;
	private final RayHandler rayHandler;
	private World world;
	
	public EntityHandler(World world, RayHandler rayHandler) {
		this.rayHandler = rayHandler;
		this.world = world;
		enemyList = new LinkedList<Entity>();
		creditList = new LinkedList<Entity>();
		player = new PlayerEntity(world, 
				new PlayerBodyDef(new Vector2(WIDTH/2, HEIGHT/2)), rayHandler);
		player.getBody().createFixture(PlayerFixtureDef.INSTANCE);
		
		// TODO remove later.
		EnemyEntity newEnemy = new EnemyEntity(world, new EnemyBodyDef(
				new Vector2(WIDTH*1.3f/2, HEIGHT*1.3f/2)), rayHandler);
		newEnemy.getBody().createFixture(EnemyFixtures.MEDIUM.fixtureDef);
		enemyList.add(newEnemy);
		newEnemy = new EnemyEntity(world, new EnemyBodyDef(
				new Vector2(WIDTH*0.7f/2, HEIGHT*0.7f/2)), rayHandler);
		newEnemy.getBody().createFixture(EnemyFixtures.MEDIUM.fixtureDef);
		enemyList.add(newEnemy);
	}
	
	public void update() {
		moveEntities();
		player.update();
	}
	
	private void moveEntities() {
		Vector2 pos = player.getBody().getPosition();
		dX = pos.x - WIDTH/2;
		dY = pos.y - HEIGHT/2;
		player.getBody().setTransform(WIDTH/2, HEIGHT/2, 0);
		for(Entity e : enemyList) {
			e.move(-dX, -dY);
		}
		for(Entity e : creditList) {
			e.move(-dX, -dY);
		}
	}
}
