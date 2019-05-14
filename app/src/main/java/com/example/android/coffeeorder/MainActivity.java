package com.example.android.coffeeorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    String TheName;


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
        // passing the variable TheName to the method displayMessage()
        displayMessage(tempHolder);
        //this method will open the email and send the order with the details .
        sendMail(view);

    }

    /*
    this method is going to handle the name taken from the user when entered in edit text
    we need to pass this method to makeOrder method to handle it probably
    going to add the new message alongside the displayPrice method

     */
    public void displayMessage(String message) {
        TextView textView = (TextView) findViewById(R.id.price_text);
        // saving the name from the user along side the price and pass it to the method displayMessage to handle it later
        TheName = "Name : " + message + "\n";
        TheName = TheName + "Quantity : " + coffeeQuantity + "  \n";
        TheName = TheName + "Total is : $" + price + "\n";
        TheName = TheName + "Thank you !! ";
        textView.setText(TheName);

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
            //   Log.v("MainActivity" , "coffee been added" + coffeeQuantity);
            coffeeQuantity += counter;
            displayQuantity(coffeeQuantity);
        }
    }

    /*
 this method will handle sending an email when the button send by email is clicked
     */

//    public void composeEmail(String[] addresses, String subject) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    public void sendMail(View view) {
        String[] to = {"shadow8evil@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:")).setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to); // recipient email addresses
        emailIntent.putExtra(Intent.EXTRA_TEXT, TheName);// the body of the message
        try {
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
            finish();
            Log.i("Finished sending email", "email been sent ");
        } catch (
                android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }


} // end of class

