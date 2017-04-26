package com.bupa.ninegridimageview.adapter;/**
 * Created by Administrator on 2017/4/25.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bupa.ninegridimageview.R;
import com.bupa.ninegridimageview.entity.Photo;
import com.bupa.ninegridimageview.util.UIUtils;
import com.bupa.ninegridimageview.view.BadgeDrawable;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.List;

import static com.bupa.ninegridimageview.util.UIUtils.dp2px;

/**
 * 作者：ZLei on 2017/4/25 10:27
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private final LayoutInflater mInflater;
    private List<Photo> mPostList;
    private int mShowStyle;
    private PopupWindow mPopupWindow;
    private View mView;
    private IShow mShow;

    public PhotoAdapter(Context context, List<Photo> postList, int showStyle) {
        mInflater = LayoutInflater.from(context);
        mPostList = postList;
        mShowStyle = showStyle;
    }

    public PhotoAdapter(Context context, List<Photo> postList, int showStyle, PopupWindow window, View view, IShow show) {
        mInflater = LayoutInflater.from(context);
        mPostList = postList;
        mShowStyle = showStyle;
        mPopupWindow = window;
        mView = view;
        mShow = show;
    }


    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mShowStyle == NineGridImageView.STYLE_FILL) {
            return new PhotoViewHolder(mInflater.inflate(R.layout.item_post_fill_style, parent, false));
        } else {
            return new PhotoViewHolder(mInflater.inflate(R.layout.item_post_grid_style, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bind(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private NineGridImageView<String> mNglContent;
        private TextView mTvContent;

        private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                Glide.with(context).load(s).placeholder(R.drawable.ic_default_image).into(imageView);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<String> list) {
                preview(mNglContent.getChildAt(index), index, list);
            }
        };

        public PhotoViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
            mNglContent = (NineGridImageView<String>) itemView.findViewById(R.id.ngl_images);
            mNglContent.setAdapter(mAdapter);
            mNglContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(Photo post) {
            mNglContent.setImagesData(post.getImgUrlList());
            mTvContent.setText(new SpannableString(TextUtils.concat(
                    new BadgeDrawable.Builder()
                            .type(BadgeDrawable.TYPE_WITH_TWO_TEXT_COMPLEMENTARY)
                            .badgeColor(0xffCC9933)
                            .text1(post.getContent())
                            .text2("QQ:2293809059")
                            .textSize(50)
                            .padding(dp2px(10), dp2px(10), dp2px(10), dp2px(10), dp2px(10))
                            .strokeWidth((int) dp2px(2))
                            .build().toSpannable()
            )));

            Log.d("jaeger", "九宫格高度: " + mNglContent.getMeasuredHeight());
            Log.d("jaeger", "item 高度: " + itemView.getMeasuredHeight());
        }
    }

    private void preview(View view, int position, List<String> list) {
        /*View imageView = View.inflate(UIUtils.getContext(), R.layout.item_pager_img_sel_bupa, null);
        mPopupWindow = new PopupWindow(imageView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);*/
        //设置需要焦点
        mPopupWindow.setFocusable(true);
        mPopupWindow.dismiss();
        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mShow.show();
        //设置进入和出去的动画
        mPopupWindow.setAnimationStyle(R.style.PopupWindowStyle);
        //在给定的view的下面显示， 后面的两个参数分别对应的是x方向和Y方向的偏移量
        mPopupWindow.showAsDropDown(view, 60, -view.getHeight() + 10);
        ImageView image = (ImageView) mView.findViewById(R.id.ivImage);
        View close = mView.findViewById(R.id.ivPhotoCheaked);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        Glide.with(UIUtils.getContext()).load(list.get(position)).placeholder(R.drawable.ic_default_image).into(image);
    }

    public interface IShow {
        void show();
    }
}
