package com.bupa.ninegridimageview.app;/**
 * Created by Administrator on 2017/4/25.
 */

import android.app.Application;

import com.bupa.ninegridimageview.util.UIUtils;

/**
 * 作者：ZLei on 2017/4/25 09:20
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UIUtils.init(this);
    }
}
