package com.johs.top_artistas.Songs;

import android.content.Context;
import android.widget.Toast;

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
        final String url = "https://ws.audioscrobbler.com/2.0/?method=artist.getTopTracks&artist=" + nameArtist + "&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json";
        final int numSongs = 5;

        // Realiza solicitud HTTP utilizando Volley
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Analiza la respuesta JSON para obtener el top de canciones
                    try {
                        final JSONObject topTracks = response.getJSONObject("toptracks");
                        final JSONArray trackArray = topTracks.getJSONArray("track");

                        // Recorre el arreglo de canciones y obtener los datos relevantes
                        for (int i = 0; i < numSongs; i++) {
                            final JSONObject track = trackArray.getJSONObject(i);
                            final String nombreCancion = track.getString("name");
                            final String image = track.getJSONArray("image").getJSONObject(2).getString("#text");
                            final String listeners = track.getString("listeners");
                            final String playcount = track.getString("playcount");

                            // Crea un objeto Song y añadirlo a la lista de canciones
                            listSong.add(new Song(image, nombreCancion, listeners, playcount));
                        }
                        //LLama al presentador para que pinte el resultado
                        presenter.showSongs(listSong);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(context,"Falló de conexión", Toast.LENGTH_SHORT).show());

        // Agrega la solicitud a la cola de solicitudes de Volley
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
