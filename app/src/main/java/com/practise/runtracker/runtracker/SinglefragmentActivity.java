package com.practise.runtracker.runtracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by e00959 on 1/23/2015.
 */
public abstract  class SinglefragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager l_fragManager=getSupportFragmentManager();
        Fragment l_crimeFrag    =   l_fragManager.findFragmentById(R.id.fragmentContainer);

        if(l_crimeFrag==null)
        {
            l_crimeFrag = createFragment();
            FragmentTransaction l_fragTrans =   l_fragManager.beginTransaction();
            l_fragTrans.add(R.id.fragmentContainer,l_crimeFrag);
            l_fragTrans.commit();

        }

    }

    protected abstract Fragment createFragment();
}
