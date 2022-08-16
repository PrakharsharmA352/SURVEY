package com.example.surveyx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Two_wheeler2 extends AppCompatActivity {
private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheeler2);
        next=(Button) findViewById(R.id.gotot3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent next=new Intent(getApplicationContext(),Two_wheeler4.class);
               startActivity(next);
            }
        });
    }
}