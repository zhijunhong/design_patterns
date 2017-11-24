package com.fudaojun.chapter_one_0300.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public class BaseActivity extends AppCompatActivity {
    private OnRequestPermissionListener mLisenter;

    /**
     * 获取系统权限
     */
    protected void requestPermission(String[] permissions, int requestCode, OnRequestPermissionListener listener) {
        this.mLisenter = listener;

        if (Build.VERSION.SDK_INT >= M) {
            if (checkPermission(permissions)) {
                //需要申请权限-在回调方法中判断是否获取权限
                requestPermissions(permissions, requestCode);
            } else {
                //权限通过
                if (listener != null) {
                    listener.permissionGrant();
                }
            }
        } else {
            if (listener != null) {
                listener.permissionGrant();
            }
        }
    }

    /**
     * @param permissions
     */
    private boolean checkPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取权限成功或失败的回调
     */
    protected interface OnRequestPermissionListener {
        void permissionGrant();

        void permissionReject();
    }


    /**
     * 申请权限结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (checkResultPermissin(grantResults)) {
                //获取权限失败
                if (mLisenter != null) {
                    mLisenter.permissionReject();
                }
            } else {
                //获取权限成功
                mLisenter.permissionGrant();
            }
        }
    }

    /**
     * 检查权限获取结果
     *
     * @param resultPermissions
     * @return
     */
    private boolean checkResultPermissin(int[] resultPermissions) {
        for (int result : resultPermissions) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

}
