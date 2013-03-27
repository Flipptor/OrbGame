package com.flipptor.orbgame.definitions;

/**
 * 
 * @author Viktor Anderling
 *
 * An enumerator for defining fixtures of different enemies.
 *
 */
public enum EnemyFixtures {
	
	SMALL(1), MEDIUM(2), LARGE(3);
	
	public EnemyFixtureDef fixtureDef;
	
	private EnemyFixtures(float radius) {
		fixtureDef = new EnemyFixtureDef(radius);
	}
		
}