package com.batchmates.android.navigator;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class InternetTab extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] activities;
    private boolean save=false;
    private FrameLayout layout;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_tab);

        layout=(FrameLayout)findViewById(R.id.mainLayoutInt);
        drawerLayout=(DrawerLayout)findViewById(R.id.draweLayoutInt);
        activities=getResources().getStringArray(R.array.activities);
        drawerList=(ListView)findViewById(R.id.list_ViewInt);
        web=(WebView)findViewById(R.id.wvWeb);
        setTitle("Internet Tab");
        drawerList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,activities));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerList.setOnItemClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        web.setWebViewClient(new WebViewClient());

        web.loadUrl("https://www.google.com/");
        WebSettings webSettings=web.getSettings();

        webSettings.setJavaScriptEnabled(true);


    }


    //needed for overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.overflow_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);


    }

    //for menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.circlePuzzle:
                //play a puzzle game
                Intent intent= new Intent(this,ThreeByThree.class);
                startActivity(intent);
                Log.d("Play a Puzzle", "onOptionsItemSelected: ");
                return true;

            case R.id.settings:
                if(save==true)
                {
                    Snackbar snack3= Snackbar.make(drawerLayout,"Evil has been Vanquished",Snackbar.LENGTH_SHORT);
                    snack3.show();
                    layout.setBackgroundResource(R.drawable.ill_save_you);
                    Toast.makeText(this, "Ill SAVE YA LADDY", Toast.LENGTH_LONG).show();
                    save=false;
                }
                else {
                    Toast.makeText(this, "You dont need saving", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.aboutUs:
                Snackbar snack= Snackbar.make(drawerLayout,"We love snacks",Snackbar.LENGTH_SHORT);
                snack.show();
                return true;

            case R.id.funStuff:
                Snackbar snack2= Snackbar.make(drawerLayout,"This is the end",Snackbar.LENGTH_LONG);
                snack2.show();
                Toast.makeText(this, "FOOLISH MORTAL", Toast.LENGTH_LONG).show();
                layout.setBackgroundResource(R.drawable.the_end_two);
                save=true;
                return true;

            case R.id.info_on_Creator:
                Toast.makeText(this, "Richard Rosato", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.kill_app:
                finish();
                return true;

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast toast= Toast.makeText(this,"Clicked "+activities[position],Toast.LENGTH_SHORT);
        toast.show();

        switch (position)
        {
            case 0:
                Intent intent= new Intent(this,InternetTab.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case 1:
                Intent intent2= new Intent(this,SoundTest.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;

        }
        drawerLayout.closeDrawers();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//
//            //can go back to first activity and then leave
//            Intent intent = new Intent(this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            //finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
