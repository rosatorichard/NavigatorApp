package com.batchmates.android.navigator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] activities;
    private FrameLayout layout;
    private boolean save = false;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Navigator");
        layout = (FrameLayout) findViewById(R.id.mainLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.draweLayout);
        activities = getResources().getStringArray(R.array.activities);
        drawerList = (ListView) findViewById(R.id.list_View);

        //Toast toast =Toast.makeText(this,"I love Toast",Toast.LENGTH_SHORT);
        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activities));

//
//        //Testing based on video
//        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open_animation,R.string.close_animation){
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };

//        //Shows the back button at the top of the screen. Currently does nothing
//        drawerLayout.setDrawerListener(toggle);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerList.setOnItemClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_indicator);

    }

    //needed for overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.overflow_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);


    }


    //for menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.circlePuzzle:
                //play a puzzle game
                Intent intent = new Intent(this, ThreeByThree.class);
                startActivity(intent);
                Log.d("Play a Puzzle", "onOptionsItemSelected: ");
                return true;

            case R.id.settings:

                if (save == true) {
                    Snackbar snack3 = Snackbar.make(drawerLayout, "Evil has been Vanquished", Snackbar.LENGTH_SHORT);
                    snack3.show();
                    layout.setBackgroundResource(R.drawable.ill_save_you);
                    Toast.makeText(this, "Ill SAVE YA LADDY", Toast.LENGTH_LONG).show();
                    save=false;
                }
                else
                {
                    Toast.makeText(this, "You dont need saving", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.aboutUs:
                Snackbar snack = Snackbar.make(drawerLayout, "We love snacks", Snackbar.LENGTH_SHORT);
                snack.show();
                return true;

            case R.id.funStuff:
                Snackbar snack2 = Snackbar.make(drawerLayout, "This is the end", Snackbar.LENGTH_LONG);
                snack2.show();
                Toast.makeText(this, "FOOLISH MORTAL", Toast.LENGTH_LONG).show();
                layout.setBackgroundResource(R.drawable.the_end_two);
                save = true;

                return true;

            case R.id.info_on_Creator:
                Toast.makeText(this, "Richard Rosato", Toast.LENGTH_SHORT).show();
                break;

            case R.id.kill_app:
                finish();
                return true;

            case android.R.id.home:

                if(drawerLayout.isDrawerOpen(drawerList)==true)
                {
                    drawerLayout.closeDrawer(drawerList);
                }
                else
                {
                    drawerLayout.openDrawer(drawerList);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(this, "Clicked " + activities[position], Toast.LENGTH_SHORT);
        toast.show();

        switch (position) {
            case 0:
                Intent intent = new Intent(this, InternetTab.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case 1:
                Intent intent2 = new Intent(this, SoundTest.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent2);
                break;

        }
        drawerLayout.closeDrawers();
    }

    public void callWill(View view) {

        Uri number = Uri.parse("tel:12057376281");
        Intent intent = new Intent(Intent.ACTION_CALL, number);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

    }
}
