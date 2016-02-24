package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by abdullah on 2/5/16.
 */
public class GameOverScreen extends InputAdapter implements Screen {
    DotsGame game;
    StretchViewport viewport;

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton rstBtn;
    private TextButton mnuBtn;
    private Label currentScore;
    private Label highScore;
    private int score;
    private int topScore;
    private Constants.Difficulty difficulty;
    ShapeRenderer renderer;

    public GameOverScreen(DotsGame game, int score, int topScore, Constants.Difficulty difficulty) {
        this.game = game;
        this.difficulty = difficulty;
        this.score = score;
        this.topScore = topScore;
        renderer = new ShapeRenderer();
        viewport = new StretchViewport(Constants.menuwidth, Constants.menuHeight);
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.stage = new Stage(viewport);
        this.table = new Table();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        init();
    }

    private void init() {
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.center);
        table.setPosition(0, Constants.menuHeight/2);
        rstBtn = new TextButton("Restart", skin);
        rstBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showGameScreen(difficulty);
            }
        });
        mnuBtn = new TextButton("Main Menu", skin);
        mnuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showMainMenu();
            }
        });
        currentScore = new Label("Connected Dots: " + String.valueOf(score), skin);
        highScore = new Label("High Score: " + String.valueOf(topScore), skin);
        table.defaults().padBottom(25);
        table.add(currentScore).row();
        table.add(highScore).row();
        table.add(rstBtn).row();
        table.add(mnuBtn);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Constants.setBG();
        stage.act(delta);
        stage.draw();
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
        renderer.dispose();
        stage.dispose();
    }
}
