package com.johs.top_artistas.main;

import android.content.Context;

import com.johs.top_artistas.entity.Artist;

import java.util.ArrayList;

public interface IMainContract {

    interface View{
        void showArtist(ArrayList<Artist> listArtist);
    }
    interface Presenter {
        void showArtist(ArrayList<Artist> listArtist);
        void getArtist(ArrayList<Artist> listArtist, Context context);

    }
    interface Model{
        void getArtist(ArrayList<Artist> listArtist, Context context);
    }
}
