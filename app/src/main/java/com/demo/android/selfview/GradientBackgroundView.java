package com.demo.android.selfview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/3/19.
 * set the gradient background by yourself.
 * only gradient animation provided.
 */

public class GradientBackgroundView extends View {
    private float width, height;
    private Paint paint;
    private float initFillWidth;
    private float currentWidth;
    private int mMaskColor;
    private ValueAnimator valueAnimator;
    private Paint backgroundPaint;
    private Shader shader;
    public GradientBackgroundView(Context context) {
        this(context, null);
    }

    public GradientBackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientBackgroundView);
        mMaskColor = typedArray.getColor(R.styleable.GradientBackgroundView_mask_color, Color.parseColor("#ffffff"));
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setColor(mMaskColor);
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initFillWidth = 0;
        currentWidth = initFillWidth;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(shader == null){
            shader = new LinearGradient(0, 0, width, height, Color.parseColor("#514d57"), Color.parseColor("#ff7a3f"), Shader.TileMode.CLAMP);
            backgroundPaint.setShader(shader);
        }
        RectF backgroundRect = new RectF(0, 0, width, height);
        canvas.drawRect(backgroundRect, backgroundPaint);
        RectF rect = new RectF(currentWidth, 0, width, height);
        canvas.drawRect(rect, paint);
    }

    public void startGradientAnimation() {
        valueAnimator = ValueAnimator.ofFloat(initFillWidth, width);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentWidth = (float) animation.getAnimatedValue();
                System.out.println("current width is " + currentWidth + ",init width is " + initFillWidth + ",width is " + width);
                invalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.start();
    }

    public void stopGradientAnimation(){
        valueAnimator.cancel();
        currentWidth = 0;
        invalidate();
    }
}
