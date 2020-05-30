package com.cl.zlschool;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cl.base.BaseActivity;
import com.cl.fragments.CurriculumFragment;
import com.cl.fragments.HomeFragment;
import com.cl.fragments.MaterialFragment;
import com.cl.fragments.MyCenterFragment;
import com.cl.fragments.VIPFragment;
import com.google.android.material.tabs.TabLayout;

import java.sql.Driver;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements TabLayout.BaseOnTabSelectedListener {


    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.main_tabLayout)
    TabLayout mainTabLayout;
    @BindView(R.id.contentLin)
    LinearLayout contentLin;

    private HomeFragment homeFragment;
    private CurriculumFragment curriculumFragment;
    private VIPFragment vipFragment;
    private MaterialFragment materialFragment;
    private MyCenterFragment myCenterFragment;
    private Fragment lastFragment;
    private FragmentManager manager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbr_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sao:
                initSao();
                break;
            case R.id.msg:
                initMsg();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initMsg() {

    }

    private void initSao() {

    }

    @Override
    protected void initView() {
        mainToolbar.setTitle("");
        setSupportActionBar(mainToolbar);
        initFragment();
        mainTabLayout.setOnTabSelectedListener(this);
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        curriculumFragment = new CurriculumFragment();
        vipFragment = new VIPFragment();
        materialFragment = new MaterialFragment();
        myCenterFragment = new MyCenterFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.contentLin, homeFragment)
                .add(R.id.contentLin, curriculumFragment)
                .add(R.id.contentLin, vipFragment)
                .add(R.id.contentLin, materialFragment)
                .add(R.id.contentLin, myCenterFragment)
                .hide(curriculumFragment)
                .hide(vipFragment)
                .hide(materialFragment)
                .hide(myCenterFragment)
                .commit();
        lastFragment = homeFragment;
    }

    final int HOME = 0;
    final int CURRICULUM = 1;
    final int VIP = 2;
    final int MATERIAL = 3;
    final int MY = 4;
    int fragmentType;

    private Fragment selectFragment(int type) {
        switch (type) {
            case HOME:
                return homeFragment;
            case CURRICULUM:
                return curriculumFragment;
            case VIP:
                return vipFragment;
            case MATERIAL:
                return materialFragment;
            case MY:
                return myCenterFragment;
        }
        return null;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 4) {
            mainToolbar.setVisibility(View.GONE);
        } else {
            mainToolbar.setVisibility(View.VISIBLE);
        }
        switch (tab.getPosition()) {
            case 0:
                fragmentType = HOME;
                break;
            case 1:
                fragmentType = CURRICULUM;
                break;
            case 2:
                fragmentType = VIP;
                break;
            case 3:
                fragmentType = MATERIAL;
                break;
            case 4:
                fragmentType = MY;
                break;
        }
        setFragment();
    }

    private void setFragment() {
        Fragment fragment = selectFragment(fragmentType);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.show(fragment)
                .hide(lastFragment)
                .commit();
        lastFragment = fragment;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
