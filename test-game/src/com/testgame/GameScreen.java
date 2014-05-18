package com.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen {
	private TextureActor menuButton;
	private Stage stage;
	private float ActX, ActY;
	
	
	class GoToMenuListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			MyTestGame.getInstance().showMenu();
		}
	}
	
	class Listener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			//ActX = getTouchDownX();
			//ActY = getTouchDownY();
		}
	}
	class ListenerMouseMoved extends InputListener {
		@Override
		public boolean mouseMoved(InputEvent event, float x, float y) {
			ActX = x;
			ActY = y;
			return false;
		}
		
		
	}
	
	
		
	
	public GameScreen(SpriteBatch batch) {
		menuButton = new TextureActor(new Texture("data/menu-button.png"));
		menuButton.addListener(new GoToMenuListener());
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);
		stage.addActor(menuButton);
		
		menuButton.setPosition(ActX, ActY); 
		stage.addListener(new Listener());
		stage.addListener(new ListenerMouseMoved());
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		menuButton.setPosition(ActX, ActY); 
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void dispose() {}
}
