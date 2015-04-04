package com.practise.runtracker.runtracker;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class RunActivity extends SinglefragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RunFragment();
    }
}
