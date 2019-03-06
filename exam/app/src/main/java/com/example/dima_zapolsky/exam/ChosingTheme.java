package com.example.dima_zapolsky.exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ChosingTheme extends AppCompatActivity {

    private ArrayList<Theme> themes;
    private RecyclerView themesView;

    private ThemeAdapter adapter = null;

    public Theme get_theme(int position) {
        return themes.get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosing_theme);
        GeneralManager.getInstance(null).fill(this);

        themesView = findViewById(R.id.themes_view);

        adapter = new ThemeAdapter(GeneralManager.getInstance(null).getThemes());
        themesView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        themesView.setAdapter(adapter);
    }

}
