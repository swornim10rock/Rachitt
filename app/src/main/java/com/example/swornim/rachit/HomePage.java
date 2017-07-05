package com.example.swornim.rachit;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private ArrayAdapter<UserDatabaseInformation> adapter;
    private List<UserDatabaseInformation> postdata=new ArrayList<>();
    private ListView listView;
    private FragmentTransaction fragmentTransaction;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        imageView1=(ImageView) findViewById(R.id.homepagetemplateDetais1);
        imageView2=(ImageView) findViewById(R.id.homepagetemplateDetais2);
        imageView3=(ImageView) findViewById(R.id.homepagetemplateDetais3);
        imageView4=(ImageView) findViewById(R.id.homepagetemplateDetais4);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,FirebasePlayFragment.class));

            }
        });

             imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,FirebasePlayFragment.class));

            }
        });

             imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomePage.this,FirebasePlayFragment.class));

            }

        });
             imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,FirebasePlayFragment.class));


            }
        });
        getSupportActionBar().setTitle("Home Page");



    }

}
