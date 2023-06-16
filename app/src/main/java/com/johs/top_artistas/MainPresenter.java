package com.johs.top_artistas;

import android.content.Context;

import com.johs.top_artistas.models.Artist;

import java.util.ArrayList;

public class MainPresenter implements IMainContract.Presenter{
    private IMainContract.View view;
    private IMainContract.Model model;

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
    public void getData(ArrayList<Artist> listArtist, Context context) {
        if(view != null) {
            model.getData(listArtist,context);
        }
    }
}
