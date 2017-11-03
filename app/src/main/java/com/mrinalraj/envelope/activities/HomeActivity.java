package com.mrinalraj.envelope.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mrinalraj.envelope.R;
import com.mrinalraj.envelope.adapters.PostAdapter;
import com.mrinalraj.envelope.adapters.PostStructure;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    private RecyclerView postContainer;
    private List<PostStructure> postStructureList = new ArrayList<>();
    private PostAdapter pAdapter;
    private View blank2;
    private FloatingActionButton toTop;
    private int previousTotal = 5;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private EditText commentText;
    private LinearLayout writepost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("");
        android.support.v7.widget.Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        searchView = (MaterialSearchView) findViewById(R.id.searchView);
        searchView.setVoiceSearch(true);

        blank2 = (View) findViewById(R.id.blankview2);
        toTop =  (FloatingActionButton) findViewById(R.id.toTop);
        writepost = (LinearLayout) findViewById(R.id.writepost);

        writepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,WritePost.class));
                overridePendingTransition(R.anim.enter_write_post,R.anim.exit_write_post);
            }
        });

        postContainer = (RecyclerView) findViewById(R.id.postContainer);
        pAdapter = new PostAdapter(postStructureList, new PostAdapter.PostAdapterListener() {
            @Override
            public void onLikeClicked(View v, int position) {
                if(postStructureList.get(position).getLikeimg() == R.drawable.like){
                    postStructureList.get(position).setLikeimg(R.drawable.liked);
                    postStructureList.get(position).incLikes();
                    pAdapter.notifyDataSetChanged();
                }
                else{
                    postStructureList.get(position).setLikeimg(R.drawable.like);
                    postStructureList.get(position).decLikes();
                    pAdapter.notifyDataSetChanged();
                }
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCommentClicked(View v, int position) {
                showCommentBox(v,position);
            }

            @Override
            public void onExtendedMenuClicked(View v, int position) {

                PopupMenu menu = new PopupMenu(HomeActivity.this , v);
                menu.getMenuInflater().inflate(R.menu.popup_menu_post,menu.getMenu());

                menu.getMenu().getItem(1).setVisible(false);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.report :
                                break;
                            case R.id.settings:
                                break;
                            case R.id.delete:
                                break;
                        }
                        return false;
                    }
                });

                menu.show();
            }
        });

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //mLayoutManager.setReverseLayout(true);
        //mLayoutManager.setStackFromEnd(true);
        postContainer.setLayoutManager(mLayoutManager);
        postContainer.setItemAnimator(new DefaultItemAnimator());
        postContainer.setAdapter(pAdapter);

        dataPrepare();

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutManager.smoothScrollToPosition(postContainer,null,0);
            }
        });


        postContainer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    Toast.makeText(HomeActivity.this, "end"+postStructureList.get(postStructureList.size()-1).getID(), Toast.LENGTH_SHORT).show();
                    dataPrepare();
                    // Do something

                    loading = true;
                }
            }
        });

    }


    private void dataPrepare(){
        String commentsArray[] = {"Comment 1","Comment2"};
        PostStructure postStructure = new PostStructure("23:11","02-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "40","789098",R.drawable.like,commentsArray);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","03-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "22", "40","789098",R.drawable.like,null);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","04-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "29", "40","789098",R.drawable.like,commentsArray);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","05-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "32", "40","789098",R.drawable.like,null);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","06-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "40","789098",R.drawable.like,null);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","07-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "40","789098",R.drawable.like,null);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","08-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "40","789098",R.drawable.like,null);
        postStructureList.add(postStructure);
        postStructure = new PostStructure("23:11","09-oct-2014","Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.", "20", "40","789098",R.drawable.like,null);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showCommentBox(View v,int position){
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.homeScreenLayout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popView = inflater.inflate(R.layout.comment_window,null);

        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.commentBg)));
        popupWindow.setElevation(10);

        ImageView likesicon = (ImageView) popView.findViewById(R.id.likesicon);
        likesicon.setImageResource(postStructureList.get(position).getLikeimg());
        TextView likes = (TextView) popView.findViewById(R.id.likescountcomment);
        likes.setText(postStructureList.get(position).getPost()[3]);
        TextView textView = (TextView) popView.findViewById(R.id.no_comment_text);
        textView.setText("No comments yet \nStart something fresh");

        if(Integer.valueOf(postStructureList.get(position).getPost()[4]) == 0){
            textView.setVisibility(View.GONE);
        }

        popupWindow.setAnimationStyle(R.style.comment);

        commentText = (EditText) popView.findViewById(R.id.commentstext);
        commentText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(HomeActivity.this, commentText.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        popupWindow.showAtLocation(mainLayout, Gravity.CENTER,0,0);


    }
}
