package com.mygdx.enigma;

import com.badlogic.gdx.Game;
import java.util.ArrayList;

public class Main extends Game {

    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Integer> PointsSide = new ArrayList<>();

    @Override
    public void create(){
        Read();
        setScreen(new Menu(this, this.nameList, this.PointsSide));
    }

    private void Read(){
        try {
            this.nameList.add("pillow0.jpg");
            this.nameList.add("pillow1.jpg");
            this.nameList.add("pillow2.jpg");
            this.PointsSide.add(4);
            this.PointsSide.add(2);
            this.PointsSide.add(3);
        }
        catch (Exception e){e.printStackTrace();}
    }

    public void render() {
        super.render();
    }

    public void dispose() {
    }
}