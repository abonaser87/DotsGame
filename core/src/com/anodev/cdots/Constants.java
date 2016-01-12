package com.anodev.cdots;

import com.badlogic.gdx.Gdx;

/**
 * Created by abdullah on 1/8/16.
 */
public class Constants {
    public static final int coulmns = 3;
    public static final int rows = 9;
    public static final float radius = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    public static final float SPEED = 10.0f;
    public static final int SEGMENTS = 500;
    public static final float MAX_SPEED = 500.0f;

    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();
    public static final float xStep = screenWidth / coulmns;
    public static final float yStep = screenHeight / 7.0f;

    public static final float THICKNESS = 1.0f / 40 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
}
