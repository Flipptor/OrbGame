package com.flipptor.orbgame;

/**
 * A singleton class for storing setting-specific variables such 
 * as graphic options etc.
 * 
 * @author Viktor Anderling
 * 
 */
public class Settings {
	// TODO maybe make this class serializeable?
	
	public static int numberOfRays = 1000;
	public static Difficulty difficulty = Difficulty.NORMAL;
	
	private static final Settings INSTANCE = new Settings();
	
	private Settings() {}
	
	public enum Difficulty {
		EASY, NORMAL, HARD;
	}
	
}
