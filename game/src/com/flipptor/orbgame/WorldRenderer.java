package com.flipptor.orbgame;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.flipptor.orbgame.entities.EntityHandler;
import com.flipptor.orbgame.entities.PlayerEntity;

public class WorldRenderer {
	private World world;
	private OrthographicCamera camera;
	private OrbGame game;
	private float width, height;
	private Box2DDebugRenderer renderer;
	private RayHandler rayHandler;
	private EntityHandler entityHandler;
	
	private SpriteBatch batch;
	private String[] debugText = new String[2];
	private BitmapFont font;
	
	
	public WorldRenderer(OrbGame game) {
		this.game = game;
		
		//TODO fix so that resolution doesn't matter!
		width = Gdx.graphics.getWidth()/Settings.worldToScreenScale;
		height = Gdx.graphics.getHeight()/Settings.worldToScreenScale;
		System.out.println("width: " + width + ", height: " + height);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		world = new World(new Vector2(0,0), false);
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(width*0.5f, height*0.5f, 0);
		camera.update();
		
		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);
		
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(camera.combined);
		
		entityHandler = new EntityHandler(world, rayHandler);
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.setScale(2f);
		
		
		
	}
	
	public void render() {
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderer.render(world, camera.combined);
		
		rayHandler.update();
		rayHandler.render();

		world.step(1/60f, 6, 2);
		entityHandler.update();
		
		batch.begin();
		
		debugText = getDebugText();
		for(int i = 0; i < debugText.length; i++) {
			font.draw(batch, debugText[i], 10f, 0f + font.getCapHeight()*(i+1) + font.getLineHeight()*(i+1));
		}
		
		batch.end();
	}
	
	private String[] getDebugText() {
		PlayerEntity player = entityHandler.getPlayerEntity();
		debugText[0] = "credits: " + player.getCredits();
		debugText[1] = "lives: " + player.getStatus().getHealth(false);
		return debugText; 
	}
}
