package com.example.chooseyouradventurev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }

        });
    }
}
