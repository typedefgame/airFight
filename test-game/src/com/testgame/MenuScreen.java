package com.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
	private TextureActor gameButton;
	private Stage stage;
	
	class GoToGameListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			MyTestGame.getInstance().showGame();
		}
	}
	
	public MenuScreen(SpriteBatch batch) {
		gameButton = new TextureActor(new Texture("data/game-button.png"));
		gameButton.setPosition(200, 200);
		gameButton.addListener(new GoToGameListener());
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);
		stage.addActor(gameButton);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
