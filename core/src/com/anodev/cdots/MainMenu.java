package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
/**
 * Created by abdullah on 1/30/16.
 */
public class MainMenu extends InputAdapter implements Screen {
    DotsGame game;
    StretchViewport viewport;

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton playBtn;
    private TextButton ldrBtn;
    private TextButton crdtBtn;
    float unit_scale;

    public MainMenu(DotsGame game) {

        this.game = game;
        viewport = new StretchViewport(Constants.menuwidth, Constants.menuHeight);

        viewport.apply();
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.stage = new Stage(viewport);

        table = new Table();
        unit_scale = Gdx.graphics.getWidth() / 540;
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        table.setWidth(stage.getWidth());

        table.align(Align.center | Align.center);
        table.setPosition(0,Constants.menuHeight/2);

        playBtn = new TextButton("Play",skin,"default");
        playBtn.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showDifficultyScreen();
            }
        });
        ldrBtn = new TextButton("Leaderboards",skin);
        ldrBtn.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        crdtBtn = new TextButton("Credits",skin);
        crdtBtn.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        table.add(playBtn).padBottom(50);
        table.row();
        table.add(ldrBtn).padBottom(50);
        table.row();
        table.add(crdtBtn);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Constants.setBG();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
        stage.dispose();
    }
}
