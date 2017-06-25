package com.batchmates.android.navigator;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SoundTest extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] activities;
    private TextView view;
    private SeekBar seek;
    private boolean save=false;
    private FrameLayout layout;
    float soundBefore=(float)Math.abs(1);
    float log=soundBefore/100;
    private int prog=1;
    private MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_test);
        setTitle("Music Tab");
        layout=(FrameLayout)findViewById(R.id.mainLayoutSound);
        drawerLayout=(DrawerLayout)findViewById(R.id.draweLayoutSound);
        activities=getResources().getStringArray(R.array.activities);
        drawerList=(ListView)findViewById(R.id.list_ViewSound);
        view= (TextView)findViewById(R.id.numberTxt);
        seek=(SeekBar)findViewById(R.id.seekBar);
        media=new MediaPlayer().create(this,R.raw.hero);
        media.setVolume(0,log);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //initilizing the drawer
        drawerList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,activities));
        drawerList.setOnItemClickListener(this);


        //initializing seekbar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d(""+progress, "onProgressChanged: ");
                prog=progress;
                view.setText(String.valueOf(progress));
                soundUpdate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void soundUpdate(int i)
    {

        float soundBefore=(float)Math.abs(i);
        float log=soundBefore/100;

        if(log==(float)0.0)
        {
            soundBefore=(float)Math.abs(1);
            log=soundBefore/100;
            media.setVolume(0,log);
        }else
        {
            media.setVolume(0,log);
        }
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
                save=true;
                view.setTextColor(Color.WHITE);
                Toast.makeText(this, "FOOLISH MORTAL", Toast.LENGTH_LONG).show();
                layout.setBackgroundResource(R.drawable.the_end_two);
                return true;

            case R.id.info_on_Creator:
                Toast.makeText(this, "Richard Rosato", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.kill_app:
                finish();
                return true;

            case android.R.id.home:
                media.release();
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
//            Log.d("Sound_Back", "onKeyDown: ");
//            if(media.isPlaying()==true)
//            {
//                media.stop();
//            }
//            Intent intent = new Intent(this,MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            //finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void startMusic(View view) {

        if(media.isPlaying()==false)
        {

            float soundBefore=(float)Math.abs(prog);
            float log=soundBefore/100;
            Log.d(""+log, "startMusic: ");
            if(prog==1||prog==0)
            {
                Toast toast2= Toast.makeText(this,"Cant Start a song wiht No Sound",Toast.LENGTH_SHORT);
                toast2.show();
            }
            else
                {
                    media = new MediaPlayer().create(this, R.raw.hero);
                    media.setVolume(0, prog);
                    media.start();
                    //soundUpdate(prog);
            }
        }
        else
        {
            Toast toast= Toast.makeText(this,"Already playing Song",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void stopMusic(View view) {

        if(media.isPlaying()==true)
        {
            media.stop();
        }
        else
        {
            Toast toast= Toast.makeText(this,"Song is not playing",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
