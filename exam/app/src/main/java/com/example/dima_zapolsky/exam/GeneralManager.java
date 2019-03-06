package com.example.dima_zapolsky.exam;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public final class GeneralManager {

    private volatile static GeneralManager instance = null;
    Context context;
    private static ArrayList<Theme> themes;

    private GeneralManager(Context context) {
        themes = new ArrayList<Theme>();
        this.context = context;
    }

    public static GeneralManager getInstance(Context context) {
        if (instance == null) {
            synchronized (GeneralManager.class) {
                if (instance == null) {
                    instance = new GeneralManager(context);
                }
            }
        }
        return instance;
    }


    public static void fill(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is =  assetManager.open("input.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String s = new String(buffer);
            Log.e("kek", String.valueOf(s.length()));
            ArrayList<String> str = new ArrayList<String>(Arrays.asList(s.split("\n")));

            for (int i = 1; i < str.size(); i++) {
                Theme theme = new Theme();
                String th = str.get(i);
                i++;
                while (str.get(i).charAt(0) != '1') {
                    th = th.concat(" ");
                    th = th.concat(str.get(i));
                    i++;
                }
                theme.setName(th);

                while (true) {
                    if (str.get(i) == "")
                        break;
                    Question question = new Question();

                    String name = str.get(i);
                    i++;
                    while (str.get(i).charAt(0) != ' ' && str.get(i).charAt(0) != '\t') {
                        name = name.concat(" ");
                        name = name.concat(str.get(i));
                        i++;
                    }
                    while (name.charAt(0) == ' ' || name.charAt(0) == '\t')
                        name = name.substring(1);
                    while (name.charAt(0) >= '0' && name.charAt(0) <= '9')
                        name = name.substring(1);
                    question.setName(name);

                    while (i < str.size() && !str.get(i).equals("") && !str.get(i).equals("\n")
                            && !(str.get(i).charAt(0) >= '0' && str.get(i).charAt(0) <= '9')) {
                        String ans = str.get(i);

                        while (!str.get(i).contains("(верно)") && !str.get(i).contains("(неверно)")) {
                            i++;
                            ans = ans.concat(" ");
                            ans = ans.concat(str.get(i));
                        }
                        if (ans.contains("Погрешность")) {
                            ans = ans.concat(" (верно)");
                        }
                        if (ans.contains("(верно)")) {
                            ans = ans.substring(0, ans.indexOf("(верно)"));
                            question.addRightAnswer(question.getAnswersSize());
                        }
                        else {
                            ans = ans.substring(0, ans.indexOf("(неверно)"));
                        }
                        while (ans.charAt(0) == ' ' || ans.charAt(0) == '\t')
                            ans = ans.substring(1);
                        while (ans.charAt(0) >= '0' && ans.charAt(0) <= '9') {
                            ans = ans.substring(1);
                        }
                        while (ans.charAt(0) == ' ' || ans.charAt(0) == '\t')
                            ans = ans.substring(1);
                        question.addAnswer(ans);
                        i++;
                        //Log.e("kek", Integer.toString(i));
                    }
                    theme.addQuestion(question);
                    if (i >= str.size() || str.get(i).equals("") || str.get(i).equals("\n")) {
                        break;
                    }
                }
                themes.add(theme);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
;    }

    public static ArrayList<Theme> getThemes() {
        return themes;
    }

    public static Theme getThemeByNumber(int position) {
        return themes.get(position);
    }

    public static String getThemeNameByNumber(int position) {
        return themes.get(position).getName();
    }
}
