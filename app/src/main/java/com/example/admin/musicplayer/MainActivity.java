package com.example.admin.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private ImageButton play,pause,stop,next,prev;

    private int positionNext,positionPrev;


    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
                public void onAudioFocusChange(int focusChange){
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();

                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();

                    }
                }
            };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mp) {

        }


        public void  OnCompletion(MediaPlayer mp){

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        play = (ImageButton)findViewById(R.id.btnPlay);
        pause = (ImageButton)findViewById(R.id.btnPause);
        stop = (ImageButton)findViewById(R.id.btnStop);
        next = (ImageButton)findViewById(R.id.btnNext);
        prev = (ImageButton)findViewById(R.id.btnPrev);



        //        Create list of Words
        final ArrayList<Music> songs = new ArrayList<Music>();

        songs.add(new Music("Water Under The Bridge","Adele",R.raw.water_under_the_bridge));
        songs.add(new Music("All I Ever Need","Austin Mahome",R.raw.austin_mahone_all_i_ever_need));
        songs.add(new Music("Broadway","R. City",R.raw.broadway));
        songs.add(new Music("A Thousand Years","Christina Perri",R.raw.christina_perri_a_thousand_years_official_music_video_mp3));
        songs.add(new Music("Don't You Worry","R. City",R.raw.don_t_you_worry));
        songs.add(new Music("Numb Encore","Linkin Park ft. Jay Z",R.raw.numb_encore));
        songs.add(new Music("River Lea","Adele",R.raw.river_lea));
        songs.add(new Music("Something In The Way You Move","Ellie Goulding",R.raw.something_in_the_way_you_move));
        songs.add(new Music("Take You Down","R. City",R.raw.take_you_down));
        songs.add(new Music("Army","Ellie Goulding",R.raw.army));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.

        WordAdapter adapter = new WordAdapter(this,songs,R.color.category_Music);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.List);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Music music = songs.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(MainActivity.this, music.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }});

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }

                Music music = songs.get(positionPrev--);
                mMediaPlayer =mMediaPlayer.create(MainActivity.this, music.getAudioResourceId());


                mMediaPlayer.start();
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }

                Music music = songs.get(positionNext++);
                mMediaPlayer =mMediaPlayer.create(MainActivity.this, music.getAudioResourceId());


                mMediaPlayer.start();
            }

        });



    }

    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
