package com.bupa.ninegridimageview;/**
 * Created by Administrator on 2017/4/26.
 */

import android.content.Intent;
import android.os.Bundle;

import com.bupa.ninegridimageview.base.BaseActivity;
import com.bupa.ninegridimageview.util.UIUtils;
import com.jaeger.library.StatusBarUtil;

import me.wangyuwei.particleview.ParticleView;

/**
 * 作者：ZLei on 2017/4/26 11:06
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class WelcomeActivity extends BaseActivity {
    private ParticleView pv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StatusBarUtil.setTranslucent(this, 55);
        initView();
        pv.startAnim();
        pv.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(UIUtils.getContext(),MainActivity.class));
            }
        });
    }

    private void initView() {
        pv = (ParticleView) findViewById(R.id.pv);
    }
}
