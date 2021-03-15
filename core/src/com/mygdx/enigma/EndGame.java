package com.mygdx.enigma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class EndGame implements Screen {

    private SpriteBatch batch; // объект для отрисовки спрайтов нашей игры
    private OrthographicCamera camera; // область просмотра нашей игры
    private Texture endTexture, backgroundTexture;
    private Sprite endSprite, backgroundSprite;
    private static float SCALE = 800f; // задаём относительный размер
    private Vector3 temp = new Vector3();
    private int points;

    public EndGame(int points){
        //this.game = game;
        // получаем размеры экрана устройства пользователя и записываем их в переменнные высоты и ширины
        float height= Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        this.points = points;
        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        endTexture = new Texture(Gdx.files.internal("end.png"));
        endSprite = new Sprite(endTexture);
        backgroundTexture = new Texture(Gdx.files.internal("backEnd.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        endSprite.setSize(endSprite.getWidth()*(width/SCALE), endSprite.getHeight()*(width/SCALE));
        endSprite.setPosition(width/2 - endSprite.getHeight(), height/2);
        backgroundSprite.setSize(width, height);
    }

    private void handleTouch() {
        // Проверяем были ли касание по экрану?
        if(Gdx.input.justTouched()) {
            // Получаем координаты касания и устанавливаем эти значения в временный вектор
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            // получаем координаты касания относительно области просмотра нашей камеры
            camera.unproject(temp);
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // устанавливаем в экземпляр spritebatch вид с камеры (области просмотра)
        batch.setProjectionMatrix(camera.combined);
        //отрисовка игровых объектов
        batch.begin();
        backgroundSprite.draw(batch);
        endSprite.draw(batch);
        handleTouch();
        Text.printMessage(batch, String.valueOf(points), Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
