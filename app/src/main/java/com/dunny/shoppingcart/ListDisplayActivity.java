package com.dunny.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListDisplayActivity extends AppCompatActivity {

    ListActivity taskList;
    ListActivity doneList;
    Button btnAdd;
    EditText etTask;
    ListView lvDone;
    ListView lvTodo;
    ActivityAdapter activityAdapter;
    ActivityAdapter activityAdapter2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        sharedPreferences = getSharedPreferences("dataApp", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean load = getIntent().getBooleanExtra("load",false);

        //region Animated background
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        ConstraintLayout lvDoneList = findViewById(R.id.theRealListContainer);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) lvDoneList.getBackground();
        animationDrawable1.setEnterFadeDuration(2400);
        animationDrawable1.setExitFadeDuration(4000);
        animationDrawable1.start();

        ConstraintLayout clGetList = findViewById(R.id.theRealGETListContainer);
        AnimationDrawable animationDrawable2 = (AnimationDrawable) clGetList.getBackground();
        animationDrawable2.setEnterFadeDuration(2400);
        animationDrawable2.setExitFadeDuration(4000);
        animationDrawable2.start();
        //endregion
        //region Find all the stuff
        btnAdd = findViewById(R.id.addButton);
        etTask = findViewById(R.id.etTask);
        lvTodo = findViewById(R.id.doneList);
        lvDone = findViewById(R.id.getList);

        if (load) {
            String dataRecovered = sharedPreferences.getString("keyList", "");
            String dataRecovered2 = sharedPreferences.getString("keyList2", "");
            if (dataRecovered.isEmpty()) {
                taskList = new ListActivity();
            } else {
                taskList = new ListActivity().fromJson(dataRecovered);

            }
            if (dataRecovered2.isEmpty()) {
                doneList = new ListActivity();
            } else {
                doneList = new ListActivity().fromJson(dataRecovered2);
            }
        }
        else
        {
            taskList = new ListActivity();
            doneList = new ListActivity();
        }

        activityAdapter = new ActivityAdapter(ListDisplayActivity.this,R.layout.middleman,taskList.list);
        activityAdapter2 = new ActivityAdapter(ListDisplayActivity.this,R.layout.middleman,doneList.list);
        lvTodo.setAdapter(activityAdapter);
        lvDone.setAdapter(activityAdapter2);
        //endregion

        //region Add and remove stuff to/from the lists
        btnAdd.setOnClickListener(View ->{
           String data = etTask.getText().toString();
           if (data.isEmpty()){
               Toast.makeText(ListDisplayActivity.this,"Fill out your next task!",Toast.LENGTH_SHORT).show();
           }
           else
           {
               taskList.list.add(new Activity(data,false));
               activityAdapter.notifyDataSetChanged();
               editor.putString("keyList", taskList.toJson()).apply();
               etTask.setText(null);
           }
        });
        lvTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Add to done list
                doneList.list.add(taskList.list.get(i));
                //Delete from this list
                taskList.list.remove(i);
                //Save json and notify adapter
                activityAdapter.notifyDataSetChanged();
                activityAdapter2.notifyDataSetChanged();
                editor.putString("keyList2",doneList.toJson()).apply();
                editor.putString("keyList", taskList.toJson()).apply();

            }
        });
        lvDone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Add to done list
                taskList.list.add(doneList.list.get(i));
                //Delete from this list
                doneList.list.remove(i);
                //Save json and notify adapter
                activityAdapter.notifyDataSetChanged();
                activityAdapter2.notifyDataSetChanged();
                editor.putString("keyList2",doneList.toJson()).apply();
                editor.putString("keyList", taskList.toJson()).apply();

            }
        });
        lvTodo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                taskList.list.remove(i);
                activityAdapter.notifyDataSetChanged();
                editor.putString("keyList", taskList.toJson()).apply();
                return false;
            }
        });
        lvDone.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                doneList.list.remove(i);
                activityAdapter2.notifyDataSetChanged();
                editor.putString("keyList2", doneList.toJson()).apply();
                return false;
            }
        });
     }
}