package com.johs.top_artistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.johs.top_artistas.adapters.ArtistAdapter;
import com.johs.top_artistas.models.Artist;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerArtists;
    ArrayList<Artist> listaOp;
    ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerArtists = findViewById(R.id.recyclerArtists);

        listaOp = new ArrayList<>();

        listaOp.add(new Artist("", "David Bowie", "4279887"));
        listaOp.add(new Artist("", "Coldplay", "6925187"));
        listaOp.add(new Artist("", "Queen", "5401652"));
        listaOp.add(new Artist("", "Radiohead", "6018666"));
        listaOp.add(new Artist("", "The Rolling Stones", "4656035"));

        adapter = new ArtistAdapter(listaOp, MainActivity.this);
        recyclerArtists.setAdapter(adapter);
        recyclerArtists.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));
    }
}