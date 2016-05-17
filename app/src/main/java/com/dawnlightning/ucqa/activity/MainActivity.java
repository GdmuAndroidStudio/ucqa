package com.dawnlightning.ucqa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.model.TestModel;

public class MainActivity extends AppCompatActivity {
    private TestModel testModel=new TestModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testModel.login();
        //testModel.Register();
        testModel.Upload();
    }
}
