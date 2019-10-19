package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Menu implements Screen {
    OrthographicCamera camera;
    Main game;

    public Menu(Main game) {
        this.game = game;
        float height = (float)Gdx.graphics.getHeight();
        float width = (float)Gdx.graphics.getWidth();
        this.camera = new OrthographicCamera(width, height);
        this.camera.setToOrtho(false);
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 1.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        if (Gdx.input.isTouched()) {
            this.game.setScreen(new MyGame());
            this.dispose();
        }

    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        this.dispose();
    }

    public void dispose() {
    }
}