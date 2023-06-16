package com.johs.top_artistas.Songs;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.johs.top_artistas.entity.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SongModel implements ISongContract.Model{
    private final ISongContract.Presenter presenter;

    public SongModel(ISongContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getSong(ArrayList<Song> listSong, String nameArtist, Context context) {
        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.getTopTracks&artist=" + nameArtist + "&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json";

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
                            presenter.showSongs(listSong);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Manejar errores de solicitud
                    // ...
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
