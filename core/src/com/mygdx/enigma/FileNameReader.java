package com.mygdx.enigma;

import java.io.BufferedReader;
import java.util.ArrayList;

public class FileNameReader {

    public static ArrayList<String> ReadLevelNames(String fileName) throws Exception{
        ArrayList<String> levelNames = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new java.io.FileReader(fileName));
        String s;
        while ((s = buf.readLine()) != null) {
            levelNames.add(s);
        }
        return levelNames;
    }
}
