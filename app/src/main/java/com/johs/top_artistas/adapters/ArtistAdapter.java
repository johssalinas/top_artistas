package com.johs.top_artistas.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johs.top_artistas.ArtistInfoActivity;
import com.johs.top_artistas.R;
import com.johs.top_artistas.models.Artist;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>{

    ArrayList<Artist> listaArtists;
    Context context;

    public ArtistAdapter(ArrayList<Artist> listaArtists, Context context) {
        this.listaArtists = listaArtists;
        this.context = context;
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, null, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistViewHolder holder, int position) {
        holder.nameArtist.setText(listaArtists.get(position).getName());
        holder.listenersArtist.setText(listaArtists.get(position).getListeners());

    }

    @Override
    public int getItemCount() {
        return listaArtists.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView imageArtist;
        TextView nameArtist;
        TextView listenersArtist;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            imageArtist = itemView.findViewById(R.id.imageArtist);
            nameArtist = itemView.findViewById(R.id.nameArtist);
            listenersArtist = itemView.findViewById(R.id.listenersArtist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ArtistInfoActivity.class);
                    intent.putExtra("ID", listaArtists.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
