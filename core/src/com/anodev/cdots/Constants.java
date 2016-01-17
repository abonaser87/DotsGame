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
    public static final float MAX_SPEED = 500.0f;

    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();

    public static final float yStep = screenHeight / 7.0f;

    public static final float THICKNESS = 1.0f / 40 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final String EASY_LABEL = "EASY";
    public static final String MEDIUM_LABEL = "MEDIUM";
    public static final String HARD_LABEL = "HARD";

    public static final float EASY_COLOUMNS = 5;
    public static final float MEDIUM_COLOUMNS = 15;
    public static final float HARD_COLOUMNS = 25;

    // TODO: Add constants for the color of each difficulty select circle
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

    // TODO: Add constant for the size of the difficulty world
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;

    // TODO: Add constant for the radius of the difficulty select "buttons"
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);


    public enum Difficulty {
        EASY(3, "EASY"),
        MEDIUM(4, "MEDIUM"),
        HARD(5, "HARD");

        int coloumns;
        String label;

        Difficulty(int coloumns, String label) {
            this.coloumns = coloumns;
            this.label = label;
        }
    }
}
