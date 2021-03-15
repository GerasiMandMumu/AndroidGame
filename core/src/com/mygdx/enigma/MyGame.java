package com.mygdx.enigma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyGame implements Screen {

	private final Main game;
	private Music music;
	private Sound clashSound;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private float height, width;
	private Texture ballImage, gameScreen, soundOnTexture, soundOffTexture, vibroOnTexture, vibroOffTexture, exitGameTexture,
            pointTexture0, pointTexture1, pointTexture2, pointTexture3, pointTexture4,
            pointTexture5, pointTexture6, pointTexture7, pointTexture8, pointTexture9;

	private Sprite gameScreenSprite, soundOnSprite, soundOffSprite, vibroOnSprite, vibroOffSprite, ballSprite, exitGameSprite,
		   pointSprite0, pointSprite1, pointSprite2, pointSprite3, pointSprite4,
           pointSprite5, pointSprite6, pointSprite7, pointSprite8, pointSprite9,
            pacmanOnSprite, pacmanOffSprite;

	private final int index0 = 0, index1 = 1, index2 = 2, index3 = 3, rotateAngle = 5;
	private ArrayList<Integer> ranks = new ArrayList<>();
    private int score = 0, vibroFlag = 0, soundFlag = 0;
    // задаём относительный размер
	private static float BUTTON_RESIZE = 810f;
	// временный вектор для "захвата" входных координат
	private Vector3 temp = new Vector3();
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private List<Sprite> spriteList;
	private ArrayList<Integer> PointsSide;
	private ArrayList<String> nameList;
	private ArrayList<Sprite> pillowList = new ArrayList<>();

	private boolean pacmanFlag = false;

	MyGame(final Main game, ArrayList<String> nameList, ArrayList<Integer> PointsSide) {

		this.game = game;
		//Определятся размеры устройства
		this.height= Gdx.graphics.getHeight();
		this.width = Gdx.graphics.getWidth();

		this.nameList = nameList;
		this.PointsSide = PointsSide;

        batch = new SpriteBatch();

		//ЗВУКИ
		//звук удара
		clashSound = Gdx.audio.newSound(Gdx.files.internal("clash.mp3"));
		//музыка
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

		//ТЕКСТУРЫ
		//изображение шара
		ballImage = new Texture(Gdx.files.internal("ball.png"));
		//экран в игре
		gameScreen = new Texture(Gdx.files.internal("earth.jpg"));
		//кнопка включенного звука
		soundOnTexture = new Texture(Gdx.files.internal("sound_label_on.png"));
		//кнопка выключенного звука
		soundOffTexture = new Texture(Gdx.files.internal("sound_label_off.png"));
		//кнопка включенной вибрации
		vibroOnTexture = new Texture(Gdx.files.internal("vibro_on.png"));
		//кнопка выключенной вибрации
		vibroOffTexture = new Texture(Gdx.files.internal("vibro_off.png"));
		//кнопка выхода в самой игре
		exitGameTexture = new Texture(Gdx.files.internal("exit_game.png"));

		//очки
        pointTexture0 = new Texture(Gdx.files.internal("point0.png"));
        pointTexture1 = new Texture(Gdx.files.internal("point1.png"));
        pointTexture2 = new Texture(Gdx.files.internal("point2.png"));
        pointTexture3 = new Texture(Gdx.files.internal("point3.png"));
        pointTexture4 = new Texture(Gdx.files.internal("point4.png"));
        pointTexture5 = new Texture(Gdx.files.internal("point5.png"));
        pointTexture6 = new Texture(Gdx.files.internal("point6.png"));
        pointTexture7 = new Texture(Gdx.files.internal("point7.png"));
        pointTexture8 = new Texture(Gdx.files.internal("point8.png"));
        pointTexture9 = new Texture(Gdx.files.internal("point9.png"));

        //пакмен
        pacmanOnSprite = new Sprite(new Texture("pacmanOn.png"));
        pacmanOffSprite = new Sprite(new Texture("pacmanOff.png"));

		//СПРАЙТЫ
		ballSprite = new Sprite(ballImage);
		gameScreenSprite = new Sprite(gameScreen);
		soundOnSprite = new Sprite(soundOnTexture);
		soundOffSprite = new Sprite(soundOffTexture);
		vibroOnSprite = new Sprite(vibroOnTexture);
		vibroOffSprite = new Sprite(vibroOffTexture);
		exitGameSprite = new Sprite(exitGameTexture);

		//очки
        pointSprite0 = new Sprite(pointTexture0);
        pointSprite1 = new Sprite(pointTexture1);
        pointSprite2 = new Sprite(pointTexture2);
        pointSprite3 = new Sprite(pointTexture3);
        pointSprite4 = new Sprite(pointTexture4);
        pointSprite5 = new Sprite(pointTexture5);
        pointSprite6 = new Sprite(pointTexture6);
        pointSprite7 = new Sprite(pointTexture7);
        pointSprite8 = new Sprite(pointTexture8);
        pointSprite9 = new Sprite(pointTexture9);

        spriteList = Arrays.asList(pointSprite0, pointSprite1, pointSprite2, pointSprite3, pointSprite4, pointSprite5,
				pointSprite6, pointSprite7, pointSprite8, pointSprite9);

		//создается камера и SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);

		//размер заднего фона
		gameScreenSprite.setSize(width,height);

		//размер шариков
		pointSprite0.setSize(pointSprite0.getWidth()*(width/BUTTON_RESIZE), pointSprite0.getHeight()*(width/BUTTON_RESIZE));
        pointSprite1.setSize(pointSprite1.getWidth()*(width/BUTTON_RESIZE), pointSprite1.getHeight()*(width/BUTTON_RESIZE));
        pointSprite2.setSize(pointSprite2.getWidth()*(width/BUTTON_RESIZE), pointSprite2.getHeight()*(width/BUTTON_RESIZE));
        pointSprite3.setSize(pointSprite3.getWidth()*(width/BUTTON_RESIZE), pointSprite3.getHeight()*(width/BUTTON_RESIZE));
        pointSprite4.setSize(pointSprite4.getWidth()*(width/BUTTON_RESIZE), pointSprite4.getHeight()*(width/BUTTON_RESIZE));
        pointSprite5.setSize(pointSprite5.getWidth()*(width/BUTTON_RESIZE), pointSprite5.getHeight()*(width/BUTTON_RESIZE));
        pointSprite6.setSize(pointSprite6.getWidth()*(width/BUTTON_RESIZE), pointSprite6.getHeight()*(width/BUTTON_RESIZE));
        pointSprite7.setSize(pointSprite7.getWidth()*(width/BUTTON_RESIZE), pointSprite7.getHeight()*(width/BUTTON_RESIZE));
        pointSprite8.setSize(pointSprite8.getWidth()*(width/BUTTON_RESIZE), pointSprite8.getHeight()*(width/BUTTON_RESIZE));
        pointSprite9.setSize(pointSprite9.getWidth()*(width/BUTTON_RESIZE), pointSprite9.getHeight()*(width/BUTTON_RESIZE));

        //размер пакмена
        pacmanOnSprite.setSize(pacmanOnSprite.getWidth()*(width/BUTTON_RESIZE), pacmanOnSprite.getHeight()*(width/BUTTON_RESIZE));
        pacmanOffSprite.setSize(pacmanOffSprite.getWidth()*(width/BUTTON_RESIZE), pacmanOffSprite.getHeight()*(width/BUTTON_RESIZE));

        //размер кнопок
        soundOnSprite.setSize(soundOnSprite.getWidth()*(width/BUTTON_RESIZE), soundOnSprite.getHeight()*(width/BUTTON_RESIZE));
		vibroOnSprite.setSize(vibroOnSprite.getWidth()*(width/BUTTON_RESIZE), vibroOnSprite.getHeight()*(width/BUTTON_RESIZE));
		exitGameSprite.setSize(exitGameSprite.getWidth()*(width/BUTTON_RESIZE), exitGameSprite.getHeight()*(width/BUTTON_RESIZE));
		soundOffSprite.setSize(soundOffSprite.getWidth()*(width/BUTTON_RESIZE), soundOffSprite.getHeight()*(width/BUTTON_RESIZE));
		vibroOffSprite.setSize(vibroOffSprite.getWidth()*(width/BUTTON_RESIZE), vibroOffSprite.getHeight()*(width/BUTTON_RESIZE));

		//размер мяча
		ballSprite.setSize(ballSprite.getWidth() * (width/BUTTON_RESIZE), ballSprite.getHeight()*(width/BUTTON_RESIZE));

		//ПРЕПЯТСТВИЯ
		for (String name:nameList) {
			pillowList.add(new Sprite(new Texture(Gdx.files.internal(name))));
		}
		//размер препятствия
		for (Sprite i:pillowList) {
			i.setSize(i.getWidth() * (width/BUTTON_RESIZE), i.getHeight()*(width/BUTTON_RESIZE));
		}
		float xPillow0 = vibroOnSprite.getWidth() + ballSprite.getWidth(), yPillow0 = height/2,
				xPillow1 = 6*pillowList.get(1).getWidth(), yPillow1 = height/2 + ballSprite.getWidth(),
				xPillow2 = width/2, yPillow2 = pillowList.get(2).getHeight()/2 - ballSprite.getWidth();
		//позиция препятствия
		pillowList.get(0).setPosition(xPillow0, yPillow0);
		pillowList.get(1).setPosition(xPillow1, yPillow1);
		pillowList.get(2).setPosition(xPillow2, yPillow2);

		//РАЗМЕЩЕНИЕ ОБЪЕКТОВ
		float xBall = width/2, yBall = height/2, xButton = 0,
				yButtonSound = 0, yButtonVibro = (height - vibroOnSprite.getWidth())/2, yButtonExit = height - exitGameSprite.getWidth(),
        xPacman = 5 * ballSprite.getHeight(), yPacman = 5 * ballSprite.getHeight();

		//позиции объектов
		ballSprite.setPosition(xBall, yBall);
		soundOnSprite.setPosition(xButton,yButtonSound);
		vibroOnSprite.setPosition(xButton, yButtonVibro);
		exitGameSprite.setPosition(xButton, yButtonExit);
		soundOffSprite.setPosition(xButton, yButtonSound);
		vibroOffSprite.setPosition(xButton, yButtonVibro);

		//пакмен
		pacmanOnSprite.setPosition(xPacman, yPacman);
		pacmanOffSprite.setPosition(xPacman, yPacman);

        //Установка параметров звука
		music.setVolume(1);
		music.setLooping(true);
		music.play();
	}

	@Override
	public void show() {
		if(soundFlag % 2 == 0){	music.play();}
        else{music.pause();}
	}

	@Override
	public void render(float f) {

		//сообщает камере, что нужно обновить матрицы
		camera.update();
		// сообщаем SpriteBatch о системе координат визуализации указанной для камеры.
		batch.setProjectionMatrix(camera.combined);

		//Рисование объектов
		batch.begin();
		gameScreenSprite.draw(batch);
		if(soundFlag % 2 == 0){ soundOnSprite.draw(batch); }
		else{soundOffSprite.draw(batch);}
		if(vibroFlag % 2 == 0){ vibroOnSprite.draw(batch); }
		else{vibroOffSprite.draw(batch);}
		exitGameSprite.draw(batch);
		for (Sprite i:pillowList) {
			i.draw(batch);
		}
		ballSprite.setOriginCenter();
		ballSprite.rotate(rotateAngle);
		ballSprite.draw(batch);

		if (!pacmanFlag) {
			pacmanOffSprite.draw(batch);
			pacmanFlag = true;
		}
		else {
			pacmanOnSprite.draw(batch);
			pacmanFlag = false;
		}

		handleTouch();
		batch.end();

		//Установка очков
		SetPoints();

		//Вывод очков
		float xThousands = 3*pointSprite0.getWidth(), yThousands = height - 1.5f*ballSprite.getHeight(),
                xHundreds = 4*pointSprite0.getWidth(), yHundreds = height - 1.5f*ballSprite.getHeight(),
				xDozens = 5*pointSprite0.getWidth(), yDozens = height - 1.5f*ballSprite.getHeight(),
				xUnits = 6*pointSprite0.getWidth(), yUnits = height - 1.5f*ballSprite.getHeight();

		drawSprite(sprites.get(index0), sprites.get(index1), sprites.get(index2), sprites.get(index3), batch,
		 xThousands, yThousands, xHundreds, yHundreds, xDozens, yDozens, xUnits, yUnits);

		//Проверка границ
		BorderCheck(ballSprite);
        BorderCheck(pacmanOnSprite);
        BorderCheck(pacmanOffSprite);

		//Обход препятствий
		int j = 0;
		for (Sprite i:pillowList) {
			ObstacleAvoidance(i, ballSprite, PointsSide.get(j));
			j++;
		}

		// обработка пользовательского ввода
		KeyboardControl();

        //гироскоп
		GyroscopeControl();

		//движение монстра
        PacmanMove();

		if(score >= 9){ game.setScreen(new EndGame(score)); }
	}

	private void SetPoints(){
        //Вывод очков
        if(score <= 0) {
            score = 0;
            sprites.add(pointSprite0);
            sprites.add(pointSprite0);
            sprites.add(pointSprite0);
            sprites.add(pointSprite0);
        }
        else{
            //извлечение значений очков, если не 0000
            ranks = Points.BitExtraction(score);
            int thousands = ranks.get(index0), hundreds = ranks.get(index1), dozens = ranks.get(index2), units = ranks.get(index3);
            sprites = new ArrayList<>();
            Points.ScoreCreation(spriteList, sprites, thousands);
            Points.ScoreCreation(spriteList, sprites, hundreds);
            Points.ScoreCreation(spriteList, sprites, dozens);
            Points.ScoreCreation(spriteList, sprites, units);
        }
    }

	private void KeyboardControl(){
		int speed = 10;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) ballSprite.setX(ballSprite.getX() - speed);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ballSprite.setX(ballSprite.getX() + speed);
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) ballSprite.setY(ballSprite.getY() - speed);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) ballSprite.setY(ballSprite.getY() + speed);
	}

	private void GyroscopeControl(){
		boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
		if(gyroscopeAvail) {
			float speedX = 6, speedY = 6;
			float gyroX = Gdx.input.getGyroscopeX();
			float gyroY = Gdx.input.getGyroscopeY();
			float x = (float)((int)(gyroX * 1000) / 1000.0);
			float y = (float)((int)(gyroY * 1000) / 1000.0);
			if (x != 0 && y != 0) {
				if (x < 0) {
					ballSprite.setX(ballSprite.getX() - speedX);
				} else {
					ballSprite.setX(ballSprite.getX() + speedX);
				}
				if (y < 0) {
					ballSprite.setY(ballSprite.getY() - speedY);
				} else {
					ballSprite.setY(ballSprite.getY() + speedY);
				}
			}
		}
	}

	private void PacmanMove(){
	    float speed = 3;
	    //координата X
        if (pacmanOnSprite.getX() <= ballSprite.getX()){
            pacmanOnSprite.setX(pacmanOnSprite.getX() + speed);
			pacmanOffSprite.setX(pacmanOffSprite.getX() + speed);
        }
        else{
            pacmanOnSprite.setX(pacmanOnSprite.getX() - speed);
			pacmanOffSprite.setX(pacmanOffSprite.getX() - speed);
        }

        //координата Y
        if (pacmanOnSprite.getY() <= ballSprite.getY()){
            pacmanOnSprite.setY(pacmanOnSprite.getY() + speed);
			pacmanOffSprite.setY(pacmanOffSprite.getY() + speed);
        }
        else{
            pacmanOnSprite.setY(pacmanOnSprite.getY() - speed);
			pacmanOffSprite.setY(pacmanOffSprite.getY() - speed);
        }
    }

	private void ObstacleAvoidance(Sprite pillowSprite, Sprite ball, int flag){
		float coord = ballSprite.getHeight()/2;
		//Обход препятствий
		//1
		if((ball.getX() + ball.getWidth() > pillowSprite.getX())
				&& (ball.getX() < pillowSprite.getX() + ball.getHeight())
				&& (pillowSprite.getY() < ball.getY() + ball.getHeight())
				&& (ball.getY() < pillowSprite.getY() + pillowSprite.getHeight())){
			ball.setX(ball.getX() - coord);
			if(flag == 1){score+=2;}
		}
		//4
		if((ball.getX() < pillowSprite.getX() + pillowSprite.getWidth())
				&& (ball.getX() > pillowSprite.getX() + ball.getHeight())
				&& (pillowSprite.getY() < ball.getY() + ball.getHeight())
				&& (ball.getY() < pillowSprite.getY() + pillowSprite.getHeight())){
			ball.setX(ball.getX() + coord);
			if(flag == 4){ score+=3; }
		}
		//2
		if((ball.getY() + ball.getWidth() > pillowSprite.getY())
				&& (ball.getY() < pillowSprite.getY() + ball.getHeight())
				&& (pillowSprite.getX() < ball.getX() + ball.getHeight())
				&& (ball.getX() < pillowSprite.getX() + pillowSprite.getWidth())){
			ball.setY(ball.getY() - coord);
			if(flag == 2){ score+=5; }
		}
		//3
		if((ball.getY() < pillowSprite.getY() + pillowSprite.getHeight())
				&& (ball.getY() > pillowSprite.getY() + ball.getHeight())
				&& (pillowSprite.getX() < ball.getX() + ball.getHeight())
				&& (ball.getX() < pillowSprite.getX() + pillowSprite.getWidth())){
			ball.setY(ball.getY() + coord);
			if(flag == 3){ score+=10; }
		}
    }

	private void drawSprite(Sprite sprite0, Sprite sprite1, Sprite sprite2, Sprite sprite3, SpriteBatch batch,
							float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3){
		batch.begin();

		sprite0.setPosition(x0, y0);
		sprite0.draw(batch);

		sprite1.setPosition(x1, y1);
		sprite1.draw(batch);

		sprite2.setPosition(x2, y2);
		sprite2.draw(batch);

        sprite3.setPosition(x3, y3);
        sprite3.draw(batch);

		batch.end();
	}

	private void handleTouch(){
		// Проверяем касания по экрану
		if(Gdx.input.justTouched()) {
			// Получаем координаты касания и устанавливаем эти значения в временный вектор
			temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			// получаем координаты касания относительно области просмотра нашей камеры
			camera.unproject(temp);
			float touchX = temp.x;
			float touchY= temp.y;
			// обработка касания по кнопке SOUND
			if((touchX >= soundOnSprite.getX()) && touchX <= (soundOnSprite.getX() + soundOnSprite.getWidth()) && (touchY >= soundOnSprite.getY()) && touchY <= (soundOnSprite.getY() + soundOnSprite.getHeight()) ){
				soundFlag++;
				if(soundFlag % 2 == 1){music.pause();}
				else{music.play();}
			}
			// обработка касания по кнопке VIBRO
			else if((touchX >= vibroOnSprite.getX()) && touchX <= (vibroOnSprite.getX() + vibroOnSprite.getWidth()) && (touchY >= vibroOnSprite.getY()) && touchY <= (vibroOnSprite.getY() + vibroOnSprite.getHeight()) ){
				vibroFlag++;
			}
			else if((touchX >= exitGameSprite.getX()) && touchX <= (exitGameSprite.getX() + exitGameSprite.getWidth()) && (touchY >= exitGameSprite.getY()) && touchY <= (exitGameSprite.getY() + exitGameSprite.getHeight())){
				Gdx.app.exit();
			}
		}
	}

	private void BorderCheck(Sprite image){
		int vibroTime = 30;
		float board = image.getHeight(), jump = image.getHeight()/2;
		// шар остается в пределах экрана
		if(image.getX() < 0) {
			if(vibroFlag % 2 == 0) { Gdx.input.vibrate(vibroTime); }
			image.setX(0);
			image.setX(image.getX() + jump);
			if(soundFlag % 2 == 0) {clashSound.play();}
			score--;
		}
		if(image.getX() > width - board) {
			if(vibroFlag % 2 == 0) { Gdx.input.vibrate(vibroTime); }
			image.setX(width - board);
			image.setX(image.getX() - jump);
			if(soundFlag % 2 == 0) {clashSound.play();}
            score--;
		}
		if(image.getY() < 0) {
			if(vibroFlag % 2 == 0) { Gdx.input.vibrate(vibroTime); }
			image.setY(0);
			image.setY(image.getY() + jump);
			if(soundFlag % 2 == 0) { clashSound.play(); }
            score--;
		}
		if(image.getY() > height - board) {
			if(vibroFlag % 2 == 0) { Gdx.input.vibrate(vibroTime); }
			image.setY(height - board);
			image.setY(image.getY() - jump);
			if(soundFlag % 2 == 0) { clashSound.play(); }
            score--;
		}
	}

	@Override
	public void dispose() {
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
}