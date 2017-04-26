package com.bupa.ninegridimageview.base;/**
 * Created by Administrator on 2017/4/25.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bupa.ninegridimageview.server.ViewServer;

/**
 * 作者：ZLei on 2017/4/25 09:41
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewServer.get(this).addWindow(this);
    }
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}
