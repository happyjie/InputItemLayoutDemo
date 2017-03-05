package com.example.asus.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    InputItemLayout llCreateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llCreateDate = (InputItemLayout) findViewById(R.id.ll_create_date);
        llCreateDate.setArrowShow(false);
    }

    public void doClick(View view){
        String name = ((InputItemLayout)findViewById(view.getId())).getName();
        if(name.startsWith("*")){
            name = name.substring(1);
        }else if(name.endsWith("*")){
            name = name.substring(0, name.length()-1);
        }
        Snackbar.make(findViewById(R.id.container), "点击了"+ name, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
