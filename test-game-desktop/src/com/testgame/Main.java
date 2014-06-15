package com.testgame;

import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	public static void main(String[] args) throws IOException {
		new LwjglApplication(new TouchPadTest(), "MyTestGame", 480, 320, false);
		//коммент
	}
}
