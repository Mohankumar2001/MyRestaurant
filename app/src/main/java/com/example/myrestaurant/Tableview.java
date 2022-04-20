package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Tableview extends AppCompatActivity {
    TableLayout t1;
    ArrayList<mydata> mydatalist;
    Button bck,del;
    EditText in ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableview);
        loadData();
         t1 = (TableLayout) findViewById(R.id.mytable);
        setview(t1);
        bck = findViewById(R.id.bck);
        del = findViewById(R.id.del);
        in = findViewById(R.id.in);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Tableview.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delno = Integer.parseInt(in.getText().toString());
                if(delno == 0){
                    Toast.makeText(Tableview.this, "cant be empty", Toast.LENGTH_SHORT).show();
                }
                mydatalist.remove(delno-1);
                saveData();
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
    }

    private void setview(TableLayout t1) {
        String order_no,table_no,d1,d2,d3,d4;
        int n = mydatalist.size();
        for (int i = -1; i < n; i++) {

            if(i== -1){
                 order_no = "Order No";
                 table_no = "Table No";
                 d1 = "Dish 1";
                 d2 = "Dish 2";
                 d3 = "Dish 3";
                 d4 = "Dish 4";
            }
            else {
                mydata temp = mydatalist.get(i);
                 order_no = Integer.toString(i + 1);
                 table_no = Integer.toString(temp.gettno());
                 d1 = temp.getd1();
                 d2 = temp.getd2();
                 d3 = temp.getd3();
                 d4 = temp.getd4();
            }

                TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                tableRowParams.setMargins(5, 5, 5, 5);
                TableRow newRow = new TableRow(this);
                newRow.setPadding(5, 5, 5, 5);

                TextView column1 = new TextView(this);
                TextView column2 = new TextView(this);
                TextView column3 = new TextView(this);
                TextView column4 = new TextView(this);
                TextView column5 = new TextView(this);
                TextView column6 = new TextView(this);

                column1.setText(order_no);
                column1.setPadding(5, 5, 5, 5);
                column1.setTextColor(Color.parseColor("#E8630A"));
                column1.setTextSize(20);

                column2.setText(table_no);
                column2.setPadding(5, 5, 5, 5);
                column2.setTextColor(Color.parseColor("#E8630A"));
                column2.setTextSize(20);

                column3.setText(d1);
                column3.setPadding(5, 5, 5, 5);
                column3.setTextColor(Color.parseColor("#E8630A"));
                column3.setTextSize(20);

                column4.setText(d2);
                column4.setPadding(5, 5, 5, 5);
                column4.setTextColor(Color.parseColor("#E8630A"));
                column4.setTextSize(20);

                column5.setText(d3);
                column5.setPadding(5, 5, 5, 5);
                column5.setTextColor(Color.parseColor("#E8630A"));
                column5.setTextSize(20);

                column6.setText(d4);
                column6.setPadding(5, 5, 5, 5);
                column6.setTextColor(Color.parseColor("#E8630A"));
                column6.setTextSize(20);

                newRow.addView(column1);
                newRow.addView(column2);
                newRow.addView(column3);
                newRow.addView(column4);
                newRow.addView(column5);
                newRow.addView(column6);


                t1.addView(newRow, tableRowParams);
            }

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
        else{
            Toast.makeText(Tableview.this, "data present" ,Toast.LENGTH_LONG ).show();
        }
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mydatalist);
        editor.putString("data list", json);
        editor.apply();
    }
}