package com.howaboutthis.satyaraj.hollywood;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LoaderManager.LoaderCallbacks<List<ItemEvent>>, View.OnClickListener
{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<ItemEvent> mListItem;
    ProgressBar progressBar;
    private String NEWS_REQUEST_URL;
    private int mLoader = 0 ;

    private LinearLayoutManager mLayoutManager;
    Parcelable state;
    String mShow = "movie";
    String mSort;
    int mGenre ;

    private Animation fab_open,fab_close;
    private com.github.clans.fab.FloatingActionButton fab1, fab2, fab3, fab4;
    private com.github.clans.fab.FloatingActionMenu fab;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        final Spinner categories = findViewById(R.id.categories);

        progressBar = findViewById(R.id.progressBar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories, R.layout.spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categories.setAdapter(adapter);
        mListItem = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.my_custom_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);



        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String mCategories = categories.getSelectedItem().toString();


                if(Objects.equals(mCategories, "All Categories")){
                       if (Objects.equals(mShow, "movie")) {
                            NEWS_REQUEST_URL = "https://api.themoviedb.org/3/discover/movie?api_key=6895eb2d267f0a04a18aede18213d210&language=en-US&sort_by=vote_count.desc&include_adult=false&include_video=false&page=1";
                        } else {
                            NEWS_REQUEST_URL = "https://api.themoviedb.org/3/discover/tv?api_key=6895eb2d267f0a04a18aede18213d210&language=en-US&sort_by=vote_count.desc&include_adult=false&include_video=false&page=1";
                        }
                       mSort = "vote_count.desc";
                       mGenre = 0;
                       setLoader(0);
                    }

                else if(Objects.equals(mCategories, "Action")){
                    if(Objects.equals(mShow,"movie"))
                    setRequestUrl("movie",mSort,28,1);
                    else
                        setRequestUrl("tv",mSort,28,1);
                    setLoader(1);
                    mGenre = 28;
                }
                else if (Objects.equals(mCategories, "Adventure")){
                    if(Objects.equals(mShow,"movie"))
                        setRequestUrl("movie",mSort,12,1);
                    else
                        setRequestUrl("tv",mSort,12,1);

                    setLoader(2);
                    mGenre = 12;
                }
                else if(Objects.equals(mCategories, "Animation")){
                    if(Objects.equals(mShow,"movie"))
                        setRequestUrl("movie",mSort,16,1);
                    else
                        setRequestUrl("tv",mSort,16,1);

                    setLoader(3);
                    mGenre = 16;
                }
                else if(Objects.equals(mCategories, "Comedy")){
                    if(Objects.equals(mShow,"movie"))
                    setRequestUrl("movie",mSort,35,1);
                    else
                        setRequestUrl("tv",mSort,35,1);

                    setLoader(4);
                    mGenre = 35;
                }
                else if(Objects.equals(mCategories, "Crime")){
                    if (Objects.equals(mShow,"movie"))
                    setRequestUrl("movie",mSort,80,1);
                    else
                        setRequestUrl("tv",mSort,80,1);

                    setLoader(5);
                    mGenre = 80;
                }
                else if(Objects.equals(mCategories, "Documentary")){
                    if (Objects.equals(mShow,"movie"))
                        setRequestUrl("movie",mSort,99,1);
                    else
                        setRequestUrl("tv",mSort,99,1);
                    setLoader(6);
                    mGenre = 99;
                }
                else if(Objects.equals(mCategories, "Drama")){
                    if (Objects.equals(mShow,"movie"))
                        setRequestUrl("movie",mSort,18,1);
                    else
                        setRequestUrl("tv",mSort,18,1);

                    setLoader(7);
                    mGenre = 18;
                }
                else if(Objects.equals(mCategories, "Family")){
                    if (Objects.equals(mShow,"movie"))
                        setRequestUrl("movie",mSort,10751,1);
                    else
                        setRequestUrl("tv",mSort,10751,1);
                    setLoader(8);
                    mGenre = 10751;
                }
                else if(Objects.equals(mCategories, "Fantasy")){
                    if(Objects.equals(mShow, "movie"))
                    setRequestUrl("movie",mSort,14,1);
                    else
                        setRequestUrl("tv",mSort,14,1);
                    setLoader(9);
                    mGenre = 14;
                }
                else if(Objects.equals(mCategories, "History")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,36,1);
                    else
                        setRequestUrl("tv",mSort,36,1);

                    setLoader(10);
                    mGenre = 36;
                }
                else if(Objects.equals(mCategories, "Horror")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,27,1);
                    else
                        setRequestUrl("tv",mSort,27,1);

                    setLoader(11);
                    mGenre = 27;
                }
                else if(Objects.equals(mCategories, "Music")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,10402,1);
                    else
                        setRequestUrl("tv",mSort,10402,1);

                    setLoader(12);
                    mGenre = 10402;
                }
                else if(Objects.equals(mCategories, "Mystery")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,9648,1);
                    else
                        setRequestUrl("tv",mSort,9648,1);

                    setLoader(13);
                    mGenre = 9648;
                }
                else if(Objects.equals(mCategories, "Romance")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,10749,1);
                    else
                        setRequestUrl("tv",mSort,10749,1);
                    setLoader(14);
                    mGenre = 10749;
                }
                else if(Objects.equals(mCategories, "Sci-Fi")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie",mSort,878,1);
                    else
                        setRequestUrl("tv",mSort,878,1);

                    setLoader(15);
                    mGenre = 878;
                }
                else if(Objects.equals(mCategories, "TV Movie")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie", mSort,10770,1);
                    else
                        setRequestUrl("tv", mSort,10770,1);

                    setLoader(16);
                    mGenre = 10770;
                }
                else if(Objects.equals(mCategories, "Thriller")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie", mSort,53,1);
                    else
                        setRequestUrl("tv", mSort,53,1);

                    setLoader(17);
                    mGenre = 53;
                }
                else if(Objects.equals(mCategories, "War")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie", mSort,10752,1);
                    else
                        setRequestUrl("tv", mSort,10752,1);
                    setLoader(18);
                    mGenre = 10752;
                }
                else if(Objects.equals(mCategories, "Western")){
                    if(Objects.equals(mShow, "movie"))
                        setRequestUrl("movie", mSort,37,1);
                    else
                        setRequestUrl("tv", mSort,37,1);
                    setLoader(19);
                    mGenre = 37;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("");

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab4 = findViewById(R.id.fab4);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);


        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if ((dy > 0) && (fab.getVisibility() == View.VISIBLE)) {

                        if (fab.isOpened())
                            fab.close(true);
                        fab.startAnimation(fab_open);
                        fab.setClickable(true);
                    } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                        if (fab.isOpened())
                            fab.close(true);
                        fab.startAnimation(fab_close);
                        fab.setClickable(false);

                    }
                }
            });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);



        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (null != searchView) {
            //noinspection ConstantConditions
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                mLoader++;
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                NEWS_REQUEST_URL = "https://api.themoviedb.org/3/search/movie?api_key=6895eb2d267f0a04a18aede18213d210&query="+query;
                setLoader(mLoader);
                return true;
            }

        };

        if (searchView != null) {
            searchView.setOnQueryTextListener(queryTextListener);
        }



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this,aboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.movies ){
            mLoader++;
            mShow = "movie";
            setRequestUrl("movie","vote_count.desc",0,1);
            setLoader(mLoader);
            mGenre = 0;

        }
        else if (id == R.id.tv_shows){
            mLoader++;
            mShow = "tv";
            setRequestUrl("tv","vote_count.desc",0,1);
            setLoader(mLoader);
            mGenre = 0;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<List<ItemEvent>> onCreateLoader(int i, Bundle bundle) {
        Log.d(String.valueOf(this),"Loader");
        state = null;

        progressBar.setVisibility(View.VISIBLE);
        return new hollywoodLoader(this, NEWS_REQUEST_URL, mShow);
    }

    @Override
    public void onLoadFinished(Loader<List<ItemEvent>> loader, List<ItemEvent> list) {
        Log.d(String.valueOf(this), "onFinished");

        progressBar.setVisibility(View.GONE);


        if (state != null)
            mLayoutManager.onRestoreInstanceState(state);
        else {
            mListItem.clear();
            mListItem.addAll(list);
            mAdapter = new MoviesAdapter(mListItem, this, fab);
            mRecyclerView.setAdapter(mAdapter);
            mLayoutManager.onRestoreInstanceState(null);

        }
    }
    @Override
    public void onLoaderReset(Loader<List<ItemEvent>> loader) {
    }

    public void setRequestUrl (String show , String sort, int genre, int page){

        mListItem.clear();
        mAdapter.notifyDataSetChanged();
        if ( genre == 0) {
            NEWS_REQUEST_URL = "https://api.themoviedb.org/3/discover/"+show+"?api_key=6895eb2d267f0a04a18aede18213d210&language=en-US&sort_by=" + sort + "&include_adult=false&include_video=false&page=" + page;
        }
        else {
            NEWS_REQUEST_URL = "https://api.themoviedb.org/3/discover/"+show+"?api_key=6895eb2d267f0a04a18aede18213d210&language=en-US&sort_by=" + sort + "&include_adult=false&include_video=false&page=" + page + "&with_genres=" + genre;
        }

    }

    public void setLoader( int loader) {

        if (isConnected())
        {
            mRecyclerView.setVisibility(View.VISIBLE);

            getLoaderManager().destroyLoader(mLoader);
        getLoaderManager().initLoader(loader, null,MainActivity.this);
        mLoader = loader;
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);

            View emptyView = findViewById(R.id.empty_view);
            emptyView.setVisibility(View.VISIBLE);

        }
        }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.fab1:
                fab.close(true);
                mSort = "popularity.desc";
                setRequestUrl(mShow,"popularity.desc",mGenre,1);
                setLoader(100);
                break;

            case R.id.fab2:
                fab.close(true);
                mSort = "vote_average.desc";
                setRequestUrl(mShow,"vote_average.desc",mGenre,1);
                setLoader(101);
                break;

            case R.id.fab3:
                fab.close(true);
                mSort = "release_date.desc";
                setRequestUrl(mShow,"release_date.asc",mGenre,1);
                setLoader(102);
                break;

            case R.id.fab4:
                fab.close(true);
                mSort = "revenue.desc";
                setRequestUrl(mShow,"revenue.desc",mGenre,1);
                setLoader(103);
                break;
        }
    }

@Override
    protected void onPause(){
        super.onPause();
    state = mLayoutManager.onSaveInstanceState();
    }

    public void changePage(String buttonText){
        int page = Integer.parseInt(buttonText);
        setRequestUrl(mShow,mSort,mGenre,page);
        setLoader(page);

    }

    @SuppressWarnings("ConstantConditions")
    public boolean isConnected(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //noinspection ConstantConditions
        @SuppressWarnings("ConstantConditions") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isWifi = false;
        boolean isMobile = false;
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
        {
            isWifi = activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            isMobile = activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return isMobile || isWifi;
    }
}
