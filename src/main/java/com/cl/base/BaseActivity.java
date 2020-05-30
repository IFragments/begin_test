package com.cl.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.logic.LoadTypeConfig;
import com.cl.utils.Application1907;
import com.cl.utils.DataType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected Application1907 application;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        application = (Application1907) getApplication();
        ButterKnife.bind(this);
        initView();
        initData();
    }
    public void setRecycler(RecyclerView recycler, SmartRefreshLayout smartRefreshLayout, DataType dataType) {
        if (recycler != null) recycler.setLayoutManager(new LinearLayoutManager(application));
        if (smartRefreshLayout != null && dataType != null) {
            smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> dataType.getType(LoadTypeConfig.MORE));
            smartRefreshLayout.setOnRefreshListener(refreshLayout -> dataType.getType(LoadTypeConfig.REFRESH));
        }
    }
    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();

}
