package com.johs.top_artistas;

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
import com.johs.top_artistas.adapters.SongAdapter;
import com.johs.top_artistas.models.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArtistInfoActivity extends AppCompatActivity {

    RecyclerView recyclerSong;
    ArrayList<Song> listSong;
    SongAdapter adapter;
    TextView idRank;
    ImageView imageArtist;
    TextView nameArtist;
    TextView listenersArtist;

    String StringnameArtist;
    String StringlistenersArtist;
    String StringimageArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        recyclerSong = findViewById(R.id.recyclerSong);
        imageArtist = findViewById(R.id.imageArtist);
        nameArtist = findViewById(R.id.nameArtist);
        listenersArtist = findViewById(R.id.listenersArtist);
        idRank = findViewById(R.id.idRank);
        Bundle extras = getIntent().getExtras();
        StringnameArtist = extras.getString("nameArtist");
        StringlistenersArtist = extras.getString("listenersArtist");
        StringimageArtist = extras.getString("imageArtist");

        Glide.with(this)
                .load(StringimageArtist)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageArtist);
        nameArtist.setText(StringnameArtist);
        listenersArtist.setText(StringlistenersArtist);



        // Inicializar la lista de canciones
        listSong = new ArrayList<>();

        // Obtener el top de canciones del artista
        obtenerTopCanciones();
    }

    // Método para obtener el top de canciones de un artista
    private void obtenerTopCanciones() {
        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.getTopTracks&artist=" + StringnameArtist + "&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json";

        // Realizar solicitud HTTP utilizando Volley
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Analizar la respuesta JSON para obtener el top de canciones
                    try {
                        JSONObject topTracks = response.getJSONObject("toptracks");
                        JSONArray trackArray = topTracks.getJSONArray("track");

                        // Recorrer el arreglo de canciones y obtener los datos relevantes
                        for (int i = 0; i < 5; i++) {
                            JSONObject track = trackArray.getJSONObject(i);
                            String nombreCancion = track.getString("name");
                            String image = track.getJSONArray("image").getJSONObject(2).getString("#text");
                            String listeners = track.getString("listeners");
                            String playcount = track.getString("playcount");

                            // Crear un objeto Song y añadirlo a la lista de canciones
                            listSong.add(new Song(image, nombreCancion, listeners, playcount));
                        }

                        // Mostrar la lista de artistas en tu RecyclerView
                        adapter = new SongAdapter(listSong, ArtistInfoActivity.this);
                        recyclerSong.setAdapter(adapter);
                        recyclerSong.setLayoutManager(new LinearLayoutManager(ArtistInfoActivity.this, RecyclerView.VERTICAL, false));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Manejar errores de solicitud
                    // ...
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
