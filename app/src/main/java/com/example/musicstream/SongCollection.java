package com.example.musicstream;

public class SongCollection
{
    private Song song[] = new Song[3];


    public SongCollection()
    {

        //Micheal Buble
        Song theWayYouLookTonight = new Song ("S1001", " 1. The Way You Look Tonight ","Micheal Buble", "https://p.scdn.co/mp3-preview/b4893d249495bdad00828e6f0059b7e7d762da07?cid=2afe87a64b0042dabf51f37318616965",
                4.66, R.drawable.michael_buble_collection);

        //Micheal Jackson
        Song billieJean = new Song ("S1002", "2. Billie Jean", "Micheal Jackson", "https://p.scdn.co/mp3-preview/71638a1eac196a5daa9fbf152693585e323d8558?cid=2afe87a64b0042dabf51f37318616965",
                4.9, R.drawable.billie_jean);


        //Populate Song Array
        song[0] = theWayYouLookTonight;
        song[1] = billieJean;



    }


    public Song getCurrentSong (int currentSongId)
    {
        return song[currentSongId];
    }

    //Search Song Id
    public int searchSongById (String id)
    {
        for (int index = 0; index < song.length; index++)
        {

            //Temporary Song object
            Song tempSong = song[index];
            if (tempSong.getId().equals(id))
            {
                return index;

            }
        }
        return -1;
    }

    //Next Song
    public int getNextSong(int currentSongIndex)
    {
        if (currentSongIndex >= song.length-1)
        {
            return currentSongIndex;
        }
        else
        {
            return  currentSongIndex +1;
        }
    }

    public int getPrevSong(int currentSongIndex)
    {
        if (currentSongIndex <= 0)
        {
            return currentSongIndex;
        }
        else
        {
            return currentSongIndex -1;
        }
    }

}
