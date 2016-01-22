package com.example.harryaung.wizard;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by Harry on 1/22/2016.
 */
public class Wizard_SurfaceView extends SurfaceView {

    private int colorCode;

    public Wizard_SurfaceView(Context context,int colorCode) {
        super(context);

        this.colorCode = colorCode;

        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(colorCode);

        invalidate();
    }
}
