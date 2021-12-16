package com.example.mynotes;

import static com.example.mynotes.CoatOfArmsFragment.ARG_INDEX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class CoatOfArmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arms);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }

        // Если эта activity запускается первый раз,
        // то перенаправим параметр фрагменту и запустим фрагмент
        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.coat_of_arms_fragment_container, CoatOfArmsFragment.newInstance(getIntent().getExtras().getInt(ARG_INDEX))).commit();
    }
}
