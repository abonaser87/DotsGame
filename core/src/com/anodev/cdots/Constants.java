package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by abdullah on 1/8/16.
 */
public class Constants {
    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();
    public static final int menuHeight = 960;
    public static final int menuwidth = 540;
    public static final int rows = 9;
    public static final float radius = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final float SPEED = 10.0f;
    public static final int SEGMENTS = 500;
    public static final float MAX_SPEED = 0.25f * screenHeight;

    public static final float yStep = screenHeight / 7.0f;

    public static final float THICKNESS = 1.0f / 40 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final String EASY_LABEL = "EASY";
    public static final String MEDIUM_LABEL = "MEDIUM";
    public static final String HARD_LABEL = "HARD";

    public static final int EASY_COLOUMNS = 3;
    public static final int MEDIUM_COLOUMNS = 4;
    public static final int HARD_COLOUMNS = 5;

    public static final float EASY_SPEED = 0.25f * screenHeight;
    public static final float MEDIUM_SPEED = 0.3f * screenHeight;
    public static final float HARD_SPEED = 0.35f * screenHeight;

    public static final int EASY_CHANGE = 10;
    public static final int MEDIUM_CHANGE = 7;
    public static final int HARD_CHANGE = 5;

    public static final float DIFFICULTY_BUBBLE_RADIUS = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

    public static final float DIFFICULTY_LABEL_SCALE = 1f;

    public static final Vector2 EASY_CENTER = new Vector2(screenWidth / 4, screenHeight / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(screenWidth / 2, screenHeight / 2);
    public static final Vector2 HARD_CENTER = new Vector2(screenWidth * 3 / 4, screenHeight / 2);
    public static final Vector2 MAIN_MENU_CENTER = new Vector2(screenWidth / 2, screenHeight / 3);

    public static void setBG() {
        Gdx.gl.glClearColor(245.0f / 255.0f, 233.0f / 255.0f, 208.0f / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public enum Difficulty {
        EASY(EASY_COLOUMNS, EASY_LABEL, EASY_SPEED, EASY_CHANGE),
        MEDIUM(MEDIUM_COLOUMNS, MEDIUM_LABEL, MEDIUM_SPEED, MEDIUM_CHANGE),
        HARD(HARD_COLOUMNS, HARD_LABEL, HARD_SPEED, HARD_CHANGE);

        int coloumns;
        float speed;
        int colorChange;
        String label;

        Difficulty(int coloumns, String label, float speed, int colorChange) {
            this.coloumns = coloumns;
            this.label = label;
            this.speed = speed;
            this.colorChange = colorChange;
        }
    }
}
