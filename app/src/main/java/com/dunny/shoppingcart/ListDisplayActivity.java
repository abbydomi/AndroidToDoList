package com.dunny.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ListView;

public class ListDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        //Animated background
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

     }
}