package com.bupa.ninegridimageview;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bupa.ninegridimageview.adapter.PhotoAdapter;
import com.bupa.ninegridimageview.base.BaseActivity;
import com.bupa.ninegridimageview.entity.Photo;
import com.bupa.ninegridimageview.util.UIUtils;
import com.jaeger.library.StatusBarUtil;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.liuguangqiang.recyclerview.widget.SuperRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String[] IMG_URL_LIST = {
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/1.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/2.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/3.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/4.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/5.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/6.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/7.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/8.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/9.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/10.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/11.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/12.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/13.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/14.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/15.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/16.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/17.jpg",
            "https://coding.net/u/zl1993/p/UploadPic/git/raw/master/pic/18.jpg",
    };
    private List<Photo> mPhotoList;
    private SuperRecyclerView mRvPhotoLister;
    private PhotoAdapter mPhotoAdapter;
    private View mImageView;
    private PopupWindow mPopupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,0xd793d0);
        initView();
        initData();
        listener();
    }

    private void listener() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void initData() {
        mPopupWindow = new PopupWindow(mImageView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPhotoList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            List<String> imgUrls = new ArrayList<>();
            imgUrls.addAll(Arrays.asList(IMG_URL_LIST).subList(0, i % 9 + 1));
            Photo photo = new Photo("看图字不重要。 ( •̀ .̫ •́ )", imgUrls);
            mPhotoList.add(photo);
        }
       /* mPhotoList.add(new Photo("完整九宫格,看图字不重要。 ( •̀ .̫ •́ )", imgUrls.subList(0, 9)));*/
        List<String> imgUrls2 = new ArrayList<>();
        imgUrls2.addAll(Arrays.asList(IMG_URL_LIST).subList(0,IMG_URL_LIST.length));
        mPhotoList.add(new Photo("看图字不重要。 ( •̀ .̫ •́ )", imgUrls2.subList(9, IMG_URL_LIST.length)));
        mPhotoAdapter = new PhotoAdapter(this, mPhotoList, NineGridImageView.STYLE_GRID, mPopupWindow, mImageView, new PhotoAdapter.IShow() {
            @Override
            public void show() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.4f;
                getWindow().setAttributes(lp);
            }
        });
        mRvPhotoLister.setAdapter(mPhotoAdapter);
    }


    private void initView() {
        mRvPhotoLister = (SuperRecyclerView) findViewById(R.id.rv_post_list);
        mRvPhotoLister.setLayoutManager(new LinearLayoutManager(this));
        mImageView = View.inflate(UIUtils.getContext(), R.layout.item_pager_img_sel_bupa, null);
    }

}
