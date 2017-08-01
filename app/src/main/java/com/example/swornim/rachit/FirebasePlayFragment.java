package com.example.swornim.rachit;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swornim on 5/23/2017.
 */

public class FirebasePlayFragment extends AppCompatActivity {
    private ListView firebaseListView;
//    private ArrayAdapter<UserDatabaseInformation> adapter;
    private static MediaPlayer mediaPlayer;
    private DatabaseReference mDatabaseReferences;

    private RecycleViewAdapter recyclerViewadapter;
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private List<UserDatabaseInformation> postdata=new ArrayList<>();
    private List<UserDatabaseInformation> originalPostdataHolder=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebaseplayfragment);
//        firebaseListView = (ListView) findViewById(R.id.firebaseListView);
//        adapter = new RenderCustomAdapterPlay(getApplicationContext(), postdata);
//        firebaseListView.setAdapter(adapter);
//        firebaseListView.setTextFilterEnabled(true);

        mDatabaseReferences = FirebaseDatabase.getInstance().getReference();
        mDatabaseReferences.child("songlist/").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserDatabaseInformation messageObject = dataSnapshot.getValue(UserDatabaseInformation.class);
                postdata.add(messageObject);
                originalPostdataHolder.add(messageObject);
                Log.i("mytag",dataSnapshot.toString());
                recyclerViewadapter.notifyDataSetChanged();
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



        recycleView=(RecyclerView) findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setHasFixedSize(true);


        recyclerViewadapter=new RecycleViewAdapter(FirebasePlayFragment.this,postdata);
        recycleView.setAdapter(recyclerViewadapter);

    }

//    public class RenderCustomAdapterPlay extends ArrayAdapter<UserDatabaseInformation>  {
//
//        private Context mcontext;
//
//        private ArrayList<UserDatabaseInformation> postdata = new ArrayList<>();
//        private ArrayList<UserDatabaseInformation> filterdata = new ArrayList<>();
//
//        public RenderCustomAdapterPlay(Context context, ArrayList<UserDatabaseInformation> postdata) {
//            super(context, R.layout.firebaseplaytemplate, postdata);
//            mcontext = context;
//            this.postdata = postdata;
//            this.filterdata = postdata;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//
//            View mView = convertView;
//            if (convertView == null) {
//
//                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//                mView = layoutInflater.inflate(R.layout.firebaseplaytemplate, parent, false);
//            }
//
//            ImageView firebaseplay = (ImageView) mView.findViewById(R.id.firebaseplay);
//
//            final TextView firebaseSongName = (TextView) mView.findViewById(R.id.firebaseSongName);
//
//            firebaseSongName.setText(postdata.get(position).getUploadingSongName());
//
//            firebaseplay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    TextView firebaseSongDisplay = (TextView) findViewById(R.id.firebaseSongDisplay);
//                    TextView firebaseUploaderName = (TextView) findViewById(R.id.firebaseUploaderName);
//
//                    firebaseSongDisplay.setText(postdata.get(position).getUploadingSongName());
//                    firebaseUploaderName.setText("Uploaded By: " + postdata.get(position).getUploaderUserName());
//
//
//                    if ((mediaPlayer == null)) {
//
//
//                        //for the first time instanc creation
//                        mediaPlayer = new MediaPlayer();
//                        mediaPlayer.reset();
//                        mediaPlayer.stop();
//
//                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        try {
//                            mediaPlayer.setDataSource(postdata.get(position).getUploadingFilePath());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mediaPlayer) {
//                                mediaPlayer.start();
//
//                            }
//                        });
//                        mediaPlayer.prepareAsync();
//                    } else {
//                        ;
//                        //resets previous playing mediaplayer
//                        mediaPlayer.reset();
//                        mediaPlayer.stop();
//
//                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        try {
//                            mediaPlayer.setDataSource(postdata.get(position).getUploadingFilePath());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mediaPlayer) {
//                                mediaPlayer.start();
//
//                            }
//                        });
//                        mediaPlayer.prepareAsync();
//
//                    }
//
//                }
//            });
//
//
//            return mView;
//        }
//
//        @NonNull
//        @Override
//        public Filter getFilter() {
//            return new Filter() {
//                @Override
//                protected FilterResults performFiltering(CharSequence charSequence) {
//                    FilterResults filterResults = new FilterResults();
//
//
//                    //if users types something
//                    if (charSequence != null && charSequence.length() > 0) {
//                        charSequence = charSequence.toString().toLowerCase(); //convert typed text to lowercase
//                        //to make the search easy tp compare
//                        ArrayList<UserDatabaseInformation> filterPost=new ArrayList<>();//temporary items holder
//
//                        //filter data is whole lists of songs
//                        for (int i=0;i<filterdata.size();i++) {
//                            if(filterdata.get(i).getUploadingSongName().toLowerCase().contains(charSequence)){
//                                filterPost.add(filterdata.get(i));//add that particular object
//                            }
//                        }
//                        filterResults.count=filterPost.size();
//                        filterResults.values=filterPost;
//                    }else{
//                        filterResults.count=filterdata.size();
//                        filterResults.values=filterdata;
//                    }
//
//                    return filterResults;//it then calls pubklish results method
//                }
//
//                @Override
//                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//
//                      postdata=(ArrayList<UserDatabaseInformation>) filterResults.values;
//
//                    if(postdata!=null){
//                        notifyDataSetChanged(); //display the searched items
//                    }
//                }
//            };
//        }
//    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu, menu);
            MenuItem menuItem = menu.findItem(R.id.menuId);

            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
             ImageView cancelImage = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);

            cancelImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewadapter.reloadOriginalData(originalPostdataHolder);

                    Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String searching) {

                    //search algorithm
                    searching=searching.toLowerCase();
                    List<UserDatabaseInformation> newList=new ArrayList<>();
                    String matched="nope";//intitial flag
                    for(UserDatabaseInformation each:postdata){

                        String eachString=each.getUploadingSongName().toLowerCase();
                        for(int i=0;i<eachString.length();i++){

                            for(int j=i;j<searching.length();j++){
                                if(eachString.charAt(j)==searching.charAt(j)){
                                    matched="yup";
                                }else{
                                    matched="nope";
                                    break;
                                }
                            }
                        }
                        if(matched.equals("yup"))
                            newList.add(each);
//                        if(each.getUploadingSongName().toLowerCase().contains(s)){
//                            newList.add(each);
//                        }
                    }
                    recyclerViewadapter.setfiler(newList);
                    return false;
                }
            });


            return super.onCreateOptionsMenu(menu);
        }
}



