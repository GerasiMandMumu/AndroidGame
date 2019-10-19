package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGame implements Screen {
	Texture ballImage;
	SpriteBatch batch;
	OrthographicCamera camera;
	Rectangle ball;

	public MyGame() {
		ballImage = new Texture(Gdx.files.internal("ball.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		ball = new Rectangle();
		ball.x = 120;
		ball.y = 120;
		ball.width = 64;
		ball.height = 64;
	}

	public void show() {
	}

	public void render(float f) {
		Gdx.gl.glClearColor(0.0F, 0.0F, 0.2F, 1.0F);
		Gdx.gl.glClear(16384);
		camera.update();
		batch.setProjectionMatrix(this.camera.combined);
		batch.begin();
		batch.draw(this.ballImage, this.ball.x, this.ball.y);
		batch.end();
		double delta = 3;
		if (ball.x < 0) {
			Gdx.input.vibrate(10);
			ball.x = 0;
			delta = 10;
			ball.x += delta;
		}

		if (ball.x > 740) {
			Gdx.input.vibrate(10);
			ball.x = 740;
			delta = 10;
			ball.x += delta;
		}

		if (ball.y < 0) {
			Gdx.input.vibrate(10);
			ball.y = 0;
			delta = 10;
			ball.y += delta;
		}

		if (ball.y > 420) {
			Gdx.input.vibrate(10);
			ball.y = 420;
			delta = 10;
			ball.y += delta;
		}

		float gyroX = Gdx.input.getGyroscopeX();
		float gyroY = Gdx.input.getGyroscopeY();
		if (gyroX < 0) {
			ball.x -= delta;
		}
		else {
			ball.x += delta;
		}

		if (gyroY < 0) {
			ball.y -= delta;
		}
		else {
			ball.y += delta;
		}

	}

	public void dispose() {
		ballImage.dispose();
		batch.dispose();
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void hide() {
	}
}
