package com.distribuidora.appsbo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.distribuidora.appsbo.distribuidora.R;


public class MainActivityOld extends Activity implements
        ActionBar.OnNavigationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);
    }


    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item
        return true;
    }


}

