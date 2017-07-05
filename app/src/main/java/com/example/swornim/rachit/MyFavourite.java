package com.example.swornim.rachit;

import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        mListview=(ListView) findViewById(R.id.mListviewfav);

        if(getIntent().getSerializableExtra("messageObject")!=null){
            postdataObject=(UserDatabaseInformation) getIntent().getSerializableExtra("messageObject");
            postdata=postdataObject.getFilteredData();
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

                    if ((mediaPlayer == null)) {


                        //for the first time instanc creation
                        mediaPlayer = new MediaPlayer();
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




}
