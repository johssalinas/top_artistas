package com.johs.top_artistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.johs.top_artistas.adapters.SongAdapter;
import com.johs.top_artistas.models.Song;

import java.util.ArrayList;

public class ArtistInfoActivity extends AppCompatActivity {

    RecyclerView recyclerSong;
    ArrayList<Song> listSong;
    SongAdapter adapter;

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        recyclerSong = findViewById(R.id.recyclerSong);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("ID");

        //Get Songs

        listSong = new ArrayList<>();

        listSong.add(new Song("","Creep", "2442540", "25253877"));
        listSong.add(new Song("","Creep", "2442540", "25253877"));

        adapter = new SongAdapter(listSong, ArtistInfoActivity.this);
        recyclerSong.setAdapter(adapter);
        recyclerSong.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }
}