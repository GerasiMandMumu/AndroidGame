package com.mygdx.enigma;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
public class PillowFileReader {

    static ArrayList<String> nameList = new ArrayList<>();
    static ArrayList<Integer> PointsSide = new ArrayList<>();

    private static void AddingComponents(String[] arr){
        nameList.add(arr[0]);
        PointsSide.add(Integer.parseInt(arr[3]));
    }

    protected static void ReadLevel(String levelName) throws Exception{
        BufferedReader buf = new BufferedReader(new FileReader(levelName));
        String s;
        while ((s = buf.readLine()) != null) {
            String[] sArr = s.split(" ");
            AddingComponents(sArr);
        }
    }
}
