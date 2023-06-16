package com.johs.top_artistas.Songs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.johs.top_artistas.R;
import com.johs.top_artistas.adapters.SongAdapter;
import com.johs.top_artistas.entity.Song;
import com.johs.top_artistas.main.MainActivity;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity implements ISongContract.View{

    private RecyclerView recyclerSong;
    private ArrayList<Song> listSong;
    private SongAdapter adapter;
    private String StringNameArtist;
    private String stringListenersArtist;
    private String stringImageArtist;
    private String stringIdArtist;
    private ImageView imageArtist;
    private TextView nameArtist;
    private TextView listenersArtist;
    private TextView idRank;
    private ISongContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        recyclerSong = findViewById(R.id.recyclerSong);
        imageArtist = findViewById(R.id.imageArtist);
        nameArtist = findViewById(R.id.nameArtist);
        listenersArtist = findViewById(R.id.listenersArtist);
        idRank = findViewById(R.id.idRank);

        //Obtiene la información enviada desde el adaptador
        Bundle extras = getIntent().getExtras();
        StringNameArtist = extras.getString("nameArtist");
        stringListenersArtist = extras.getString("listenersArtist");
        stringImageArtist = extras.getString("imageArtist");
        stringIdArtist = extras.getString("idArtist");

        // Inicializar la lista de canciones
        listSong = new ArrayList<>();
        presenter = new SongPresenter(this);

        showArtistInfo();
        presenter.getSong(listSong, StringNameArtist,this);
    }

    private void showArtistInfo(){
        Glide.with(this)
                .load(stringImageArtist)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageArtist);
        nameArtist.setText(StringNameArtist);
        listenersArtist.setText(stringListenersArtist);
        idRank.setText(stringIdArtist);
    }

    @Override
    public void showSongs(ArrayList<Song> listSong) {
        adapter = new SongAdapter(listSong, SongActivity.this);
        recyclerSong.setAdapter(adapter);
        recyclerSong.setLayoutManager(new LinearLayoutManager(SongActivity.this, RecyclerView.VERTICAL, false));
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
