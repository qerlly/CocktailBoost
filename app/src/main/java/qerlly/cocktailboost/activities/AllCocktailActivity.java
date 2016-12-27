package qerlly.cocktailboost.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import qerlly.cocktailboost.R;
import qerlly.cocktailboost.adapter.Item;
import qerlly.cocktailboost.adapter.OnItemClickListener;
import qerlly.cocktailboost.adapter.RecyclerViewAdapter;
import qerlly.cocktailboost.db.DatabaseHelper;
import qerlly.cocktailboost.utils.ThemeSwitcher;

public class AllCocktailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private DatabaseHelper mDBHelper;
    private ArrayList<Item> mArrayList = new ArrayList<>();
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ThemeSwitcher().onSwitch(this);
        setContentView(R.layout.all_activity);
        inputDB();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.all);
        mToolbar.setLogo(R.drawable.cocktail_all);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_all);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new RecyclerViewAdapter(this, mArrayList);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
               /* Intent intent = new Intent(AllCocktailActivity.this, DetailCocktail.class);
                intent.putExtra(DetailCocktail.EXTRA_PARAM_ID, position);
                startActivity(intent);*/
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_row:
                item.setChecked(true);
                mStaggeredLayoutManager.setSpanCount(1);
                return true;
            case R.id.view_grid:
                item.setChecked(true);
                mStaggeredLayoutManager.setSpanCount(2);
                return true;
            case R.id.all_category:
                return true;
            case R.id.alcohol_category:
                return true;
            case R.id.no_alcohol_category:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem mCategoryItem = menu.findItem(R.id.category);
        MenuItem mAllItem = menu.findItem(R.id.all_category);
        MenuItem mAlcoholItem = menu.findItem(R.id.alcohol_category);
        MenuItem mNonAlcoholItem = menu.findItem(R.id.no_alcohol_category);

        mAllItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mCategoryItem.setIcon(R.drawable.toast);
                return false;
            }
        });

        mAlcoholItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mCategoryItem.setIcon(R.drawable.whiskey);
                return false;
            }
        });

        mNonAlcoholItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mCategoryItem.setIcon(R.drawable.lemon_juice);
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
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
                        mItem.setRecipe(mCursor.getString(3));
                        mArrayList.add(mItem);
                    } while (mCursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}



