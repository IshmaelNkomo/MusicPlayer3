package com.example.admin.musicplayer;

/**
 * Created by Admin on 7/6/2017.
 */

public class Music {

//    song title
    private String mSongTitle;

    //    Artist name
    private String mArtistName;


//    Audio Resource ID for the Song
    private int mAudioResourceId;


public Music(String songTitle, String artistName, int audioResourceId){

//         initialise the parameters

    mSongTitle = songTitle;
    mArtistName = artistName;
    mAudioResourceId = audioResourceId;
}
//    Return the song title
    public String getmSongTitle() {
        return mSongTitle;
    }
// Return the artist name
    public String getmArtistName() {
        return mArtistName;
    }
//   Return the audio resource ID of the Song
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
