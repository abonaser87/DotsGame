package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by abdullah on 1/8/16.
 */
public class Constants {
    public static final int rows = 9;
    public static final float radius = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final float SPEED = 10.0f;
    public static final int SEGMENTS = 500;
    public static final float MAX_SPEED = 100.0f;

    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();

    public static final float yStep = screenHeight / 7.0f;

    public static final float THICKNESS = 1.0f / 40 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final String EASY_LABEL = "EASY";
    public static final String MEDIUM_LABEL = "MEDIUM";
    public static final String HARD_LABEL = "HARD";

    public static final int EASY_COLOUMNS = 3;
    public static final int MEDIUM_COLOUMNS = 4;
    public static final int HARD_COLOUMNS = 5;

    // TODO: Add constants for the color of each difficulty select circle
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);



    // TODO: Add constant for the radius of the difficulty select "buttons"
    public static final float DIFFICULTY_BUBBLE_RADIUS = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    public static final float DIFFICULTY_LABEL_SCALE = 1f;

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    public static final Vector2 EASY_CENTER = new Vector2(screenWidth / 4, screenHeight / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(screenWidth / 2, screenHeight / 2);
    public static final Vector2 HARD_CENTER = new Vector2(screenWidth * 3 / 4, screenHeight / 2);


    public enum Difficulty {
        EASY(EASY_COLOUMNS, EASY_LABEL),
        MEDIUM(MEDIUM_COLOUMNS, MEDIUM_LABEL),
        HARD(HARD_COLOUMNS, HARD_LABEL);

        int coloumns;
        String label;

        Difficulty(int coloumns, String label) {
            this.coloumns = coloumns;
            this.label = label;
        }
    }
}
