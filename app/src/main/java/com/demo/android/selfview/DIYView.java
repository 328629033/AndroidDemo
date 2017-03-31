package com.demo.android.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by herr.wang on 2017/3/14.
 */

public class DIYView extends View {
    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
            | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public DIYView(Context context){
        this(context, null);
    }

    public DIYView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public DIYView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawColor(Color.WHITE);
        canvas.translate(10, 10);
        paint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, paint);
        canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, paint);
        canvas.restore();

        paint.setColor(Color.GREEN);
        canvas.drawCircle(150, 150, 75, paint);
    }
}
