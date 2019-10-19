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
		this.ballImage = new Texture(Gdx.files.internal("ball.png"));
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800.0F, 480.0F);
		this.batch = new SpriteBatch();
		this.ball = new Rectangle();
		this.ball.x = 120.0F;
		this.ball.y = 120.0F;
		this.ball.width = 64.0F;
		this.ball.height = 64.0F;
	}

	public void show() {
	}

	public void render(float f) {
		Gdx.gl.glClearColor(0.0F, 0.0F, 0.2F, 1.0F);
		Gdx.gl.glClear(16384);
		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.batch.draw(this.ballImage, this.ball.x, this.ball.y);
		this.batch.end();
		double delta = 3.0D;
		Rectangle var10000;
		if (this.ball.x < 0.0F) {
			Gdx.input.vibrate(10);
			this.ball.x = 0.0F;
			delta = 10.0D;
			var10000 = this.ball;
			var10000.x = (float)((double)var10000.x + delta);
		}

		if (this.ball.x > 740.0F) {
			Gdx.input.vibrate(10);
			this.ball.x = 740.0F;
			delta = 10.0D;
			var10000 = this.ball;
			var10000.x = (float)((double)var10000.x + delta);
		}

		if (this.ball.y < 0.0F) {
			Gdx.input.vibrate(10);
			this.ball.y = 0.0F;
			delta = 10.0D;
			var10000 = this.ball;
			var10000.y = (float)((double)var10000.y + delta);
		}

		if (this.ball.y > 420.0F) {
			Gdx.input.vibrate(10);
			this.ball.y = 420.0F;
			delta = 10.0D;
			var10000 = this.ball;
			var10000.y = (float)((double)var10000.y + delta);
		}

		float gyroX = Gdx.input.getGyroscopeX();
		float gyroY = Gdx.input.getGyroscopeY();
		if (gyroX < 0.0F) {
			var10000 = this.ball;
			var10000.x = (float)((double)var10000.x - delta);
		} else {
			var10000 = this.ball;
			var10000.x = (float)((double)var10000.x + delta);
		}

		if (gyroY < 0.0F) {
			var10000 = this.ball;
			var10000.y = (float)((double)var10000.y - delta);
		} else {
			var10000 = this.ball;
			var10000.y = (float)((double)var10000.y + delta);
		}

	}

	public void dispose() {
		this.ballImage.dispose();
		this.batch.dispose();
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
