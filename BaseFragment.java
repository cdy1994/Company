package com.huahui.company;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huahui.company.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    /**
     * activity的软引用，防止acitivity消失时，获取为null
     */
    protected SoftReference<Activity> mActivity;
    public BaseActivity baseContext;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.mActivity = new SoftReference<Activity>(activity);
        baseContext = (BaseActivity)getActivity();
    }

    public View mContentView = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mContentView) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (null != parent) {
                parent.removeView(mContentView);
            }
        } else {
            mContentView = inflater.inflate(getLayoutId(), null);
        }
        ButterKnife.bind(this, mContentView);
        initView();

        return mContentView;
    }

    /**
     * 设置布局文件
     *
     * @return 返回布局文件资源Id
     */
    public abstract int getLayoutId();
    //数据初始化
    public abstract void initView();


    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event) {

    }

    //页面跳转
    public <T extends BaseActivity> void intentActivity(Class<T> clazz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }
}
