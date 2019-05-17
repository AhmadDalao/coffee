package com.example.android.coffeeorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // represents the coffee quantity with this variable
    int coffeeQuantity = 1;

    /*
    this application display order form to order coffee. and send the order through the email app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    } // end of onCreate

    /*
    this method will decrease the coffeeQuantity when the button subt is clicked on
    also it will make sure to show toast message to the user if they are trying to order less than 1 cup
    the coffeeQuantity is then passed to the displayQuantity() method to display the change there
     */
    public void SubtractCoffee(View view) {
        if (coffeeQuantity <= 1) {
            Toast.makeText(this, "you cant have less than 1 cup of coffee", Toast.LENGTH_LONG).show();
        } else
            coffeeQuantity = coffeeQuantity - 1;
        //calling the displayQuantity() method
        displayQuantity(coffeeQuantity);
    }

    /*
       this method will increase  the coffeeQuantity when the button add is clicked on
       also it will make sure to show toast message to the user if they are trying to order more than 100 cup
       the coffeeQuantity is then passed to the displayQuantity() method to display the change there
        */
    public void AddCoffee(View view) {
        if (coffeeQuantity >= 100) {
            Toast.makeText(this, "you cant have more than 100 cups of coffee!!", Toast.LENGTH_SHORT).show();
        } else
            coffeeQuantity = coffeeQuantity + 1;
        //calling the displayQuantity() method
        displayQuantity(coffeeQuantity);
    }

    /**
     * this method calculates the coffee price
     *
     * @param number       takes the coffeeQuantity and do the math for it
     * @param hasChocolate takes a boolean variable to calculate the price when its checked and increase
     *                     the price by 3 for each coffee cup with chocolate
     * @param hasCream     takes a boolean variable to calculate the price when its checked and increase
     *                     the price by 2 for each coffee cup with cream
     * @return it will return the price back
     */
    public int calculatePrice(int number, boolean hasChocolate, boolean hasCream) {
        int price = 5;
        if (hasChocolate && hasCream) {
            // 5 =  5 * 5 = 25
            price = (number * price);
            price = price + (3 + 2) * number;
        } else if (hasChocolate) {
            price = (number * price);
            price = price + (3 * number);

        } else if (hasCream) {
            price = (number * price);
            price = price + (2 * number);
        } else
            price = (number * price);

        return price;
    }

    /*
    this method display the coffee quantity when its decreasing or increasing
    @param number will take the coffeeQuantity and display it on the screen
     */
    public void displayQuantity(int number) {
        TextView textView = findViewById(R.id.quantity_text);
        textView.setText(String.valueOf(number));
    }

    /**
     * this method takes the details passed to it from makeOrder and display it inside price_text field
     *
     * @param TakeDetail will show the details on the screen passed to it from {@makeOrder} method
     */
    public void displayDetail(String TakeDetail) {
        TextView textView = findViewById(R.id.price_text);
        textView.setText(TakeDetail);

    }

    /**
     * this method will prepare the order summary  and return and string contains all the required information
     *
     * @param nameFromUser is the name we passed from the editText taken from makeOrder method
     * @param price        we pass from makeOrder calculated price for sure
     * @param hasChocolate is the variable we pass from makeOrder method too , it will show true next to "With chocolate"
     *                     if the checkbox been clicked on
     * @param hasCream     is the variable we pass from makeOrder method too , it will show true next to "With cream "
     *                     if the checkbox been clicked on
     * @return theDetail this String variable contains all the required information for the order summary and
     * it will be return once the method been called over
     **/
    public String displayMessage(String nameFromUser, int price, boolean hasChocolate, boolean hasCream) {
        String theDetail = nameFromUser;
        theDetail = "Name: " + theDetail + "\n";
        theDetail = theDetail + "with chocolate:  " + hasChocolate + "\n";
        theDetail = theDetail + "with cream: " + hasCream + "\n";
        theDetail = theDetail + "Total is $" + price + "\n";
        theDetail = theDetail + "Thank you !!";
        return theDetail;
    }

    /*
    this method will display the order summary and price alongside and will send the order through email
    once the button Order clicked on
     */
    public void makeOrder(View view) {
        // taking the name from the user
        EditText myname = findViewById(R.id.edit_text);
        // assigning the name from user to String variable so we can use it later on
        String NameHolder = String.valueOf(myname.getText());
        // taking object from both checkboxes available
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        CheckBox cream = findViewById(R.id.cream_checkbox);
        // making boolean variable to pass it on later and to check if the checkbox been clicked on
        boolean hasCream = cream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        //calling the method calculatePrice()
        // making price variable which will be passed later on to displayMessage() method
        //i need  assign  calculatePrice() method and pass the following the coffeeQuantity and both the boolean variables
        int price = calculatePrice(coffeeQuantity, hasChocolate, hasCream);
        //calling the method displayMessage()
        // assigning the displayMessage() method to String detail and it will rerun string contains the orderSummary
        final String detail = displayMessage(NameHolder, price, hasChocolate, hasCream);
        //calling the method displayDetail()
        // passing the String detail to be printed on the screen containing all the information : name , price , if the user wants
        //chocolate or cream it will all be shown on the screen
        displayDetail(detail);

        // send the email when button send by email is clicked on
        Button mybutton = (Button) findViewById(R.id.send);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method sendEmailHelper
                // and pass the detail variable to it
                sendEmailHelper(detail);
            }
        });
    }


    /**
     * this method will handle sending an email when clicked
     *
     * @param detail will take the information needed and pass it as a text in the email
     */
    public void sendEmailHelper(String detail) {
        // to send the order through the email when makeOrder is clicked on
        final String[] addresses = {"shadow8evil@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your coffee order \n");
        intent.putExtra(Intent.EXTRA_TEXT, detail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

} // end of class

