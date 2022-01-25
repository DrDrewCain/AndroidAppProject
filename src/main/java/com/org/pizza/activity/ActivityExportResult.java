package com.org.pizza.activity;

import android.os.Bundle;

import com.org.pizza.base.BaseActivity;
import com.org.pizza.databinding.ActivityExpoertBinding;

public class ActivityExportResult extends BaseActivity {
    ActivityExpoertBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityExpoertBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initView();
    }
    private void initView(){
        String result = getIntent().getStringExtra("export");
        bind.export.setText(result);
    }
}
