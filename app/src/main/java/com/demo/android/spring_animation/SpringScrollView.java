package com.demo.android.spring_animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.demo.android.R;


/**
 * Created by herr.wang on 2017/5/26.
 * [action down] and [scroll up] is the same int the note.
 */

public class SpringScrollView extends FrameLayout {

    private static final int SCROLL_MODE_UP = 0;
    private static final int SCROLL_MODE_DOWN = 1;
    private static final int SCROLL_MODE_BOTH = 2;

    public static final byte SCROLL_STATE_IDLE = 0;
    public static final byte SCROLL_STATE_UP = 1;
    public static final byte SCROLL_STATE_DOWN = 2;

    private static final int DEFAULT_MAX_DISTANCE = -1;

    private View mTargetView;
    private View mRevealView;

    private int mScrollMode;
    private float mDampFactor;
    private int mRevealRes;
    private float mElasticFactor;
    private float mMaxSlideDistance;

    private boolean intercepting = false;
    private float mLastY;

    private int mTouchSlop;

    private ScrollCallback scrollCallback;

    public interface ScrollCallback {
        void onScrollStateChanged(byte scrollState, boolean toMax);

        void onLoadingEnd();
    }

    public SpringScrollView(Context context) {
        this(context, null);
    }

    public SpringScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SpringScrollView);
        mScrollMode = typedArray.getInteger(R.styleable.SpringScrollView_scrollMode, SCROLL_MODE_UP);
        mDampFactor = typedArray.getFloat(R.styleable.SpringScrollView_dampFactor, 0.5f);
        mRevealRes = typedArray.getResourceId(R.styleable.SpringScrollView_revealRes, 0);
        if (mRevealRes != 0) {
            mRevealView = View.inflate(context, mRevealRes, null);
            addView(mRevealView, 0);
        }
        mMaxSlideDistance = typedArray.getDimension(R.styleable.SpringScrollView_maxSlideDistance, DEFAULT_MAX_DISTANCE);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * target view must be in children.
     *
     * @param targetView
     * @return
     */
    public void setTargetView(View targetView) {
        mTargetView = targetView;
    }

    public void setScrollCallback(ScrollCallback callback) {
        scrollCallback = callback;
    }

    public View getRevealView() {
        return mRevealView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepting = false;
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY() > mLastY) {  //scroll up
                    if (!intercepting && ev.getY() - mLastY > mTouchSlop && !canTargetScrollUp()) {
                        intercepting = true;
                        mLastY = ev.getY() + mTouchSlop;
                    }
                } else if (ev.getY() < mLastY) { //scroll down
                    if (!intercepting && mLastY - ev.getY() > mTouchSlop && !canTargetScrollDown()) {
                        intercepting = true;
                        mLastY = ev.getY() + mTouchSlop;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                intercepting = false;
                break;
        }
        return intercepting;
    }


    private float offsetY;

    /**
     * it will lose efficacy if you set #OnTouchListener
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                offsetY = event.getY() - mLastY;
                if (mScrollMode == SCROLL_MODE_UP) {
                    if (offsetY > 0) {
                        slidingBy(offsetY);
                    }
                } else if (mScrollMode == SCROLL_MODE_DOWN) {
                    if (offsetY < 0) {
                        slidingBy(offsetY);
                    }
                } else if (mScrollMode == SCROLL_MODE_BOTH) {
                    slidingBy(offsetY);
                }
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (scrollCallback != null) {
                    //handle by yourself.
                    scrollCallback.onScrollStateChanged(SCROLL_STATE_IDLE, isToMax());
                } else {
                    setLoading(false);
                }
                break;
        }
        return true;
    }

    ValueAnimator downAnimator, upAnimator;
    float animTranslateY;

    public void setLoading(boolean loading) {
        if (mTargetView == null) {
            return;
        }
        if (downAnimator != null && downAnimator.isRunning()) {
            return;
        }

        if (upAnimator != null && upAnimator.isRunning()) {
            return;
        }

        if (loading) {
            loading();
        } else {
            clearLoading();
        }
    }

    private void loading() {
        if (mMaxSlideDistance == DEFAULT_MAX_DISTANCE) {
            downAnimator = ValueAnimator.ofFloat(0, 150);
        } else {
            downAnimator = ValueAnimator.ofFloat(0, mMaxSlideDistance);
        }
        downAnimator.setInterpolator(new DecelerateInterpolator());
        downAnimator.setDuration(600);
        downAnimator.setRepeatCount(0);
        downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animTranslateY = (float) animation.getAnimatedValue();
                mTargetView.setTranslationY(animTranslateY);
            }
        });
        downAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (scrollCallback != null) {
                    scrollCallback.onLoadingEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        downAnimator.start();
    }

    private void clearLoading() {
        upAnimator = ValueAnimator.ofFloat(mTargetView.getTranslationY(), 0);
        upAnimator.setRepeatCount(0);
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animTranslateY = (float) animation.getAnimatedValue();
                mTargetView.setTranslationY(animTranslateY);
            }
        });
        upAnimator.start();
    }

    private float translationY;

    private void slidingBy(float offsetY) {
        if (mTargetView != null) {
            mElasticFactor = Math.abs(1 - mTargetView.getTranslationY() / mTargetView.getMeasuredHeight());
            translationY = mTargetView.getTranslationY() + offsetY * mElasticFactor * mDampFactor;
            if (mMaxSlideDistance == DEFAULT_MAX_DISTANCE) {
                mTargetView.setTranslationY(translationY);
            } else {
                mTargetView.setTranslationY(translationY > mMaxSlideDistance ? mMaxSlideDistance : translationY);
            }
            if (scrollCallback != null) {
                if (offsetY > 0) {
                    scrollCallback.onScrollStateChanged(SCROLL_STATE_UP, isToMax());
                } else {
                    scrollCallback.onScrollStateChanged(SCROLL_STATE_DOWN, isToMax());
                }
            }
        }
    }

    private boolean isToMax() {
        return mMaxSlideDistance == DEFAULT_MAX_DISTANCE ? false : Math.abs(mTargetView.getTranslationY()) >= mMaxSlideDistance;
    }

    private boolean canTargetScrollUp() {
        if (mTargetView == null) {
            return false;
        }
        if (mTargetView instanceof AbsListView) {
            final AbsListView absListView = (AbsListView) mTargetView;
            return absListView.getChildCount() > 0
                    && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                    .getTop() < absListView.getPaddingTop());
        } else {
            return ViewCompat.canScrollVertically(mTargetView, -1) || mTargetView.getScrollY() > 0;
        }
    }

    private boolean canTargetScrollDown() {
        if (mTargetView == null) {
            return false;
        }
        if (mTargetView instanceof AbsListView) {
            final AbsListView absListView = (AbsListView) mTargetView;
            return absListView.getChildCount() > 0 && absListView.getAdapter() != null
                    && (absListView.getLastVisiblePosition() < absListView.getAdapter().getCount() - 1 || absListView.getChildAt(absListView.getChildCount() - 1)
                    .getBottom() < absListView.getPaddingBottom());
        } else {
            return ViewCompat.canScrollVertically(mTargetView, 1) || mTargetView.getScrollY() > 0;
        }
    }
}
