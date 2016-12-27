package qerlly.cocktailboost.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import qerlly.cocktailboost.R;
import qerlly.cocktailboost.adapter.Item;
import qerlly.cocktailboost.adapter.RecyclerViewAdapter;
import qerlly.cocktailboost.db.DatabaseHelper;
import qerlly.cocktailboost.utils.ThemeSwitcher;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private DatabaseHelper mDBHelper;
    private ArrayList<Item> mArrayList = new ArrayList<Item>();
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeSwitcher().onSwitch(this);
        setContentView(R.layout.favorites_activity);
        inputDB();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.favorites);
        mToolbar.setLogo(R.drawable.cocktail_fav);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_favorites);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(this, mArrayList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void inputDB() {
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.checkAndCopyDB();
            mDBHelper.openDB();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            mCursor = mDBHelper.QueryData("select * from " + DatabaseHelper.TABLE);
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    do {
                        Item mItem = new Item();
                        mItem.setName(mCursor.getString(1));
                        mItem.setDetails(mCursor.getString(2));
                        mArrayList.add(mItem);
                    } while (mCursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}


