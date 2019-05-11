package com.example.android.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    /*
    this application display order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


/*
this method will display the text when the order button is clicked.
 */
    public void makeOrder(View view) {
        display(1);
    }


    public void display(int number) {
        TextView textView = (TextView) findViewById(R.id.quantity_text);
        textView.setText("" + number);


    }

}

