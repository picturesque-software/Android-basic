package com.example.simpletito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.ViewHolder> {
    private List<videoItem> myVideoList;

    public videoAdapter(List<videoItem> videoList){
        myVideoList=videoList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        videoItem theVideo=myVideoList.get(position);
        holder.thumbnail.setImageURI(theVideo.getThumbnailRes());
    }

    @Override
    public int getItemCount() {
        return myVideoList.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        Button button;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            thumbnail=(ImageView) itemView.findViewById(R.id.video_thumbnail);
            button=(Button) itemView.findViewById(R.id.videoDetail);

        }
    }
}
