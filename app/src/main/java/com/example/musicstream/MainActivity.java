package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;




import android.view.View;

public class MainActivity extends AppCompatActivity
{

    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    //Send data to activity method
    public void sendDataActivity (int index)
    {
        Intent intent = new Intent (this, playSongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }



    public void handleSelection (View myView)
    {
        String resourceId = getResources().getResourceEntryName(myView.getId());
        Log.d("temasek", "The id of the pressed image button is : " + resourceId);

        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "The Current array index of pressed Image Button is : " + resourceId);

        //Send the information to play
        sendDataActivity(currentArrayIndex);
    }
}