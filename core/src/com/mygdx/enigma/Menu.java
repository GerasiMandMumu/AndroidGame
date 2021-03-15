package com.mygdx.enigma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;


public class Menu implements Screen {

    private SpriteBatch batch; // объект для отрисовки спрайтов нашей игры
    private OrthographicCamera camera; // область просмотра нашей игры
    private Texture startButtonTexture, exitButtonTexture, backGroundTexture;
    private Sprite startButtonSprite, exitButtonSprite, backGroundSprite;

    private static float BUTTON_RESIZE = 800f; // задаём относительный размер
    private static float START_VERT_POSITION = 2.7f; // задаём позицию конпки start
    private static float EXIT_VERT_POSITION = 4.2f; // задаём позицию кнопки exit

    private ArrayList<String> nameList;
    private ArrayList<Integer> PointsSide;

    // экземпляр класса MainGame нужен для доступа к вызову метода setScreen
    private Main game;
    // временный вектор для "захвата" входных координат
    private Vector3 temp = new Vector3();

    public Menu(Main game, ArrayList<String> nameList, ArrayList<Integer> PointsSide) {
        this.game = game;
        this.nameList = nameList;
        this.PointsSide = PointsSide;

        // получаем размеры экрана устройства пользователя и записываем их в переменнные высоты и ширины
        float height= Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        //levelNames = FileNameReader.ReadLevelNames("levels.txt");
        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        // инициализируем текстуры и спрайты
        startButtonTexture = new Texture(Gdx.files.internal("start.png"));
        exitButtonTexture = new Texture(Gdx.files.internal("exit.png"));
        backGroundTexture = new Texture(Gdx.files.internal("bubble.jpg"));
        startButtonSprite = new Sprite(startButtonTexture);
        exitButtonSprite = new Sprite(exitButtonTexture);
        backGroundSprite = new Sprite(backGroundTexture);
        // устанавливаем размер и позиции
        startButtonSprite.setSize(startButtonSprite.getWidth()*(width/BUTTON_RESIZE), startButtonSprite.getHeight()*(width/BUTTON_RESIZE));
        exitButtonSprite.setSize(exitButtonSprite.getWidth()*(width/BUTTON_RESIZE), exitButtonSprite.getHeight()*(width/BUTTON_RESIZE));
        backGroundSprite.setSize(width,height);
        startButtonSprite.setPosition((width/2f-startButtonSprite.getWidth()/2), width/START_VERT_POSITION);
        exitButtonSprite.setPosition((width/2f-exitButtonSprite.getWidth()/2), width/EXIT_VERT_POSITION);
    }

    private void handleTouch() {
        // Проверяем были ли касание по экрану?
        if(Gdx.input.justTouched()) {
            // Получаем координаты касания и устанавливаем эти значения в временный вектор
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            // получаем координаты касания относительно области просмотра нашей камеры
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY= temp.y;
            // обработка касания по кнопке Start
            if((touchX>=startButtonSprite.getX()) && touchX <= (startButtonSprite.getX()+startButtonSprite.getWidth()) && (touchY>=startButtonSprite.getY()) && touchY<=(startButtonSprite.getY()+startButtonSprite.getHeight())){
                // Переход к экрану игры
                game.setScreen(new MyGame(game, nameList, PointsSide));
            }
            // обработка касания по кнопке Exit
            else if((touchX>=exitButtonSprite.getX()) && touchX <= (exitButtonSprite.getX()+exitButtonSprite.getWidth()) && (touchY>=exitButtonSprite.getY()) && touchY<=(exitButtonSprite.getY()+exitButtonSprite.getHeight())){
                // выход из приложения
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta){
        // Очищаем экран
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // устанавливаем в экземпляр spritebatch вид с камеры (области просмотра)
        batch.setProjectionMatrix(camera.combined);
        //отрисовка игровых объектов
        batch.begin();
        backGroundSprite.draw(batch);
        startButtonSprite.draw(batch);
        exitButtonSprite.draw(batch);
        handleTouch();
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
        //dispose();
    }

    @Override
    public void dispose() {
        startButtonTexture.dispose();
        exitButtonTexture.dispose();
        //batch.dispose();
    }
}