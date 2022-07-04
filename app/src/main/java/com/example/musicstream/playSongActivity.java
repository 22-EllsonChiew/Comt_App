package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class playSongActivity extends AppCompatActivity
{
    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;


    //Instantiate new Media Player
    private MediaPlayer player = new MediaPlayer();


    //Play or Pause button
    private Button btnPlayPause = null;


    //Instantiate song collection
    private SongCollection songCollection = new SongCollection();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        //Link play or pause button to java
        btnPlayPause = findViewById(R.id.btnPlayPause);


        //btnPlayPause = findViewById.id.btnPlayPause
        //Retrieve information from main activity intent via sendData
        Bundle songData = this.getIntent().getExtras();
        //retrieve the index from song data
        currentIndex = songData.getInt("index");

        //log out current index
        Log.d("playSong", "The current index is " + currentIndex);

        //Display the song title artist and image base on current index
        displaySongBasedOnIndex(currentIndex);

        //populate play song method with fileLink
        playSong(fileLink);


    }

    //Display song based on index

    public void displaySongBasedOnIndex (int selectedIndex)
    {
        //Retrieve song object using selected index
        Song song = songCollection.getCurrentSong(selectedIndex);
        title = song.getTitle();
        artist = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();

        //Populate the view widget by their id
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtistName = findViewById(R.id.txtArtistName);
        txtArtistName.setText(artist);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);

    }

    public void playSong(String songUrl)
    {
        //wrap the code in try catch to capture error handling
        try
        {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            //Detect when music end
            gracefullyStopWhenMusicEnds();

            //change the tex of play button to pause
            btnPlayPause.setText("PAUSE");
            //Set the action bar title
            setTitle(title);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Create play or pause method
    public void PlayorPauseMusic(View view)
    {
        //check if player is playing, if yes then pause
        if(player.isPlaying())
        {
            player.pause();
            btnPlayPause.setText("PLAY");
        }
        else
        {
            player.start();
            btnPlayPause.setText("PAUSE");
        }
    }

    //Create method to detect music ends using event Listener
    private void gracefullyStopWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                Toast.makeText(getBaseContext(), "The song had ended and the onCompleteListener " + "is activated\nThe button text is changed to 'PLAY' ", Toast.LENGTH_LONG).show();
                btnPlayPause.setText("PLAY");
            }
        });
    }

    //play Next Method
    public void playNext(View view)
    {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this,"After clicking playNext,\n the current index of this song \n" + currentIndex
        ,Toast.LENGTH_LONG).show();
        Log.d("PlaySongActivity", "After playNext, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    //Play Previous Method
    public void playPrevious(View view)
    {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious, index is: " + currentIndex , Toast.LENGTH_SHORT).show();
        Log.d("PlaySongActivity", "After playPrevious, the index is now: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        player.release();
    }
}







