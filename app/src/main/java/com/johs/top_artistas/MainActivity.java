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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArtists;
    private ArrayList<Artist> listArtist;
    private ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerArtists = findViewById(R.id.recyclerArtists);

        listArtist = new ArrayList<>();

        //Get
        if (!isNetworkConnected()) {
            return;
        }

        obtenerArtistasTop();
    }

// Dentro de tu Activity o Fragment correspondiente a la primera pantalla

    // Método para obtener la lista de artistas top
    private void obtenerArtistasTop() {
        final String url = "https://ws.audioscrobbler.com/2.0/?method=geo.getTopArtists&country=colombia&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json";

        // Realizar solicitud HTTP utilizando Volley (asegúrate de tener la dependencia agregada en tu archivo build.gradle)
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Analizar la respuesta JSON para obtener los artistas
                    try {
                        JSONObject topArtists = response.getJSONObject("topartists");
                        JSONArray artistArray = topArtists.getJSONArray("artist");

                        // Limpiar la lista de artistas antes de agregar los nuevos
                        listArtist.clear();

                        // Recorrer el arreglo de artistas y obtener los nombres
                        for (int i = 0; i < 10; i++) {
                            JSONObject artist = artistArray.getJSONObject(i);
                            String image = artist.getJSONArray("image").getJSONObject(4).getString("#text");
                            String nombreArtista = artist.getString("name");
                            String listeners = artist.getString("listeners");
                            listArtist.add(new Artist(Integer.toString(i+1), image, nombreArtista, listeners));
                        }

                        // Mostrar la lista de artistas en tu RecyclerView
                        adapter = new ArtistAdapter(listArtist, MainActivity.this);
                        recyclerArtists.setAdapter(adapter);
                        recyclerArtists.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    //TODO
                    // Manejar errores de solicitud
                    // ...
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }


    // Método para verificar la conectividad a Internet
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}