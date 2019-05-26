package com.demo.android.selfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/10/13.
 */

public class RippleView extends View {
    private static final int STEP = 2;
    private int[] mRadiusArray;

    private int mRippleCount;
    private int mRippleOffset;
    private int mMinRadius;
    private int mMaxRadius;
    private int mColor;
    private Paint mPaint;


    private int mWith, mHeight;

    private boolean mRunning;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ripple);
        mRippleCount = typedArray.getInt(R.styleable.Ripple_count, 1);
        mMinRadius = typedArray.getDimensionPixelSize(R.styleable.Ripple_minRadius, 0);
        mMaxRadius = typedArray.getDimensionPixelSize(R.styleable.Ripple_maxRadius, 0);
        mRippleOffset = typedArray.getDimensionPixelOffset(R.styleable.Ripple_offset, 10);
        mColor = typedArray.getColor(R.styleable.Ripple_color, Color.parseColor("#ff7a3f"));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        mRadiusArray = new int[mRippleCount];
        for (int i = 0; i < mRadiusArray.length; ++i) {
            mRadiusArray[i] = mMinRadius - mRippleOffset * i;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mMaxRadius == 0) {
            mMaxRadius = w;
        }
        mWith = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRunning) {
            drawRipple(canvas);
            postInvalidateDelayed(20);

        }
    }

    private void drawRipple(Canvas canvas) {
        for (float radius : mRadiusArray) {
            if (radius < mMinRadius) {
                continue;
            }
            mPaint.setAlpha((int) (255 - divide(radius, mMaxRadius) * 255));
            canvas.drawCircle(mWith / 2, mHeight / 2, radius, mPaint);
        }

        for (int i = 0; i < mRadiusArray.length; ++i) {
            if ((mRadiusArray[i] += STEP) > mMaxRadius) {
                if(i == 0){
                    mRadiusArray[i] = mRadiusArray[mRadiusArray.length - 1] - mRippleOffset + STEP;
                }else {
                    mRadiusArray[i] = mRadiusArray[i-1] - mRippleOffset;
                }
            }
        }
    }

    private float divide(float num1, float num2) {
        return num1 / num2;
    }

    public void start() {
        mRunning = true;
        postInvalidate();
    }

    public void stop() {
        mRunning = false;
    }

}
