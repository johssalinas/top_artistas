package com.johs.top_artistas.Songs;

import android.content.Context;

import com.johs.top_artistas.entity.Song;

import java.util.ArrayList;

public interface ISongContract {
    interface View{
        void showSongs(ArrayList<Song> listSong);
    }
    interface Presenter{
        void showSongs(ArrayList<Song> listSong);
        void getSong(ArrayList<Song> listSong, String nameArtist, Context context);
    }
    interface Model{
        void getSong(ArrayList<Song> listSong, String nameArtist, Context context);

    }
}
