package com.mygdx.enigma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {

    static BitmapFont text;

    public static void printMessage(SpriteBatch batch, String points, float height, float width){
        //установим цвет шрифта
        text = new BitmapFont();
        text.setColor(Color.RED);
        //масштабируем размер шрифта в соответствии с шириной экрана
        text.getData().setScale(width/800f);
        GlyphLayout glyphLayout = new GlyphLayout(); //используем класс GlyphLayout
        glyphLayout.setText(text, points);
        text.draw(batch, glyphLayout, height/2, width/2);
    }

}
