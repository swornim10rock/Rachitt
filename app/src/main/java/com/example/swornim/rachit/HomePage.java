package com.example.swornim.rachit;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private ArrayAdapter<UserDatabaseInformation> adapter;
    private List<UserDatabaseInformation> postdata=new ArrayList<>();
    private ListView listView;
    private FragmentTransaction fragmentTransaction;
    private ImageView rockImage;
    private ImageView classicalRockImage;
    private ImageView melodyImage;
    private ImageView bluesImage;
    private DatabaseReference mDatabaseReferences;
    UserDatabaseInformation filterDataObject=new UserDatabaseInformation();
    private ImageView all;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        all=(ImageView) findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,FirebasePlayFragment.class));
            }
        });

        mDatabaseReferences = FirebaseDatabase.getInstance().getReference("users/");
        mDatabaseReferences.child("musicnap/songlist/").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserDatabaseInformation messageObject = dataSnapshot.getValue(UserDatabaseInformation.class);
                postdata.add(messageObject);
                Log.i("mytag",dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rockImage=(ImageView) findViewById(R.id.rockImage);
        classicalRockImage=(ImageView) findViewById(R.id.classicalRockImage);
        melodyImage=(ImageView) findViewById(R.id.melodyImage);
        bluesImage=(ImageView) findViewById(R.id.bluesImage);


        rockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFilterActivity("rock");
            }
        });

             classicalRockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callFilterActivity("classical rock");

            }
        });

             bluesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callFilterActivity("love");

            }

        });
             melodyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callFilterActivity("melody");

            }
        });
        getSupportActionBar().setTitle("Home Page");



    }


    private void  filterData(String type){

        List<UserDatabaseInformation> tempList=new ArrayList<>();
        for(int i=0;i<postdata.size();i++){

            if(postdata.get(i).getType().equals(type)){
                Log.i("mytag",postdata.get(i).getType());
                tempList.add(postdata.get(i));
            }
        }
        filterDataObject.setFilteredData(tempList);
    }

    private void callFilterActivity(String type){
        if(postdata.size()>0) {
            filterData(type);

            Intent intent = new Intent(HomePage.this, MyFavourite.class);
            intent.putExtra("messageObject", filterDataObject);
            startActivity(intent);
            //send filtered data object
        }else
            Toast.makeText(getApplicationContext(),"Loading Please wait...",Toast.LENGTH_LONG).show();

    }



}
