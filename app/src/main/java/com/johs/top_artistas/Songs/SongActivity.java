package com.johs.top_artistas.Songs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.johs.top_artistas.R;
import com.johs.top_artistas.adapters.SongAdapter;
import com.johs.top_artistas.entity.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity implements ISongContract.View{

    private RecyclerView recyclerSong;
    private ArrayList<Song> listSong;
    private SongAdapter adapter;
    private String StringnameArtist;
    private ISongContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        recyclerSong = findViewById(R.id.recyclerSong);
        ImageView imageArtist = findViewById(R.id.imageArtist);
        TextView nameArtist = findViewById(R.id.nameArtist);
        TextView listenersArtist = findViewById(R.id.listenersArtist);
        Bundle extras = getIntent().getExtras();
        StringnameArtist = extras.getString("nameArtist");
        String stringlistenersArtist = extras.getString("listenersArtist");
        String stringimageArtist = extras.getString("imageArtist");

        Glide.with(this)
                .load(stringimageArtist)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageArtist);
        nameArtist.setText(StringnameArtist);
        listenersArtist.setText(stringlistenersArtist);

        // Inicializar la lista de canciones
        listSong = new ArrayList<>();
        presenter = new SongPresenter(this);

        presenter.getSong(listSong,StringnameArtist,this);
    }

    @Override
    public void showSongs(ArrayList<Song> listSong) {
        adapter = new SongAdapter(listSong, SongActivity.this);
        recyclerSong.setAdapter(adapter);
        recyclerSong.setLayoutManager(new LinearLayoutManager(SongActivity.this, RecyclerView.VERTICAL, false));
    }
}
