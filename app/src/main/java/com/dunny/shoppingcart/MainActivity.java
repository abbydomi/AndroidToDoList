package com.dunny.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bNew, bLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animated background
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        //Find the buttons
        bNew = findViewById(R.id.buttonNew);
        bLoad = findViewById(R.id.buttonContinue);

        bNew.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListDisplayActivity.class);
            intent.putExtra("load",false);
            startActivity(intent);
        });

        bLoad.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListDisplayActivity.class);
            intent.putExtra("load",true);
            startActivity(intent);
        });
    }
}