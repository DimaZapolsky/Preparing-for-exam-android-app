package com.example.dima_zapolsky.exam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {
    ArrayList<Theme> themes;

    class ThemeHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        public ThemeHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameView);
        }
    }

    @Override
    public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_view, parent, false));
    }

    public ThemeAdapter(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public void onBindViewHolder(final ThemeHolder holder, final int position) {
        holder.nameView.setText(String.valueOf(position + 1).concat(") ").concat(themes.get(position).getName()));

        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuestionsActivity.class);
                intent.putExtra("pos", position);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }
}
