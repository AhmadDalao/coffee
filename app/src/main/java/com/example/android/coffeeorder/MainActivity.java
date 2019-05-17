package com.example.android.coffeeorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // represents the coffee quantity with this variable
    int coffeeQuantity = 1;

    /*
    this application display order form to order coffee.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    } // end of onCreate

    public void SubtractCoffee(View view) {
        if (coffeeQuantity <= 1) {
            Toast.makeText(this, "you cant have less than 1 cup of coffee", Toast.LENGTH_LONG).show();
        } else
            coffeeQuantity = coffeeQuantity - 1;
        displayQuantity(coffeeQuantity);
    }


    public void AddCoffee(View view) {
        if (coffeeQuantity >= 100) {
            Toast.makeText(this, "you cant have more than 100 cups of coffee!!", Toast.LENGTH_SHORT).show();
        } else
            coffeeQuantity = coffeeQuantity + 1;
        displayQuantity(coffeeQuantity);
    }


    public int calculatePrice(int number, boolean hasChocolate, boolean hasCream) {
        int price = 5;
        if (hasChocolate && hasCream) {
            // 5 =  5 * 5 = 25
            price = (number * price);
            price = price + (3 + 2) * number;
            return price;
        } else if (hasChocolate) {
            price = (number * price);
            price = price + (3 * number);
            return price;

        } else if (hasCream) {
            price = (number * price);
            price = price + (2 * number);
            return price;

        }
        price = (number * price);
        return price;
    }

    public void displayQuantity(int number) {
        TextView textView = findViewById(R.id.quantity_text);
        textView.setText(String.valueOf(number));
    }


    // ToDo change it later right it i'm not using it at all
    public void displayDetail(String TakeDetail) {
        TextView textView = findViewById(R.id.price_text);
        textView.setText(TakeDetail);

    }

    public String displayMessage(String message, int price, boolean hasChocolate, boolean hasCream) {

        String theDetail = message;
        theDetail = "Name: " + theDetail + "\n";
        theDetail = theDetail + "with chocolate:  " + hasChocolate + "\n";
        theDetail = theDetail + "with cream: " + hasCream + "\n";
        theDetail = theDetail + "Total is $" + price + "\n";
        theDetail = theDetail + "Thank you !!";

        return theDetail;
    }

    public void makeOrder(View view) {

        EditText myname = findViewById(R.id.edit_text);
        String tempHolder = String.valueOf(myname.getText());

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        CheckBox cream = findViewById(R.id.cream_checkbox);

        boolean hasCream = cream.isChecked();
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(coffeeQuantity, hasChocolate, hasCream);

        String detail = displayMessage(tempHolder, price, hasChocolate, hasCream);


        final String[] addresses = {"shadow8evil@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, detail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        displayDetail(detail);
    }


} // end of class

