package org.brometheus.morsegenerator;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;


public class DisplayTranslationActivity extends ActionBarActivity {

    private SoundPool pool;
    private String morsecode="";
    private int sound1;
    private int sound2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        sound1=pool.load(this,R.raw.dash,1);
        sound2=pool.load(this,R.raw.dot,2);

        //TextView textField = (TextView)findViewById(R.id.textField);


        String[] morseLetters = {".-","-...","-.-.","-..",".","..--","--.",
                "....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
                ".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        String []morseNumbers={"-----",".----","..---","...--","....-",".....",
                "-....","--...","---..","----."};

        //numbers 48-57; letters 65-90

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String translate = intent.getStringExtra(Input.EXTRA_MESSAGE);
        translate=translate.toUpperCase();
        for (int i = 0; i<translate.length();i++)
        {
            char character = translate.charAt(i);
            String debug = Character.toString(character);
            Log.i("info", debug);
            System.out.println(debug);
            //if the character can be a number or letter.
            if (character>=48&&character<=90)
            {
                //check if number.
                if (character<=57)
                {
                    morsecode = morsecode+(morseNumbers[character-48]);
                }


                else if (character >=65)
                {
                    morsecode=morsecode+(morseLetters[character-65]);

                }


                //if not the last character, add a space.
                if (i<translate.length()-1)
                {
                    morsecode=morsecode+' ';
                }
            }
            //morsecode=morsecode+' ';
        }

        setContentView(R.layout.activity_display_translation);


        TextView textField = (TextView) findViewById(R.id.textField);
        textField.setText(morsecode);
        textField.setTextSize(40);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_translation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playback(View view) throws InterruptedException {


        for (int location = 0; location<morsecode.length();location++)
        {
            char characterToBePlayed=morsecode.charAt(location);
           if (characterToBePlayed=='.')
            {
                pool.play(sound2,1,1,1,0,1f);
                Thread.sleep(245);
            }
            else if (characterToBePlayed=='-')
            {
                pool.play(sound1,1,1,1,0,1f);
                Thread.sleep(500);
            }
            else
           {
               Thread.sleep(500);
           }
        }

    }







/*
        MediaPlayer dot = MediaPlayer.create(this,R.raw.dot);
        MediaPlayer dash = MediaPlayer.create(this, R.raw.dash);
        char current;
        char next;






        */

}
