package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Создаем фрагмент
        NotesFragment notesFragment = new NotesFragment();
        // Вызываем FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, notesFragment).commit();
    }

}