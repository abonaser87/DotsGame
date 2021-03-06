package com.anodev.cdots;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.anodev.TweenAccessors.Value;
import com.anodev.TweenAccessors.ValueAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by 84170 on 05/01/2016.
 */
public class GridCircles extends InputAdapter {
    private static Preferences prefs;
    int score = 0;
    int counter = 0;
    int topScore = 0;
    private Constants.Difficulty difficulty;
    private ColorPicker color;
    private DelayedRemovalArray<CirclesClient> circle;
    private FitViewport viewport;
    private CirclesClient x;
    private float newY;
    private float xStep;
    private GameState currentState;
    private BitmapFont font;
    private SpriteBatch batch;
    private DotsGame game;
    private Circles chosenColor;
    private ColorChecker checker;
    private boolean misClick = false;
    private TweenManager manager;
    private Value alpha = new Value();
    private Color transitionColor;
    private String leaderboardId;

    public GridCircles(FitViewport viewport, Constants.Difficulty difficulty, DotsGame game) {
        this.game = game;
        this.viewport = viewport;
        this.difficulty = difficulty;
        color = new ColorPicker();
        currentState = GameState.RUNNING;
        batch = new SpriteBatch();
        circle = new DelayedRemovalArray<CirclesClient>();
        Gdx.input.setInputProcessor(this);

        xStep = Constants.screenWidth / difficulty.coloumns;
        createGrid(Constants.rows, -Constants.screenHeight, new Vector2(0, -50));
        font = new BitmapFont(Gdx.files.internal("data/modenine.fnt"));
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE / 1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        chosenColor = new Circles(color.getRandColor(difficulty.coloumns, -1));
        checker = new ColorChecker();
        checker.setMainColor(chosenColor.getColor());
        prefs = Gdx.app.getPreferences("CDots");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        } else {
            topScore = prefs.getInteger("highScore");
        }
        transitionColor = new Color();

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    private void createGrid(int rows, float offset, Vector2 velocity) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < difficulty.coloumns; col++) {
                float xOffset = col * xStep;
                float yOffset = Constants.yStep * row;
                createCircle(xOffset, yOffset + offset, velocity,row);
            }
        }
    }

    private void createCircle(float xOffset, float yOffset, Vector2 velocity, int row) {
        x = new CirclesClient(xOffset, yOffset, color.getRandColor(difficulty.coloumns, row), viewport, difficulty.speed);
        x.setVelocity(velocity);
        circle.add(x);
    }

    public void update(float delta, FitViewport viewport) {
        // Make a seprate class to handel the screens and states
        switch (currentState) {

            case RUNNING:
                
                updateRunning(delta, viewport);
                break;
            case GAMEOVER:
                gameOver(delta, viewport);
                break;

        }


    }

    private void gameOver(float delta, FitViewport viewport) {
        if (topScore < score) topScore = score;
        setHighScore(topScore);
        switch (difficulty) {
            case EASY:
                leaderboardId = "CgkInJzn17IfEAIQBg";
            case MEDIUM:
                leaderboardId = "CgkInJzn17IfEAIQBw";
            case HARD:
                leaderboardId = "CgkInJzn17IfEAIQCA";
        }

        if (game.playServices.isSignedIn()) {
            if (topScore < score) game.playServices.submitScore(score, leaderboardId);
            if (topScore >= 0) game.playServices.unlockAchievement("CgkInJzn17IfEAIQAQ");
            if (topScore >= 10) game.playServices.unlockAchievement("CgkInJzn17IfEAIQAg");
            if (topScore >= 50) game.playServices.unlockAchievement("CgkInJzn17IfEAIQAw");
            if (topScore >= 100) game.playServices.unlockAchievement("CgkInJzn17IfEAIQBA");
            if (topScore >= 500) game.playServices.unlockAchievement("CgkInJzn17IfEAIQBQ");
        }
        game.setScreen(new GameOverScreen(game, score, topScore, difficulty));
        restart();
    }

    private void updateRunning(float delta, FitViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            x.update(delta);
        }
        if (checker.getLines().size > 0) {
            if (checker.getLines().get(checker.getLines().size - 1).isNotInScreen()) {
                currentState = GameState.GAMEOVER;
            }
        }
        if (circle.get(circle.size - 1).isNotInScreen()) {
            newY = circle.get(0).getPosition().y - 2 * Constants.yStep - (circle.get(circle.size - 1).getPosition().y - circle.get(0).getPosition().y);
            createGrid(Constants.rows, newY, circle.get(0).getVelocity());
        }
        circle.begin();
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            if (x.isNotInScreen()) {
                circle.removeIndex(i);
            }
        }
        circle.end();
        checker.getLines().begin();
        for (int i = 0; i < checker.getLines().size; i++) {
            LineShape x = checker.getLines().get(i);
            if (x.isNotInScreen()) {
                checker.getLines().removeIndex(i);
            }
        }
        checker.getLines().end();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        for (CirclesClient x : circle) {
            if (worldClick.dst(x.getPosition()) < Constants.radius) {
                counter += 1;
                checker.addColor(x.getColor(), x.getPosition());
                if (checker.isMatching()) {
                    score += 1;
                    AssetLoader.correct.play();
                } else {
                    if (score > 0) {
                        score -= 1;
                        transitionColor.set(x.getColor());
                        misClick = true;
                        AssetLoader.wrong.play();
                    }
                    // TODO: Screen feedback that the choice was wrong ?
                }
            }
        }
        if (counter > 0 && counter % difficulty.colorChange == 0) {
            Color temp = color.getRandColor(difficulty.coloumns, -1);
            while (temp.equals(chosenColor.getColor())) {
                temp = color.getRandColor(difficulty.coloumns, -1);
            }
            chosenColor.setColor(temp);
            checker.setMainColor(chosenColor.getColor());

        }
        return true;
    }

    private void restart() {
        circle.clear();
        checker.clearAll();
        score = 0;
        counter = 0;
    }

    public void render(ShapeRenderer renderer, float delta) {
        renderer.setAutoShapeType(true);
        for (CirclesClient x : circle) {
            x.render(renderer);
        }
        for (LineShape line : checker.getLines()) {
            line.render(renderer);
        }
        if (misClick) {
            prepareTransition(77, 91, 115, 5f);
            drawTransition(delta, renderer);
        }
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, String.valueOf(score), Constants.screenWidth - xStep / 8, Constants.screenHeight / 2 - xStep / 8, 0, Align.center, false);
        batch.end();
        chosenColor.render(xStep / 8, Constants.screenHeight / 2 - xStep / 8, Constants.radius / 4, Constants.SEGMENTS, renderer);

    }

    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(5);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuint).start(manager);
    }

    public void drawTransition(float delta, ShapeRenderer renderer) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(77.0f / 255.0f, 91.0f / 255.0f, 115.0f / 255.0f, alpha.getValue());
            renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            renderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            misClick = false;
        }
    }
    public enum GameState {
        RUNNING, GAMEOVER
    }
}
