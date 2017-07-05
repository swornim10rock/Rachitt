package com.example.swornim.rachit;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu,menu);
//        MenuItem menuItem=menu.findItem(R.id.menuId);
//
//        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.getFilter().filter(s);
//                return false;
//            }
//        });


        return super.onCreateOptionsMenu(menu);
    }


    public class RenderCustomAdapterPlay extends ArrayAdapter<UserDatabaseInformation> {

        private List<UserDatabaseInformation> postdata=new ArrayList<>();

        public RenderCustomAdapterPlay(Context context, List<UserDatabaseInformation> postdata) {
            super(context, R.layout.firebaseplaytemplate,postdata);
            this.postdata=postdata;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View mView=convertView;
            if (convertView==null){

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                mView = layoutInflater.inflate(R.layout.firebaseplaytemplate, parent, false);
            }

            return mView;
        }
    }


}
