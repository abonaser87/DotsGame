package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 17/01/2016.
 */
public class DifficultyScreen extends InputAdapter implements Screen {
    DotsGame game;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;

    public DifficultyScreen(DotsGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.screenWidth, Constants.screenHeight);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont(Gdx.files.internal("data/modenine.fnt"));
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Constants.setBG();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.EASY_LABEL);
        font.setColor(Color.BLACK);
        font.draw(batch, Constants.EASY_LABEL, Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.MEDIUM_LABEL);
        font.draw(batch, Constants.MEDIUM_LABEL, Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.HARD_LABEL);
        font.draw(batch, Constants.HARD_LABEL, Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO: Unproject the touch from the screen to the world
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        // TODO: Check if the touch was inside a button, and launch the icicles screen with the appropriate difficulty

        if (worldTouch.dst(Constants.EASY_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showGameScreen(Constants.Difficulty.EASY);
        }

        if (worldTouch.dst(Constants.MEDIUM_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showGameScreen(Constants.Difficulty.MEDIUM);
        }

        if (worldTouch.dst(Constants.HARD_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.showGameScreen(Constants.Difficulty.HARD);
        }

        return true;
    }
}
