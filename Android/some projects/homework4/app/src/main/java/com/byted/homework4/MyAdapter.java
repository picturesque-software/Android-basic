package com.byted.homework4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<ArticleResponse.Article> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView name;

        public MyViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            name = v.findViewById(R.id.name);
        }
    }


    public void setData(List<ArticleResponse.Article> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.id.setText("" + mDataset.get(position).id);
        holder.name.setText(mDataset.get(position).name);

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}