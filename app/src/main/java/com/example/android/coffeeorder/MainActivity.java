package com.example.android.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // to represent the coffee quantity
    private int CoffeeQuantity = 0;
    // to represent the price of the cup of coffee
    private int price = 0;
    /* counter to start at 0 will increase or decrease based on each click by the methods below
         add and subtract coffee
        */
    private int counter = 1;


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
        //  displayPrice(price * CoffeeQuantity  );
        price = CoffeeQuantity * 5;
        EditText myname = (EditText) findViewById(R.id.edit_text);
        String edittextName = String.valueOf(myname.getText());
        String priceMessage = "Total : $ " + price;
        priceMessage = priceMessage + "\n thank you" + " " + edittextName;
        displayMessage(priceMessage);
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



    /*
    this method will display thank you message when the order is done , need to link it with @makeOrder method
     */

    public void displayMessage(String message) {
        TextView messagePrice = (TextView) findViewById(R.id.price_text_view);
        messagePrice.setText(message);

    }


    /*
    this method is going to add the coffee quantity by one when clicked
     */
    public void AddCoffee(View view) {
        CoffeeQuantity += counter;
        displayQuantity(CoffeeQuantity);
    }


    /*
       this method is going to subtract  the coffee quantity by one when clicked
        */
    public void SubtractCoffee(View view) {
        CoffeeQuantity -= counter;
        displayQuantity(CoffeeQuantity);


    }
}

