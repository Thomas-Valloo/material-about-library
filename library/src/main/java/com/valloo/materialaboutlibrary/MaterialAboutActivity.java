package com.valloo.materialaboutlibrary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public abstract class MaterialAboutActivity extends AppCompatActivity {


    protected abstract CharSequence getActivityTitle();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mal_material_about_activity);

        setTitle(getActivityTitle());

        initToolbar();

        LinearLayout container = (LinearLayout) findViewById(R.id.mal_container);
        for (MaterialAboutCard card : getCardsList()) {
            View cardView = card.buildView(container, this);
            container.addView(cardView);
        }
    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected abstract List<MaterialAboutCard> getCardsList();
}
