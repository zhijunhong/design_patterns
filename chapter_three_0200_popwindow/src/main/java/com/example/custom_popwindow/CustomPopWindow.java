package com.example.custom_popwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by ZhijunHong on 2017/8/11.
 * 自定义popupwindow
 */

public class CustomPopWindow {
    //资源view-id
    private int mResLayoutId = -1;
    //context
    private Context mContext;
    //popupwindow
    private PopupWindow mPopupWindow;
    //宽度
    private int mWidth;
    //高度
    private int mHeight;
    //是否获取焦点-默认获取
    private boolean mIsFocusable = true;
    //显示view
    private View mContentView;
    //点击外面是否取消显示popupwindow
    private boolean mIsOutSize = true;
    //设置加载动画
    private int mAnimationStyle = -1;

    public CustomPopWindow(Context context) {
        this.mContext = context;
    }

    public int getHieght() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    /**
     * 设置PopupWindow位置
     *
     * @param anchor
     * @param offX
     * @param offY
     * @return
     */
    public CustomPopWindow showAsDropDown(View anchor, int offX, int offY) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, offX, offY);
        }
        return this;
    }

    /**
     * 设置PopupWindow位置
     *
     * @param anchor
     * @return
     */
    public CustomPopWindow showAsDropDown(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor);
        }
        return this;
    }

    /**
     * 设置PopupWindow位置
     *
     * @param anchor
     * @param xOff
     * @param yOff
     * @param gravity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CustomPopWindow showAsDropDown(View anchor, int xOff, int yOff, int gravity) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff, gravity);
        }
        return this;
    }

    /**
     * 相对于父控件的位置（可以根据Gravity.BOTTOM...）设置PopupWindow位置
     *
     * @param parent
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public CustomPopWindow showAtLocation(View parent, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(parent, gravity, x, y);
        }
        return this;
    }

    public static class Builder {
        private CustomPopWindow mCustomPopWindow;

        public Builder(Context context) {
            this.mCustomPopWindow = new CustomPopWindow(context);
        }

        public Builder size(int width, int height) {
            mCustomPopWindow.mWidth = width;
            mCustomPopWindow.mHeight = height;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            mCustomPopWindow.mIsFocusable = focusable;
            return this;
        }

        public Builder setView(View view) {
            mCustomPopWindow.mContentView = view;
            mCustomPopWindow.mResLayoutId = -1;
            return this;
        }

        public Builder setView(int resLayoutId) {
            mCustomPopWindow.mResLayoutId = resLayoutId;
            mCustomPopWindow.mContentView = null;
            return this;
        }

        public Builder setOutsideTouchable(boolean outsizeTouchable) {
            mCustomPopWindow.mIsOutSize = outsizeTouchable;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            mCustomPopWindow.mAnimationStyle = animationStyle;
            return this;
        }

        public CustomPopWindow create() {
            //构建popWindow
            mCustomPopWindow.build();

            return mCustomPopWindow;
        }


    }

    /**
     * 创建popupwindow
     */
    private void build() {
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(mResLayoutId, null);
        }

        if (mWidth != 0 && mHeight != 0) {
            mPopupWindow = new PopupWindow(mContentView, mWidth, mHeight);
        } else {
            mPopupWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (mAnimationStyle != -1) {
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }

        mPopupWindow.setFocusable(mIsFocusable);
        mPopupWindow.setOutsideTouchable(mIsOutSize);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (mHeight == 0 || mWidth == 0) {
            mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mWidth = mPopupWindow.getContentView().getMeasuredWidth();
            mHeight = mPopupWindow.getContentView().getMeasuredHeight();
        }
        mPopupWindow.update();
    }

    /**
     * popupwindow消失
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
