package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button view, entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
        entry = findViewById(R.id.entry);
        int net = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (net != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, net);
            Toast.makeText(MainActivity.this, "Camera Permission is required for this app to run", Toast.LENGTH_SHORT)
                    .show();

        }

        int net1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        if (net1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, net1);
            Toast.makeText(MainActivity.this, "Camera Permission is required for this app to run", Toast.LENGTH_SHORT)
                    .show();

        }
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, com.example.myrestaurant.entry.class);
                startActivity(a);
                finish();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, com.example.myrestaurant.Tableview.class);
                startActivity(a);
                finish();
            }
        });
    }
}