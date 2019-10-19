package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    SpriteBatch batch;
    BitmapFont font;

    public Main() {
    }

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        setScreen(new Menu(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
