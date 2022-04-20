package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class entry extends AppCompatActivity {
    EditText tableno , dish1,dish2,dish3,dish4;
    TextView orderno;
    Button enter,view;
    public int no1 , no2;
    public  String d1,d2,d3,d4;
    ArrayList<mydata> mydatalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        orderno = findViewById(R.id.orderno);
        tableno = findViewById(R.id.tableno);
        dish1=findViewById(R.id.dish1);
        dish2=findViewById(R.id.dish2);
        dish3=findViewById(R.id.dish3);
        dish4=findViewById(R.id.dish4);
        enter = findViewById(R.id.enter);
        view = findViewById(R.id.view);
        Intent a = new Intent(entry.this,Tableview.class);
        loadData();
        int order_no = mydatalist.size() + 1;
        orderno.setText(Integer.toString(order_no));
        orderno.setTextColor(Color.parseColor("#E8630A"));

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                no2 = Integer.parseInt(tableno.getText().toString());
                d1 = dish1.getText().toString();
                d2 = dish2.getText().toString();
                d3 = dish3.getText().toString();
                d4 = dish4.getText().toString();
                 if(no2 == 0){
                    Toast.makeText(entry.this,"table no cant be empty",Toast.LENGTH_SHORT).show();
                }
                else if(d1.isEmpty()){
                    Toast.makeText(entry.this,"Atleast 1 dish should be ordered",Toast.LENGTH_SHORT).show();
                }
                else{
                    mydatalist.add(new mydata(no1,no2,d1,d2,d3,d4));
                    saveData();
                    Toast.makeText(entry.this,"data stored"+mydatalist.size(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(a);
                finish();
            }
        });

    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mydatalist);
        editor.putString("data list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("data list", null);
        Type type = new TypeToken<ArrayList<mydata>>() {}.getType();
        mydatalist = gson.fromJson(json, type);
        if (mydatalist == null) {
            mydatalist = new ArrayList<>();
        }
    }
}