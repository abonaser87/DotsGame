package com.anodev.cdots;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 11/01/2016.
 */
public class TouchProcessor extends InputAdapter {
    CirclesClient circle;
    Array<CirclesClient> temp = new Array<CirclesClient>();
    FitViewport viewport;

    public TouchProcessor(CirclesClient circle, FitViewport viewport) {
        super();
        temp.add(circle);
        this.viewport = viewport;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(temp.items);
        for (CirclesClient x : temp) {

            Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
            if (worldClick.dst(x.getPosition()) < CirclesClient.radius) {
                System.out.println("Inside" + x.getPosition());
            }
        }
        return true;
    }
}
