package com.likefunnythings.androidtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Administrator on 2016/1/12.
 */
public class CustomeImageView extends View{

    /**
     * 图片放缩类型
     */
    public static final int IMAGE_SCALE_FIT_XY = 0; //铺满View
    public static final int IMAGE_SCALE_CENTER = 1; //居中

    /**
     * 底部文字属性
     */
    private String mTitleText;
    private int mTitleTextColor;
    private float mTitleTextSize;

    /**
     * 图片部分
     */
    private Bitmap mBitmap;
    private int mImageScaleType;

    /**
     * Paint相关之类的
     */
    private Paint mPaint;
    private Rect mTextBounds;

    /**
     * 边框
     */
    private Rect mBoundRect;

    private int mWidth;
    private int mHeight;

    public CustomeImageView(Context context) {
        this(context, null);
    }

    public CustomeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取Attr自定义的属性
        TypedArray a = null;
//                = getContext().obtainStyledAttributes(attrs, R.styleable.CustomeImageView, defStyleAttr, 0);
//        int index = a.getIndexCount();
//        for(int i=0; i < index; index ++){
//            int attr = a.getIndex(i);
//            switch (attr){
//                case R.styleable.CustomeImageView_img:
//                    mBitmap = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CustomeImageView_img, 0));
//                    break;
//                case R.styleable.CustomeImageView_imgScaleType:
//                    mImageScaleType = a.getInt(R.styleable.CustomeImageView_imgScaleType, 0);
//                    break;
//                case R.styleable.CustomeImageView_titleText:
//                    mTitleText = a.getString(R.styleable.CustomeImageView_titleText);
//                    break;
//                case R.styleable.CustomeImageView_titleTextColor:
//                    mTitleTextColor = a.getColor(R.styleable.CustomeImageView_titleTextColor, 0);
//                    break;
//                case R.styleable.CustomeImageView_titleTextSize:
//                    mTitleTextSize = a.getDimension(R.styleable.CustomeImageView_titleTextSize, 14f);
//                    break;
//            }
//        }
        a.recycle();

        //初始化Paint及一些前提条件
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTitleTextSize);
        mTextBounds = new Rect();
        mBoundRect = new Rect();
        //获取文字的边框
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBounds);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量Width
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY){ //Match_Parent or 精确的
            mWidth = specSize;
        }else{// Wrap_content or 其他的
            // 宽度测量策略 取文字或者是图片宽度的最大值
            int desireImgWidth = getPaddingLeft() + mBitmap.getWidth() + getPaddingRight();
            int desireTextWidth = getPaddingLeft() + mTextBounds.width() + getPaddingRight();
            if(specMode == MeasureSpec.AT_MOST){
                int desire = Math.max(desireImgWidth, desireTextWidth);
                mWidth = Math.min(desire, specSize);
            }

        }


        //测量高度
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY){ //Match_Parent or 精确的
            mHeight = specSize;
        }else{// Wrap_content or 其他的
            // 高度测量策略
            int desireImageHeight = mBitmap.getHeight();
            int desireTextHeight = mTextBounds.height();
            if(specMode == MeasureSpec.AT_MOST){
                int desire = getPaddingTop() + desireImageHeight + desireTextHeight + getPaddingBottom();
                mHeight = desire;
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        //绘画边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTitleTextColor);
        mPaint.setTextSize(mTitleTextSize);

        mBoundRect.left = getPaddingLeft();
        mBoundRect.top = getPaddingTop();
        mBoundRect.right = mWidth - getPaddingRight();
        mBoundRect.bottom = mHeight - getPaddingBottom();

        //1.先绘画文字
//        @NonNull String text, float x, float y, @NonNull Paint paint
        if(mTextBounds.width() > mWidth){//字体长度大于自定义View的Width
            TextPaint textPaint = new TextPaint(mPaint);
            mTitleText = (String) TextUtils.ellipsize(mTitleText,textPaint,
                    mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END);
            // 字体过长的绘画
            canvas.drawText(mTitleText, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint );
        }else{

            canvas.drawText(mTitleText, mWidth / 2 - mTextBounds.width() / 2 - getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        }


        mBoundRect.bottom = mBoundRect.bottom - mTextBounds.height();
        //2.再绘画图像  根据scaleType
        if(mImageScaleType == IMAGE_SCALE_FIT_XY){//铺满整个控件
            canvas.drawBitmap(mBitmap,null, mBoundRect, mPaint);
        }else{ //居中对齐
            mBoundRect.left = mWidth / 2 - mBitmap.getWidth() / 2;
            mBoundRect.right = mWidth /2 + mBitmap.getWidth() /2;
            mBoundRect.top = (mWidth - mTextBounds.height()) / 2 - mBitmap.getHeight() /2;
            mBoundRect.bottom = (mWidth - mTextBounds.height()) / 2 + mBitmap.getHeight() /2;
            canvas.drawBitmap(mBitmap, null, mBoundRect, mPaint);
        }

        super.onDraw(canvas);
    }
}
