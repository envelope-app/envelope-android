package com.mrinalraj.envelope.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.adapters.PostAdapter;
import com.mrinalraj.envelope.adapters.PostStructure;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Bundle b;
    MaterialSearchView searchView;
    private RecyclerView postContainer;
    private List<PostStructure> postStructureList = new ArrayList<>();
    private PostAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        postContainer = (RecyclerView) findViewById(R.id.postContainer);
        pAdapter = new PostAdapter(postStructureList, new PostAdapter.PostAdapterListener() {
            @Override
            public void onLikeClicked(View v, int position) {
                Toast.makeText(HomeActivity.this,postStructureList.get(position).getPost()[1], Toast.LENGTH_SHORT).show();
            }
            public void onDislikeClicked(View v, int position){
                Toast.makeText(HomeActivity.this, "comment for post"+ postStructureList.get(position).getID(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClicked(View v, int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        postContainer.setLayoutManager(mLayoutManager);
        postContainer.setItemAnimator(new DefaultItemAnimator());
        postContainer.setAdapter(pAdapter);

        dataPrepare();

        setTitle("");
        android.support.v7.widget.Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        searchView = (MaterialSearchView) findViewById(R.id.searchView);
        searchView.setVoiceSearch(true);

    }

    private void dataPrepare(){
        PostStructure postStructure = new PostStructure("23:11","02-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","03-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","04-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","05-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","06-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","07-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","08-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","09-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "1","40","789098");
        postStructureList.add(postStructure);

        pAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
