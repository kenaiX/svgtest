
package cc.kenai.svg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends Activity {

    private Handler mHandler = new Handler();

    private Button mReset;
    private AnimatedSvgView mAnimatedSvgView;
    private float mInitialLogoOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 49,
                getResources().getDisplayMetrics());

        mReset = (Button) findViewById(R.id.reset);
        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);


        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimatedSvgView.reset();
                ViewHelper.setTranslationY(mAnimatedSvgView, mInitialLogoOffset);

                animateLogo();
            }
        });

        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FILL_STARTED) {
                }
            }
        });
    }

    private void animateLogo() {
        mAnimatedSvgView.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateLogo();
            }
        }, 1000);
    }

}
