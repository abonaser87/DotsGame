package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by abdullah on 1/30/16.
 */
public class MainMenu extends InputAdapter implements Screen {
    DotsGame game;
    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;

    public MainMenu(DotsGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(viewport.getCamera().combined);


        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, "PLAY");
        font.setColor(Color.BLACK);
        font.draw(batch, "PLAY", Constants.screenWidth / 2, Constants.screenHeight / 3 + Constants.yStep * 2 + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, "LEADERBOARDS");
        font.draw(batch, "LEADERBOARDS", Constants.screenWidth / 2, Constants.screenHeight / 3 + Constants.yStep + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, "CREDITS");
        font.draw(batch, "CREDITS", Constants.screenWidth / 2, Constants.screenHeight / 3 + hardLayout.height / 2, 0, Align.center, false);

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

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }
}
