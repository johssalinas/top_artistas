package com.johs.top_artistas.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johs.top_artistas.R;
import com.johs.top_artistas.models.Song;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SongAdapter  extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

    ArrayList<Song> listSong;
    Context context;

    public SongAdapter(ArrayList<Song> listSong, Context context) {
        this.listSong = listSong;
        this.context = context;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, null, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        holder.nameSong.setText(listSong.get(position).getName());
        holder.playSong.setText(listSong.get(position).getPlays());
        holder.lisenSong.setText(listSong.get(position).getListeners());
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView imageSong;
        TextView nameSong;
        TextView playSong;
        TextView lisenSong;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSong = itemView.findViewById(R.id.imageSong);
            nameSong = itemView.findViewById(R.id.nameSong);
            playSong = itemView.findViewById(R.id.playSong);
            lisenSong = itemView.findViewById(R.id.lisenSong);
        }
    }
}
