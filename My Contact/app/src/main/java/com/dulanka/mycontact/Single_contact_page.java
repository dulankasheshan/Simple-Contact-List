package com.dulanka.mycontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Single_contact_page extends AppCompatActivity {

    ImageButton backButton;
    ImageView imageView;
    TextView nameTxt,numberTxt,numberTxt2,addressTxt,emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_contact_page);

        imageView = findViewById(R.id.image);
        nameTxt = findViewById(R.id.name);
        numberTxt = findViewById(R.id.number);
        numberTxt2 = findViewById(R.id.number2);
        addressTxt = findViewById(R.id.address);
        emailTxt = findViewById(R.id.email);


        Intent intent = getIntent();

        String name = intent.getStringExtra("NAME");
        String number = intent.getStringExtra("NUMBER");
        String address = intent.getStringExtra("ADDRESS");
        String email = intent.getStringExtra("EMAIL");
        int image = intent.getIntExtra("IMAGE", R.drawable.b);



        imageView.setImageResource(image);
        nameTxt.setText(name);
        numberTxt.setText(number);
        numberTxt2.setText(number);
        addressTxt.setText(address);
        emailTxt.setText(email);




    }
}