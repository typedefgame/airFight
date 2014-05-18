package com.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextureActor extends Actor {
	private Texture toDraw;
	
	public TextureActor(Texture toDraw) {
		this.toDraw = toDraw;
		setSize(100, 40);
		setPosition(100, 100);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(toDraw, getX(), getY(), getWidth(), getHeight());
	}
}
