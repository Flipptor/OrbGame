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
	
	/** Larger scaling means objects becomes larger and closer to each other */
	public static int worldToScreenScale = 20;
	
	/** The resolution of the lightning engine */
	public static int numberOfRays = 1000;
	
	/** The difficulty of the game */
	public static Difficulty difficulty = Difficulty.NORMAL;
	
	private static final Settings INSTANCE = new Settings();
	
	private Settings() {}
	
	public enum Difficulty {
		EASY, NORMAL, HARD;
	}
	
}
