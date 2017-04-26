package com.bupa.ninegridimageview.entity;/**
 * Created by Administrator on 2017/4/24.
 */

import java.util.List;

/**
 * 作者：ZLei on 2017/4/24 17:50
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class Photo {
    private String mContent;
    private List<String> mImgUrlList;

    public Photo(String content, List<String> imgUrlList) {
        mContent = content;
        mImgUrlList = imgUrlList;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public List<String> getImgUrlList() {
        return mImgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        mImgUrlList = imgUrlList;
    }
}
