package com.flipptor.orbgame.entities;

import java.util.ArrayList;
import java.util.LinkedList;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.Settings;
import com.flipptor.orbgame.definitions.CreditFixtureDef;
import com.flipptor.orbgame.definitions.EnemyTypes;
import com.flipptor.orbgame.definitions.PlayerFixtureDef;

public class EntityHandler implements ContactListener {
	private static final float WIDTH = Gdx.graphics.getWidth()/Settings.worldToScreenScale;
	private static final float HEIGHT = Gdx.graphics.getHeight()/Settings.worldToScreenScale;
	public static final Vector2 PLAYER_POSITION = new Vector2(WIDTH/2, HEIGHT/2);
	
	private LinkedList<EnemyEntity> enemyList;
	private LinkedList<CreditEntity> creditList;
	
	/**	A list containing all the contacts made since last world step */
	private ArrayList<Contact> contactList;
	
	private PlayerEntity player;
	private float dX, dY;
	private final RayHandler rayHandler;
	private World world;
	
	public EntityHandler(World world, RayHandler rayHandler) {
		this.rayHandler = rayHandler;
		this.world = world;
		world.setContactListener(this);
		enemyList = new LinkedList<EnemyEntity>();
		creditList = new LinkedList<CreditEntity>();
		contactList = new ArrayList<Contact>();
		
		player = new PlayerEntity(world, PLAYER_POSITION, rayHandler);
		player.getBody().createFixture(PlayerFixtureDef.INSTANCE);
		
		// TODO remove later.
		EnemyEntity newEntity = new EnemyEntity(world, 
				new Vector2(WIDTH*1.3f/2, HEIGHT*1.3f/2), rayHandler);
		newEntity.getBody().createFixture(EnemyTypes.MEDIUM.fixtureDef);
		enemyList.add(newEntity);
		newEntity = new EnemyEntity(world, 
				new Vector2(WIDTH*0.7f/2, HEIGHT*0.7f/2), rayHandler);
		newEntity.getBody().createFixture(EnemyTypes.MEDIUM.fixtureDef);
		enemyList.add(newEntity);
		CreditEntity newEntity2 = new CreditEntity(world, 
				new Vector2(WIDTH*1.3f/2, HEIGHT*1f/2), rayHandler, 1);
		newEntity2.getBody().createFixture(new CreditFixtureDef());
		creditList.add(newEntity2);
	}
	
	/**
	 * Updates all entities by moving, handling contact of, spawning and
	 * despawning entities.
	 */
	public void update() {
		handleContacts();
		moveEntities();
		updateEntities();
	}
	
	/**
	 * Handles all contacts that has been made since last call.
	 */
	private void handleContacts() {
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
			world.destroyBody(other.getBody());
			other.disposeLight();
		}
		// if player collides with an enemy.
		else if(other instanceof EnemyEntity) {
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

	@Override
	public void beginContact(Contact contact) {
		contactList.add(contact);
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	}
}
