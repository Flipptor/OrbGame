package com.flipptor.orbgame.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.definitions.CreditFixtureDef;
import com.flipptor.orbgame.definitions.EnemyTypes;
import com.flipptor.orbgame.definitions.PlayerFixtureDef;

public class EntityHandler {
	private static final float WIDTH = Gdx.graphics.getWidth()/Settings.worldToScreenScale;
	private static final float HEIGHT = Gdx.graphics.getHeight()/Settings.worldToScreenScale;
	public static final Vector2 PLAYER_POSITION = new Vector2(WIDTH/2, HEIGHT/2);
	private static final float MAX_ENTITY_DISTANCE = WIDTH*3f;
	private static final int MAX_EXISTING_ENEMIES = 35;
	private static final int MAX_EXISTING_CREDITS = 45;
	
	private LinkedList<EnemyEntity> enemyList;
	private LinkedList<CreditEntity> creditList;
	
	/**	A list containing all the contacts made since last world step */
	private List<Contact> contactList;
	
	private PlayerEntity player;
	private float dX, dY;
	private final RayHandler rayHandler;
	private World world;
	
	public EntityHandler(World world, RayHandler rayHandler) {
		this.rayHandler = rayHandler;
		this.world = world;
		//world.setContactListener(this);
		enemyList = new LinkedList<EnemyEntity>();
		creditList = new LinkedList<CreditEntity>();
		contactList = new ArrayList<Contact>();
		
		player = new PlayerEntity(world, PLAYER_POSITION, rayHandler);
		player.getBody().createFixture(PlayerFixtureDef.INSTANCE);
	}
	
	/**
	 * Updates all entities by moving, handling contact of, spawning and
	 * despawning entities.
	 */
	public void update() {
		handleContacts();
		moveEntities();
		updateEntities();
		despawnEntities();
		spawnEntities();
	}
	
	/**
	 * Handles all contacts that has been made since last call.
	 */
	private void handleContacts() {
		contactList = world.getContactList();
		
		for(Contact contact : contactList) {
			Fixture fixtureA = contact.getFixtureA(); // TODO maybe remove these two...
			Fixture fixtureB = contact.getFixtureB();
			if(fixtureA != null && fixtureB != null) {
				Entity entityA = (Entity) fixtureA.getBody().getUserData();
				Entity entityB = (Entity) fixtureB.getBody().getUserData();
				
				if(entityA instanceof PlayerEntity) {
					playerColliding((PlayerEntity) entityA, entityB);
				} else if(entityB instanceof PlayerEntity) {
					playerColliding((PlayerEntity) entityB, entityA);
				}
				// TODO add more collisions for bullets etc.
			}
		}
	}
	
	private void playerColliding(PlayerEntity player, Entity other) {
		
		// if player collides with a credit.
		if(other instanceof CreditEntity) {
			player.addCredits(((CreditEntity) other).getValue());
			creditList.remove(other);
			removeEntity(other);
		}
		// if player collides with an enemy.
		else if(other instanceof EnemyEntity) {
			player.getStatus().inflictDamage(1);
			// TODO add collision logic between player and enemies.
		}
	}
	
	private void moveEntities() {
		Vector2 pos = player.getBody().getPosition();
		dX = pos.x - PLAYER_POSITION.x;
		dY = pos.y - PLAYER_POSITION.y;
		player.getBody().setTransform(PLAYER_POSITION.x, PLAYER_POSITION.y, 0);
		for(Entity e : enemyList) {
			e.move(-dX, -dY);
		}
		for(Entity e : creditList) {
			e.move(-dX, -dY);
		}
	}
	
	private void updateEntities() {
		player.update();
		for(EnemyEntity enemy : enemyList) {
			enemy.update();
		}
	}
	
	private void despawnEntities() {
		LinkedList<Entity> entitiesToBeRemoved = new LinkedList<Entity>();
		for(EnemyEntity entity : enemyList) {
			if(outOfRange(entity.getBody().getPosition(), 
					MAX_ENTITY_DISTANCE)) {
				entitiesToBeRemoved.add(entity);
			}
		}
		for(CreditEntity entity : creditList) {
			if(outOfRange(entity.getBody().getPosition(), 
					MAX_ENTITY_DISTANCE)) {
				entitiesToBeRemoved.add(entity);
			}
		}
		for(Entity entity : entitiesToBeRemoved) {
			removeEntity(entity);
		}
	}
	
	private boolean outOfRange(Vector2 position, float range) {
		return (Math.abs(position.x - WIDTH/2) > range 
				|| Math.abs(position.y - HEIGHT/2) > range);
	}
	
	private void removeEntity(Entity entity) {
		if(entity instanceof EnemyEntity) {
			enemyList.remove(entity);
		} else if(entity instanceof CreditEntity) {
			creditList.remove(entity);
		}
		world.destroyBody(entity.getBody());
		entity.disposeLight();
	}
	
	private void spawnEntities() {
		Entity newEntity;
		while(enemyList.size() < MAX_EXISTING_ENEMIES) {
			newEntity = new EnemyEntity(world, 
					findValidSpawnpoint(), rayHandler);
			newEntity.getBody().createFixture(EnemyTypes.MEDIUM.fixtureDef);
			enemyList.add((EnemyEntity)newEntity);
		}
		while(creditList.size() < MAX_EXISTING_CREDITS) {
			newEntity = new CreditEntity(world, 
					findValidSpawnpoint(), rayHandler, 1);
			newEntity.getBody().createFixture(new CreditFixtureDef());
			creditList.add((CreditEntity)newEntity);
		}
	}

	private Vector2 findValidSpawnpoint() {
		Vector2 maxCoord = new Vector2(PLAYER_POSITION.x + WIDTH*0.75f, 
				PLAYER_POSITION.y + HEIGHT*0.75f);
		Vector2 minCoord = new Vector2(PLAYER_POSITION.x - WIDTH*0.75f, 
				PLAYER_POSITION.y - HEIGHT*0.75f);
		Vector2 coordinate = new Vector2(0, 0);
		do {
			coordinate.set(PLAYER_POSITION.x 
					- MAX_ENTITY_DISTANCE*(1 - (float)Math.random()*2),
					PLAYER_POSITION.y 
					- MAX_ENTITY_DISTANCE*(1 - (float)Math.random()*2));
		} while(coordinate.x < maxCoord.x && coordinate.x > minCoord.x 
				&& coordinate.y < maxCoord.y && coordinate.y > minCoord.y);
		return coordinate;
	}
	
	public PlayerEntity getPlayerEntity() {
		return player;
	}
}
