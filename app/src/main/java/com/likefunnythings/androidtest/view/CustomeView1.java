package com.likefunnythings.androidtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.likefunnythings.androidtest.R;

import java.util.Random;

/**
 * 自定义View1
 */
public class CustomeView1 extends View {

    private static final String TAG = CustomeView1.class.getSimpleName();

    private String mTitleText;
    private int mTitleTextColor = Color.RED;
    private float mTitleTextSize = 0;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private Rect mBounds;

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
                case R.styleable.CustomeView1_text:
                    mTitleText = a.getString(R.styleable.CustomeView1_text);
                    break;
                case R.styleable.CustomeView1_textColor:
                    mTitleTextColor = a.getColor(R.styleable.CustomeView1_textColor,mTitleTextColor);
                    break;
                case R.styleable.CustomeView1_textSize:
                    mTitleTextSize = a.getDimension(R.styleable.CustomeView1_textSize, mTitleTextSize);
                    break;
            }
        }

        a.recycle();

        //初始化画笔
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mBounds = new Rect();

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClickListener=====>>");
                mTitleText = getRandomNum();
                invalidate();

                //This method can be invoked from outside of the UI thread
//                postInvalidate();
            }
        });
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTitleTextSize);
        mTextPaint.setColor(mTitleTextColor);
        mTextWidth = mTextPaint.measureText(mTitleText);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        When overriding this method, you
//                * <em>must</em> call {@link #setMeasuredDimension(int, int)}
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;

        if(widthMode == MeasureSpec.EXACTLY){//MeasureSpec.EXACTLY Match_Parent 或者是 精准的数字
            width = widthSize;
        }else{
            mTextPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBounds);
            int desire = getPaddingLeft() + mBounds.width() + getPaddingRight();
            width = desire;
        }

        if(heightMode == MeasureSpec.EXACTLY){//MeasureSpec.EXACTLY Match_Parent 或者是 精准的数字
            height = heightSize;
        }else{
            mTextPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBounds);
            int desire = getPaddingTop() + mBounds.height() + getPaddingBottom();
            height = desire;
        }
        setMeasuredDimension(width, height);
//        setMeasuredDimension(int, int)
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

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }


    /**
     * 获取四位随机数字
     *
     * @return
     */
    private String getRandomNum(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < 4; i++){
            sb.append(String.valueOf(random.nextInt(10)));
        }
        return sb.toString();
    }
}
