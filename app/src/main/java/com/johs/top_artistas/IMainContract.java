package com.johs.top_artistas;

import android.content.Context;

import com.johs.top_artistas.models.Artist;

import java.util.ArrayList;

public interface IMainContract {

    interface View{
        void showArtist(ArrayList<Artist> listArtist);
    }
    interface Presenter {
        void showArtist(ArrayList<Artist> listArtist);
        void getData(ArrayList<Artist> listArtist, Context context);

    }
    interface Model{
        void getData(ArrayList<Artist> listArtist, Context context);
    }
}
