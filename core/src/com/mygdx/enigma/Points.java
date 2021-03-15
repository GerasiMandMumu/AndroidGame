package com.mygdx.enigma;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Points {

    static ArrayList<Integer> BitExtraction(int num){
        ArrayList<Integer> ranksArray = new ArrayList<>();
        if(num > 9999){
            ranksArray.add(9);
            ranksArray.add(9);
            ranksArray.add(9);
            ranksArray.add(9);
        }
        else {
            int thousands = num / 1000;
            ranksArray.add(thousands);
            int hundreds = num / 100 % 10;
            ranksArray.add(hundreds);
            int dozens = num % 100 / 10;
            ranksArray.add(dozens);
            int units = num % 10;
            ranksArray.add(units);
        }
        return ranksArray;
    }

    static void ScoreCreation(List<Sprite> spriteList, ArrayList<Sprite> sprites, int category){
        switch (category){
            case 0:
                sprites.add(spriteList.get(0));
                break;
            case 1:
                sprites.add(spriteList.get(1));
                break;
            case 2:
                sprites.add(spriteList.get(2));
                break;
            case 3:
                sprites.add(spriteList.get(3));
                break;
            case 4:
                sprites.add(spriteList.get(4));
                break;
            case 5:
                sprites.add(spriteList.get(5));
                break;
            case 6:
                sprites.add(spriteList.get(6));
                break;
            case 7:
                sprites.add(spriteList.get(7));
                break;
            case 8:
                sprites.add(spriteList.get(8));
                break;
            case 9:
                sprites.add(spriteList.get(9));
                break;
        }
    }
}