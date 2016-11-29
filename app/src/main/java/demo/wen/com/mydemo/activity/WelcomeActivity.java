package demo.wen.com.mydemo.activity;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import demo.wen.com.mydemo.R;
import demo.wen.com.mydemo.base.BaseActivity;
import demo.wen.com.mydemo.handler.MyHandler;
import demo.wen.com.mydemo.handler.OnHandlerListener;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity implements OnHandlerListener {
    @Bind(R.id.main_blur_bg)
    ImageView main_blur_bg;
    @Bind(R.id.wel_text)
    TextView wel_text;
    private TimeCount time;
    int times = 1000;
    private MyHandler handler;


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        handler = new MyHandler(this);
        handler.setOnHandlerListener(this);
//        main_blur_bg.setOnTouchListener(this);
        time = new TimeCount(times, 100);
        time.start();
    }

    private float mLastY;

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float alphaDelt = (y - mLastY) / 100;
                float alpha = main_blur_bg.getAlpha() + alphaDelt;
                if (alpha > 1.0) {
                    alpha = 1.0f;
                } else if (alpha < 0.0) {
                    alpha = 0.0f;
                }
                main_blur_bg.setAlpha(alpha);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void onHandlerMessage(Activity activity, Message msg) {
        switch (msg.what) {
            case 0:
                startActivity(MainActivity.class);
                finish();
                break;
        }

    }

    //倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            handler.sendEmptyMessageAtTime(0, 500);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int l = (int) millisUntilFinished / 100;
            float i = (float) l / 10;
            main_blur_bg.setAlpha(i);
            wel_text.setAlpha(1 - i);
        }
    }
}

