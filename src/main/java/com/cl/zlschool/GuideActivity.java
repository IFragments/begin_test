package com.cl.zlschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.cl.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.zl_guide_activity)
    RelativeLayout relativeLayout;
    @BindView(R.id.guide_btn_jump)
    Button guideBtnJump;
    @BindView(R.id.guide_image)
    ImageView guideImage;
    @BindView(R.id.zl_di)
    ImageView zlDi;

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initData() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            count--;
            if (count == 5) {
                guideBtnJump.setText("跳过 " + count);
                guideBtnJump.setVisibility(View.VISIBLE);
                guideImage.setImageResource(R.drawable.zls);
                initHandler();
            } else if (count <= 0) {
                handler.removeMessages(100);
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                guideBtnJump.setText("跳过 " + count);
                initHandler();
            }
        }
    };

    int count = 6;

    private void initHandler() {
        Message obtain = Message.obtain();
        obtain.what = 100;
        handler.sendMessageDelayed(obtain, 1000);
    }

    ;

    @Override
    protected void initView() {
        guideBtnJump.setOnClickListener(this);
        initHandler();
    }


    @Override
    public void onClick(View v) {
        handler.removeMessages(100);
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }
}