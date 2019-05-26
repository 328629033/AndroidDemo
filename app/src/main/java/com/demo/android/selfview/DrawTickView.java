package com.demo.android.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by herr.wang on 2017/12/15.
 */

public class DrawTickView extends View {
    private float mRadius, mInnerRadius;
    private float drawAngle;
    Paint mPaint;
    RectF mRectF;
    private float[] mTickPoints;
    private float[] mErrorPoints;

    public DrawTickView(Context context) {
        this(context, null);
    }
    public DrawTickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public DrawTickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#55cf4a"));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mRadius = 80.0f;
        mInnerRadius = (float) Math.sqrt(mRadius);
        mTickPoints = new float[8];
        mErrorPoints = new float[4];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int centerX = w / 2;
        int centerY = h / 2;
        mRectF = new RectF( centerX - mRadius, centerY - mRadius, centerX + mRadius, centerY + mRadius);
        mTickPoints[0] = centerX - mRadius;
        mTickPoints[1] = centerY;
        mTickPoints[2] = centerX - mRadius / 4;
        mTickPoints[3] = centerY + mRadius / 4;
        mTickPoints[4] = mTickPoints[2];
        mTickPoints[5] = mTickPoints[3];
        mTickPoints[6] = centerX + mRadius;
        mTickPoints[7] = centerY + mRadius - mRadius / 4;

        mErrorPoints[0] = centerX - mInnerRadius;
        mErrorPoints[1] = centerY - mInnerRadius;
        mErrorPoints[2] = centerX + mInnerRadius;
        mErrorPoints[3] = centerY + mInnerRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mRectF, 0, drawAngle, false, mPaint);
        if(drawAngle < 360){
            drawAngle += 6;
            postInvalidateDelayed(20);
        }else{
            canvas.drawLines(mTickPoints, mPaint);
        }

    }
}
