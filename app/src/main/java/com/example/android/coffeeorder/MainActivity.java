package com.example.android.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // to represent the coffee quantity
    int CoffeeQuantity = 2;
    // to represent the price of the cup of coffee
    int price = 5;

    /*
    this application display order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    /*
    this method will display the number of coffee cups ordered when the order button is clicked.
    also it will display the price using @displayPrice method
     */
    public void makeOrder(View view) {
        displayQuantity(CoffeeQuantity);
        // price is an integer variable fixed to 5$ price
        displayPrice(price * CoffeeQuantity);
    }

    /*
    this method will take a number and display it on the screen when called
     */
    public void displayQuantity(int number) {
        TextView textQuantity = (TextView) findViewById(R.id.quantity_text);
        textQuantity.setText("" + number);
    }


    /*
    this method will display the price of the given quantity of coffee
     */

    public void displayPrice(int coffeePrice) {
        TextView textPrice = (TextView) findViewById(R.id.price_text_view);
        textPrice.setText(NumberFormat.getCurrencyInstance().format(coffeePrice));
    }


}

