package com.likefunnythings.androidtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.likefunnythings.androidtest.R;

/**
 * TODO: document your custom view class.
 */
public class CustomeView1 extends View {
    private String mTitleText;
    private int mTitleTextColor = Color.RED;
    private float mTitleTextSize = 0;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public CustomeView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomeView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * 初始化
     *
     * @param attrs
     * @param defStyle
     */
    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomeView1, defStyle, 0);

         /*
         * Look the appearance up without checking first if it exists because
         * almost every TextView has one and it greatly simplifies the logic
         * to be able to parse the appearance first and then let specific tags
         * for this View override it.
         */
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            //获取自定义的属性值
            switch (attr) {
                case R.styleable.CustomeView1_titleText:
                    mTitleText = a.getString(R.styleable.CustomeView1_titleText);
                    break;
                case R.styleable.CustomeView1_titleTextColor:
                    mTitleTextColor = a.getColor(R.styleable.CustomeView1_titleTextColor,mTitleTextColor);
                    break;
                case R.styleable.CustomeView1_titleTextSize:
                    mTitleTextSize = a.getDimension(R.styleable.CustomeView1_titleTextSize, mTitleTextSize);
                    break;
            }
        }

        a.recycle();

        //初始化画笔
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTitleTextSize);
        mTextPaint.setColor(mTitleTextColor);
        mTextWidth = mTextPaint.measureText(mTitleText);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        //绘制文本
        // Draw the text.
        canvas.drawText(mTitleText,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);
    }

}
