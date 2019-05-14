package com.example.android.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    // represents the coffee quantity with this variable
    int coffeeQuantity = 0;
    // making a counter to increase or decrease later
    int counter = 1;
    // make a price variable set it to 0
    int price = 0;

    // the price of topping chocolate
    int chocolatePrice = 3;
    // the price of topping cream
    int creamPrice = 2;


    /*
    this application display order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    } // end of onCreate

    /*
    this method will display the price  when the button order is clicked
     using the displayPrice method and pass in the price variable after multiply it by 5 as a price
     */
    public void makeOrder(View view) {
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        CheckBox cream = (CheckBox) findViewById(R.id.cream_checkbox);

        // handling the toppings with if else statement
        if (chocolate.isChecked() && cream.isChecked()) {

            // ( 1 * 5 ) = 5 price holds 5
            price = (coffeeQuantity * 5);
            // 5 + ( 3 + 2 ) * 1
            price = price + (chocolatePrice + creamPrice) * coffeeQuantity;
            // price is 10
            displayPrice(price);
        } else if (chocolate.isChecked()) {
            // ( 1 * 5 ) = 5 price holds
            price = (coffeeQuantity * 5);
            // 5 + 3 * 1
            price = price + chocolatePrice * coffeeQuantity;
            // price is 8
            displayPrice(price);
        } else if (cream.isChecked()) {
            // ( 1 * 5 ) = 5 price is 5
            price = (coffeeQuantity * 5);
            // 5 + 2 * 1
            price = price + creamPrice * coffeeQuantity;
            // price is 7
            displayPrice(price);
            // 1 * 5 = 5
        } else price = coffeeQuantity * 5;
        // price is 5
        displayPrice(price);

        // need to get the name from the edit text entered by the user to pass it to displayMessage later on
        EditText myname = (EditText) findViewById(R.id.edit_text);
        // need to save myname in temp holder first and get the name using getText  , you must also cast the text to be safe.
        String tempHolder = String.valueOf(myname.getText());
        // saving the name from the user along side the price and pass it to the method displayMessage to handle it later
        String TheName = "The total is: $" + price + "\n";
        TheName = TheName + "Thank you " + " " + tempHolder;
        // passing the variable TheName to the method displayMessage()
        displayMessage(TheName);
    }

    /*
    this method is going to take the name from the user when entered in edit text
    we need to pass this method to makeOrder method to handle it probably
    going to add the new message alongside the displayPrice method

     */
    public void displayMessage(String message) {
        TextView textView = (TextView) findViewById(R.id.price_text);
        textView.setText(message);
    }

    /*
    this method is going to change the quantity text view and  when every the add and subtract
    buttons are clicked
    basically it will display the quantity
     */

    public void displayQuantity(int number) {
        TextView textView = (TextView) findViewById(R.id.quantity_text);
        textView.setText("" + number);
    }

    /*
    this method is going to display the Price of the ordered coffee when the button ordered is clicked on
    it will change the price text view and add the price based on the coffee quantity
    the price text will change to 0.00  that's why we are using number format and get getCurrencyInstance
     */
    public void displayPrice(int coffeePrice) {
        TextView coffee = (TextView) findViewById(R.id.price_text);
        coffee.setText(NumberFormat.getCurrencyInstance().format(coffeePrice));
    }

    /*
    this method will decrease the coffeeQuantity variable by 1
    you need to call the method displayQuantity to pass the quantity later on
     */
    public void SubtractCoffee(View view) {
        if (coffeeQuantity > 0) {
            coffeeQuantity -= counter;
            displayQuantity(coffeeQuantity);
        }
    }

    /*
  this method will increase the coffeeQuantity variable by 1
  you need to call the method displayQuantity to pass the quantity later on
   */
    public void AddCoffee(View view) {
        if (coffeeQuantity < 100) {
            coffeeQuantity += counter;
            displayQuantity(coffeeQuantity);
        }
    }

} // end of class

