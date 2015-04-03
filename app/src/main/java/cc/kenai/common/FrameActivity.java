package cc.kenai.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import cc.kenai.svg.R;

public abstract class FrameActivity extends ActionBarActivity {
    private AnimatedSvgView mAnimatedSvgView;

    /**
     * should do something in Anoter Thread
     */
    public abstract void withCreate();

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setFullScreen(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected final void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FINISHED) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatedSvgView, "alpha", 1f, 0f);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            setFullScreen(false);
                            getSupportActionBar().show();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.start();
                }
            }
        });
        mAnimatedSvgView.start();
    }

    public final void setFullScreen(boolean b){
        if(b){
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(params);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }else{
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(params);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


}