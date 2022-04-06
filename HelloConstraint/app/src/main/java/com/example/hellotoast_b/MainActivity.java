package com.example.hellotoast_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count;
    TextView textView;
    Button count_btn;
    Button zero_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        textView = (TextView) findViewById(R.id.textView);
        count_btn = (Button) findViewById(R.id.count_button);
        zero_btn = (Button) findViewById(R.id.zero_button);
    }

    public void showToast(View view){
        Toast.makeText(getApplicationContext(), R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void setToZero(View view){
        count = 0;
        textView.setText(Integer.toString(count));
        view.setBackgroundColor(getResources().getColor(R.color.init_zero_color));
        count_btn.setBackgroundColor(getResources().getColor(R.color.init_count_color));
    }

    public void countUp(View view){
        if(count == 0) zero_btn.setBackgroundColor(getResources().getColor(R.color.zero_color));
        count ++;
        textView.setText(Integer.toString(count));
        if(count % 2 == 0){
            count_btn.setBackgroundColor(getResources().getColor(R.color.even_color));
        }else count_btn.setBackgroundColor(getResources().getColor(R.color.odd_color));
    }
}