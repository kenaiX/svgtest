package cc.kenai.common;

import android.app.Activity;
import android.os.Bundle;

import cc.kenai.svg.R;

public abstract class LoadingActivity extends Activity {
    private AnimatedSvgView mAnimatedSvgView;

    public abstract void loadingFinish();

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_main);
    }

    @Override
    protected final void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.loading_svg);
        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FINISHED) {
                    loadingFinish();
                    finish();
                }
            }
        });
        mAnimatedSvgView.start();
    }

}