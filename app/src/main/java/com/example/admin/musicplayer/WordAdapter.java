package com.example.admin.musicplayer;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/6/2017.
 */

public class WordAdapter extends ArrayAdapter<Music> {

    private  int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Music> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,  words);
        mColorResourceId = colorResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Music currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artistName_text_view);
        // Get the version name from the current Music object and
        // set this text on the name TextView
        artistNameTextView.setText(currentWord.getmArtistName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.songName_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        songNameTextView.setText(currentWord.getmSongTitle());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView

//        if(currentWord.hasImage()){
//
//            iconView.setImageResource(currentWord.getImageResourceId());
//
//            iconView.setVisibility(View.VISIBLE);
//        }
//        else{
//
//            iconView.setVisibility(View.GONE);
//        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}

