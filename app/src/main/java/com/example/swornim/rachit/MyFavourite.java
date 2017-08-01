package com.example.swornim.rachit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFavourite extends AppCompatActivity {

    private UserDatabaseInformation postdataObject;
    private ArrayAdapter<UserDatabaseInformation> adapter;
    private List<UserDatabaseInformation> postdata=new ArrayList<>();
    private ListView mListview;
    private static MediaPlayer mediaPlayer;
    private String type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);


        ImageView mPause=(ImageView)findViewById(R.id.pauseMusic);
        mPause.setImageResource(android.R.drawable.ic_media_pause);
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                Toast.makeText(getApplicationContext(),"Stopped",Toast.LENGTH_SHORT).show();

            }
        });
        mListview=(ListView) findViewById(R.id.mListviewfav);
        if(getIntent().getSerializableExtra("messageObject")!=null){
            postdataObject=(UserDatabaseInformation) getIntent().getSerializableExtra("messageObject");
            postdata=postdataObject.getFilteredData();
        }

        if(getIntent().getStringExtra("type")!=null){

            type=getIntent().getStringExtra("type");
        }

        Toast.makeText(getApplicationContext(),"called",Toast.LENGTH_LONG).show();
        adapter=new mAdapter(getApplicationContext(),postdata);
        mListview.setAdapter(adapter);
    }


    public class mAdapter extends ArrayAdapter<UserDatabaseInformation> {


        private List<UserDatabaseInformation> postdata=new ArrayList<>();//its an array list container


        public mAdapter(Context context,List<UserDatabaseInformation> postdata) {
            super(context,R.layout.myfavtemplate,postdata);
            this.postdata=postdata;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View mView = convertView;
            if(convertView==null) {

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                mView = layoutInflater.inflate(R.layout.myfavtemplate, parent, false);
            }

            ImageView mPlay=(ImageView) mView.findViewById(R.id.favplay);

            TextView favSongName=(TextView) mView.findViewById(R.id.favsongname);

            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext(),"Please Wait Streaming",Toast.LENGTH_LONG).show();

                    if ((mediaPlayer == null)) {


                        //for the first time instanc creation
                        mediaPlayer = new MediaPlayer();

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(postdata.get(position).getUploadingFilePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                                recommendationTrigger();

                            }
                        });
                        mediaPlayer.prepareAsync();
                    } else {
                        ;
                        //resets previous playing mediaplayer
                        mediaPlayer.reset();
                        mediaPlayer.stop();

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(postdata.get(position).getUploadingFilePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                                recommendationTrigger();


                            }
                        });
                        mediaPlayer.prepareAsync();

                    }
                }
            });

            favSongName.setText(postdata.get(position).getUploadingSongName());

            return mView;

        }



    }


    private void recommendationTrigger(){
        Intent resultIntent = new Intent(this, RecommendationList.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        resultIntent.putExtra("messageObject",type);


        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 10, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setContentText("You have recommedation of type "+type)
                .setContentTitle("Recommendation For You")
                .setSmallIcon(R.drawable.musicplayer);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, builder.build());
    }




}
