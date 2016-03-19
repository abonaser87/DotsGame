package com.anodev.TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by abdullah on 3/19/16.
 */
public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }

}