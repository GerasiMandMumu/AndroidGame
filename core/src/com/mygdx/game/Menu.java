package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Menu implements Screen {
    OrthographicCamera camera;
    Main game;

    public Menu(Main game) {
        this.game = game;
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false);
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 1.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(16384);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        if (Gdx.input.isTouched()) {
            game.setScreen(new MyGame());
            dispose();
        }

    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        dispose();
    }

    public void dispose() {
    }
}