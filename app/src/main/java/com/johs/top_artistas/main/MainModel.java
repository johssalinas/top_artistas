package com.johs.top_artistas.main;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.johs.top_artistas.entity.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainModel implements IMainContract.Model{
    private final IMainContract.Presenter presenter;

    public MainModel(IMainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getArtist(ArrayList<Artist> listArtist, Context context) {
            final String url = "https://ws.audioscrobbler.com/2.0/?method=geo.getTopArtists&country=colombia&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json";
            final int numArtist = 10;

            // Realiza solicitud HTTP utilizando Volley
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> {
                        // Analiza la respuesta JSON para obtener los artistas
                        try {
                            final JSONObject topArtists = response.getJSONObject("topartists");
                            final JSONArray artistArray = topArtists.getJSONArray("artist");

                            // Limpia la lista de artistas antes de agregar los nuevos
                            listArtist.clear();

                            // Recorre el arreglo de artistas y obtener los nombres
                            for (int i = 0; i < numArtist; i++) {
                                final JSONObject artist = artistArray.getJSONObject(i);
                                final String image = artist.getJSONArray("image").getJSONObject(4).getString("#text");
                                final String nombreArtista = artist.getString("name");
                                final String listeners = artist.getString("listeners");
                                listArtist.add(new Artist(Integer.toString(i+1), image, nombreArtista, listeners));
                            }

                            //LLama al presentador para que pinte el resultado
                            presenter.showArtist(listArtist);

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
