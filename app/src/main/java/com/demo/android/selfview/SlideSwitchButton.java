package com.demo.android.selfview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.demo.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class SlideSwitchButton extends View {
    private static final int DEFAULT_BACKGROUND_COLOR = 0xff514d57;
    private static final int DEFAULT_TAB_TEXT_COLOR = 0xffffffff;
    private static final int DEFAULT_SLIDE_COLOR = 0xffff7a3f;
    private List<String> tabs;
    private int mWidth, mHeight;

    private int mBackgroundColor;
    private int mSliderColor;
    private int mTabTextColor;
    private float mTabTextSize;
    private float mCornerRadius;

    private int tabIndex;
    private int tabWidth;

    private Paint paint;
    private Paint textPaint;
    private Paint sliderPaint;

    private float xOffset;

    private OnSlideCallback onSlideCallback;


    public SlideSwitchButton(Context context) {
        this(context, null);
    }

    public SlideSwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideSwitchButton);
        mBackgroundColor = typedArray.getColor(R.styleable.SlideSwitchButton_background_color, DEFAULT_BACKGROUND_COLOR);
        mSliderColor = typedArray.getColor(R.styleable.SlideSwitchButton_slide_color, DEFAULT_SLIDE_COLOR);
        mTabTextColor = typedArray.getColor(R.styleable.SlideSwitchButton_tab_text_color, DEFAULT_TAB_TEXT_COLOR);
        mTabTextSize = typedArray.getDimension(R.styleable.SlideSwitchButton_tab_text_size, 12.0f);
        mCornerRadius = typedArray.getDimension(R.styleable.SlideSwitchButton_cornor_radius, 6.0f);
        typedArray.recycle();
        tabs = new ArrayList<>();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
    }

    /**
     * call before onDraw()
     * instant refresh not supported for now.
     * @param tabs
     */
    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
        if (this.tabs != null && this.tabs.size() > 0) {
            tabWidth = mWidth / this.tabs.size();
        }
    }

    public void setOnSlideCallback(OnSlideCallback onSlideCallback) {
        this.onSlideCallback = onSlideCallback;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    /**
     * TODO background and text will re-draw many times when scroll, is there any way to draw text on the top layer???
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw background
        paint.setColor(mBackgroundColor);
        RectF rectf = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rectf, mCornerRadius, mCornerRadius, paint);

        tabWidth = mWidth / tabs.size();

        //draw slide
        if (sliderPaint == null) {
            sliderPaint = new Paint();
            sliderPaint.setColor(mSliderColor);
            sliderPaint.setStyle(Paint.Style.FILL);
            sliderPaint.setStrokeWidth(10);
        }
        RectF slideRect = new RectF(xOffset, 0, tabWidth + xOffset, mHeight);
        canvas.drawRoundRect(slideRect, mCornerRadius, mCornerRadius, sliderPaint);

        //draw text
        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(mTabTextColor);
            textPaint.setTextSize(dp2Px(mTabTextSize));
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setTextAlign(Paint.Align.CENTER);
        }
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        int baseY = (int) (mHeight / 2 - fm.top / 2 - fm.bottom / 2);
        for (int i = 0; i < tabs.size(); i++) {
            canvas.drawText(tabs.get(i), tabWidth * i + tabWidth / 2, baseY, textPaint);
        }

    }

    float dx = 0, oldX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                dx = event.getX() - oldX;
                oldX = event.getX();
                xOffset += dx;
                if (xOffset < 0) {
                    xOffset = 0;
                }
                if (xOffset > mWidth - tabWidth) {
                    xOffset = mWidth - tabWidth;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                determineIndex(event.getX());
                break;
        }
        return true;
    }

    private void determineIndex(float finalPos) {
        int curTab = (int) (finalPos / tabWidth);
        if(tabIndex != curTab) {
            tabIndex = curTab;
            if (onSlideCallback != null) {
                onSlideCallback.onTabChanged(tabIndex);
            }
        }
        scroll2Tab();
    }

    private void scroll2Tab() {
        final float tempOffsetX = xOffset;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(tempOffsetX, tabIndex * tabWidth);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                xOffset = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.start();
    }

    private float dp2Px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnSlideCallback {
        void onTabChanged(int index);
    }

}
