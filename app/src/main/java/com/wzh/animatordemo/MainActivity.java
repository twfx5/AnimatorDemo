package com.wzh.animatordemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.wzh.animatordemo.fragments.Demo1;
import com.wzh.animatordemo.fragments.Demo2;
import com.wzh.animatordemo.fragments.Demo3;
import com.wzh.animatordemo.fragments.Demo4;
import com.wzh.animatordemo.fragments.Demo5;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewpager);
        initData();
    }

    private void initData() {
        List<Fragment> listFragment = new ArrayList<Fragment>();
//        listFragment.add(new Demo1());
//        listFragment.add(new Demo2());
//        listFragment.add(new Demo3());
        listFragment.add(new Demo4());
//        listFragment.add(new Demo5());
//        listFragment.add(new Demo6());
//        listFragment.add(new Demo7());
//        listFragment.add(new Demo8());

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(fragmentManager);
        fragmentAdapter.setListFragment(listFragment);
        mViewPager.setAdapter(fragmentAdapter);
    }


}
