package com.johs.top_artistas.Songs;

import android.content.Context;

import com.johs.top_artistas.entity.Song;

import java.util.ArrayList;

public class SongPresenter implements ISongContract.Presenter{
    private ISongContract.Model model;
    private ISongContract.View view;

    public SongPresenter(ISongContract.View view) {
        this.view = view;
        model = new SongModel(this);
    }

    @Override
    public void showSongs(ArrayList<Song> listSong) {
        if(view != null){
            view.showSongs(listSong);
        }
    }

    @Override
    public void getSong(ArrayList<Song> listSong, String nameArtist, Context context) {
        if(view != null)
            model.getSong(listSong, nameArtist, context);
    }
}
