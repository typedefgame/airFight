package com.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyTestGame extends Game {
	private SpriteBatch batch;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private Joystick joystickScreen;
	
	private static MyTestGame instance = new MyTestGame();
	
	private MyTestGame() {
	}
	
	public static MyTestGame getInstance() {
		return instance;
	}
	
	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(batch);
		gameScreen = new GameScreen(batch);	
		joystickScreen = new Joystick(batch);	
		
		
		setScreen(joystickScreen);
	}
	
	public void showMenu() {
		setScreen(menuScreen);
	}
	
	public void showGame() {
		setScreen(gameScreen);
	}
}
