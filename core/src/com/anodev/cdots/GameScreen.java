package com.anodev.cdots;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.anodev.TweenAccessors.Value;
import com.anodev.TweenAccessors.ValueAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 05/01/2016.
 */
public class GameScreen extends ScreenAdapter {
    Constants.Difficulty difficulty;
    ShapeRenderer renderer;
    FitViewport viewport;
    GridCircles grid;
    DotsGame game;
    private TweenManager manager;
    private Value alpha = new Value();
    private Color transitionColor;

    public GameScreen(DotsGame game, Constants.Difficulty difficulty) {
        this.game = game;
        this.difficulty = difficulty;
        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);
    }

    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }
    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        grid = new GridCircles(viewport, difficulty, game);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        AssetLoader.dispose();
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Constants.setBG();
        grid.update(delta, viewport);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);
        grid.render(renderer, delta);
        renderer.end();
        drawTransition(delta);
    }

    public void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(1, 1, 1, alpha.getValue());
            renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            renderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }
}
