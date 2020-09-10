package com.donsmart.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RowThingsAdapter extends RecyclerView.Adapter<RowThingsAdapter.ViewHolder>
{
    private ArrayList<RowThings> things;
    ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public RowThingsAdapter(Context context, ArrayList<RowThings> list)
    {
        things = list;
        activity = (ItemClicked) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle,tvNotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNotes = itemView.findViewById(R.id.tvNote);

                tvNotes.setVisibility(View.GONE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onItemClicked(things.indexOf((RowThings) v.getTag()));

                }
            });

        }
    }


    @NonNull
    @Override
    public RowThingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowThingsAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(things.get(position));

        holder.tvTitle.setText(things.get(position).getTitle());
        holder.tvNotes.setText(things.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return things.size();
    }
}
