package com.example.swornim.rachit;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swornim on 6/30/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private List<UserDatabaseInformation> postdata=new ArrayList<>();
    private static MediaPlayer mediaPlayer;
    private Activity activity;

    public RecycleViewAdapter(Activity activity, List<UserDatabaseInformation> postdata) {
        this.postdata = postdata;
        this.activity = activity;
    }

    @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.firebaseplaytemplate,parent,false);
         TextView firebaseSongDisplay = (TextView) mView.findViewById(R.id.firebaseSongDisplay);
         TextView firebaseUploaderName = (TextView) mView.findViewById(R.id.firebaseUploaderName);

        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.firebaseSongName.setText(postdata.get(position).getUploadingSongName());


        holder.firebaseplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView firebaseSongDisplay = (TextView) activity.findViewById(R.id.firebaseSongDisplay);
                TextView firebaseUploaderName = (TextView) activity.findViewById(R.id.firebaseUploaderName);

                firebaseSongDisplay.setText(postdata.get(position).getUploadingSongName());
                firebaseUploaderName.setText("Uploaded By: " + postdata.get(position).getUploaderUserName());



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


    }

    @Override
    public int getItemCount() {
        return postdata.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView firebaseplay;
        private TextView firebaseSongName;


        public MyViewHolder(final View itemView) {
            super(itemView);



            firebaseSongName = (TextView) itemView.findViewById(R.id.firebaseSongName);
            firebaseplay=(ImageView) itemView.findViewById(R.id.firebaseplay);


        }
    }

    public void setfiler(List<UserDatabaseInformation> newlist){

        postdata=new ArrayList<>();
        postdata.addAll(newlist);
        notifyDataSetChanged();
    }


}
