package com.testgame;






import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.testgame.GameScreen.GoToMenuListener;
import com.testgame.GameScreen.Listener;
import com.testgame.GameScreen.ListenerMouseMoved;


public class Joystick implements Screen {
	private TextureActor mainCircle;
	private TextureActor secondaryCircle;
	private TextureActor button;
	private Stage stage;
	
	private float centerX = 0; //***
	private float centerY = 0; 
	
	private int joystickRadius;
	private int buttonRadius;
	
	ShapeRenderer shapes; 
	
	
	
	
		
	
	public Joystick(SpriteBatch batch) {
		
		shapes = new ShapeRenderer();
		
		centerX = (Gdx.graphics.getWidth()) / 2;
		centerY = (Gdx.graphics.getHeight()) / 2;

		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);
		
		
		button = new TextureActor(new Texture("data/menu-button.png"));		
		stage.addActor(button);		
		button.setPosition(centerX, centerY); 
	    
		//Touchpad t = new Touchpad(centerX, null);
		
	}
	

	
	
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
	
	

}
