package com.johs.top_artistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.johs.top_artistas.adapters.ArtistAdapter;
import com.johs.top_artistas.models.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainContract.View{

    private RecyclerView recyclerArtists;
    private ArrayList<Artist> listArtist;
    private ArtistAdapter adapter;
    private IMainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerArtists = findViewById(R.id.recyclerArtists);

        listArtist = new ArrayList<>();
        presenter = new MainPresenter(this);

        presenter.getData(listArtist, this);
    }

    @Override
    public void showArtist(ArrayList<Artist> listArtist) {
        adapter = new ArtistAdapter(listArtist, MainActivity.this);
        recyclerArtists.setAdapter(adapter);
        recyclerArtists.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
    }
}