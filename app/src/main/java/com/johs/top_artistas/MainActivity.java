package com.johs.top_artistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.johs.top_artistas.adapters.ArtistAdapter;
import com.johs.top_artistas.models.Artist;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerArtists;
    ArrayList<Artist> listArtist;
    ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerArtists = findViewById(R.id.recyclerArtists);

        listArtist = new ArrayList<>();

        //Get Items

        listArtist.add(new Artist(1,"", "David Bowie", "4279887"));
        listArtist.add(new Artist(2,"", "Coldplay", "6925187"));
        listArtist.add(new Artist(3,"", "Queen", "5401652"));
        listArtist.add(new Artist(4,"", "Radiohead", "6018666"));
        listArtist.add(new Artist(5,"", "The Rolling Stones", "4656035"));

        adapter = new ArtistAdapter(listArtist, MainActivity.this);
        recyclerArtists.setAdapter(adapter);
        recyclerArtists.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));

        recyclerArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}