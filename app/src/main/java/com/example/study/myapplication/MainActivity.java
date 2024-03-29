package com.example.study.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] Activity_list={"Main2Activity","Main3Activity"};
    private ListView mainlistview;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);

        Transition reenterTrans = new Slide();
        getWindow().setReenterTransition(reenterTrans);
        Transition enterTrans = new Explode();
        getWindow().setEnterTransition(enterTrans);

        Transition returnTrans = new Slide();
        getWindow().setReturnTransition(returnTrans);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainlistview = (ListView) findViewById(R.id.mylist);



        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Activity_list);
        mainlistview.setAdapter(arrayAdapter);



        mainlistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name=Activity_list[position];
        try {
            Class selected=Class.forName("com.example.study.myapplication."+name);
            Intent selectedintent=new Intent(this,selected);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(selectedintent,options.toBundle());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
