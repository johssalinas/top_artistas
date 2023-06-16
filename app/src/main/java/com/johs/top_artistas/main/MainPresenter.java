package com.johs.top_artistas.main;

import android.content.Context;

import com.johs.top_artistas.entity.Artist;

import java.util.ArrayList;

public class MainPresenter implements IMainContract.Presenter{
    private final IMainContract.View view;
    private final IMainContract.Model model;

    public MainPresenter(IMainContract.View view) {
        this.view = view;
        model = new MainModel(this);
    }

    @Override
    public void showArtist(ArrayList<Artist> listArtist) {
        if(view != null) {
            view.showArtist(listArtist);
        }
    }

    @Override
    public void getArtist(ArrayList<Artist> listArtist, Context context) {
        if(view != null) {
            model.getArtist(listArtist,context);
        }
    }
}
