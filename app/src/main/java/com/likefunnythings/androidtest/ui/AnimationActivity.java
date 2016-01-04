package com.likefunnythings.androidtest.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.likefunnythings.androidtest.R;

/**
 * 动画测试的Activity
 */
public class AnimationActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_alpha_animation;
    Button btn_scale_animation;
    Button btn_translate_animation;
    Button btn_rotate_animation;

    ImageView iv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        initView();
        
    }

    /**
     * 初始化View
     */
    private void initView() {

        btn_alpha_animation = (Button) this.findViewById(R.id.btn_alpha_animation);
        btn_scale_animation = (Button) this.findViewById(R.id.btn_scale_animation);
        btn_translate_animation = (Button) this.findViewById(R.id.btn_translate_animation);
        btn_rotate_animation = (Button) this.findViewById(R.id.btn_rotate_animation);

        iv_content = (ImageView) this.findViewById(R.id.iv_content);

        btn_alpha_animation.setOnClickListener(this);
        btn_scale_animation.setOnClickListener(this);
        btn_translate_animation.setOnClickListener(this);
        btn_rotate_animation.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        动画的调用方式
//        view.startAnimation(animation);
////或者这样
//        view.setAnimation(animation);
//        animation.start();
        switch (v.getId()){
            case R.id.btn_alpha_animation:{
                iv_content.startAnimation(getAnimation(AnimationType.ALPHA));
//                iv_content.startAnimation(AnimatorInflater.loadAnimator(this, R.animator.alpha));
                break;
            }
            case R.id.btn_scale_animation:{
                iv_content.startAnimation(getAnimation(AnimationType.SCALE));
                break;
            }
            case R.id.btn_translate_animation:{
                iv_content.startAnimation(getAnimation(AnimationType.TRANSALATE));
                break;
            }
            case R.id.btn_rotate_animation:{
                iv_content.startAnimation(getAnimation(AnimationType.ROTATE));
                break;
            }
            default:


        }
    }


    enum AnimationType{
        ALPHA,//透明度变换
        SCALE,//缩放变换
        TRANSALATE,//平移变换
        ROTATE;//旋转变化
    }

    private Animation getAnimation(AnimationType type){
        switch (type){
            case ALPHA:{

//                透明度变换 动画
                Animation animation = new AlphaAnimation(0.5f, 0.9f);
                animation.setDuration(2000l);
                animation.setRepeatMode(Animation.REVERSE);//设置重复模式
                animation.setRepeatCount(Animation.INFINITE);//设置重复次数
                animation.setAnimationListener(new Animation.AnimationListener() {//设置动画的监听
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
//                animation.set
                return animation;
            }
            case SCALE:{//缩放动画
                Animation animation = new ScaleAnimation(0f, 1f, 0f, 1f);
                animation.setDuration(2000l);
                animation.setRepeatMode(Animation.REVERSE);//设置重复模式
                animation.setRepeatCount(Animation.INFINITE);//设置重复次数
                return animation;
            }
            case TRANSALATE:{//平移动画
                Animation animation = new TranslateAnimation(-50f, 50f, -50f, 50f);
                animation.setDuration(2000l);
                animation.setRepeatMode(Animation.REVERSE);//设置重复模式
                animation.setRepeatCount(Animation.INFINITE);//设置重复次数
//                animation.setInterpolator(new AccelerateInterpolator());
                animation.setInterpolator(new OvershootInterpolator());
                return animation;
            }
            case ROTATE:{
                Animation animation = new RotateAnimation(45f,-45f);
//                Animation animation = new RotateAnimation(45f,-45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
                animation.setDuration(2000l);
                animation.setRepeatMode(Animation.REVERSE);//设置重复模式
                animation.setRepeatCount(Animation.INFINITE);//设置重复次数
//                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                return animation;
            }

        }
        return null;
    }



//    ALPHA,//透明度变换
    /**
        就是改变视图的透明度，可以实现淡入淡出等动画。这个动画比较简单只需要设置开始透明度和结束透明度即可
    */

//    SCALE,//缩放变换
    /**
    缩放动画，支持设置开始x缩放（宽度缩放倍数），开始y缩放， 结束x缩放，结束y缩放，以及缩放基点x坐标，缩放基点y坐标。

    x缩放和y缩放都是相对于原始的宽度和高度的，1.0表示不缩放。

    坐标基点，同时有参数可以设置坐标基点类型，分别是：

            Animation.ABSOLUTE(默认值) 相对于控件的0点的坐标值
    Animation.RELATIVE_TO_SELF 相对于自己宽或者高的百分比（1.0表示100%）
    Animation.RELATIVE_TO_PARENT 相对于父控件的宽或者高的百分比.

    默认基点是视图的0点，默认坐标基点类型是ABSOLUTE。
     */


//    TRANSALATE,//平移变换

    /**
     * 平移支持x轴平移起点和y轴平移起点，以及设置结束点。同时每个点都可以设置type，type和上面缩放动画的基点类型一样,默认类型是ABSOLUTE.
     */

//    ROTATE;//旋转变化

    /**
     * 旋转支持设置旋转开始角度，和旋转结束角度，以及旋转基点，和旋转基点类型。类型同上面一样,默认旋转基点是（0，0），默认类型同上面一样，也不多说了。
     */



}
