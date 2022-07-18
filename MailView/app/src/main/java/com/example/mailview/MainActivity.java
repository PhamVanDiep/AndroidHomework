package com.example.mailview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MailModel> mailModels;
    List<Drawable> listBackgrounds;
    List<String> hourExtensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // changeAttribute();
    }


    public void changeAttribute(){
//        TextView textView = findViewById(R.id.textView1);
//        textView.setText("Hello World");
//        textView.setTextColor(Color.BLACK);
//        textView.setTextSize(32);
//        textView.setTextAppearance(R.style.);
    }
}