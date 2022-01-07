package com.example.mynotes;

import static com.example.mynotes.CoatOfArmsFragment.ARG_INDEX;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotesFragment extends Fragment {

    private static final String CURRENT_NOTE = "CurrentNote";
    // Текущая позиция (заметка 0)
    //private int currentPosition = 0;
    private Notes notes = null;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    // Создаем список заметок.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        // lesson-07 //  if (savedInstanceState != null) {
        // lesson-07 //     notes = savedInstanceState.getParcelable(CURRENT_NOTE);
        // lesson-07 // }
        // инициализация списка


        initList(view);
        // отображения открытой ранее заметки в ландшафтной ориентации
        // lesson-07 // if (isLandscape()) {
        // lesson-07 //     showLandCoatOfArms(notes);
        // lesson-07 // }
    }

    // создаём список заметок из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notesArray = getResources().getStringArray(R.array.coat_of_arms_header);
        String[] notesTextArray = getResources().getStringArray(R.array.coat_of_arms_notes);
        String[] notesDataArray = getResources().getStringArray(R.array.coat_of_arms_notes_date);

        // В этом цикле создаём элемент TextView, заполняем его значениями, и добавляем на экран.
        for (int i = 0; i < notesArray.length; i++) {
            String currentNotes = notesArray[i];
            String noteText = notesTextArray[i];
            String noteData = notesDataArray[i];

            TextView tv = new TextView(getContext());
            tv.setText(currentNotes);
            tv.setTextSize(getResources().getDimension(R.dimen.note_font_size));
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                showPortCoatOfArms(new Notes(position, currentNotes, noteText, noteData));
                // lesson-07 // notes = new Notes(position, currentNotes, noteText, noteData);
                // lesson-07 // showCoatOfArms(notes);
            });
        }
    }

    private void showCoatOfArms(Notes notes) {
        if (isLandscape()) {
            showLandCoatOfArms(notes);
        } else {
            showPortCoatOfArms(notes);
        }
    }

    // Показываем заметки в портретной ориентации
    private void showPortCoatOfArms(Notes notes) {
        FragmentTransaction fragmentTransaction = requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction
                .addToBackStack("")
                .add(R.id.fragment_container, CoatOfArmsFragment.newInstance(notes))
                .commit();
        // lesson-07 // Activity activity = requireActivity();
        // lesson-07 // final Intent intent = new Intent(activity, CoatOfArmsActivity.class);
        // lesson-07 // intent.putExtra(ARG_INDEX, notes);
        // lesson-07 // activity.startActivity(intent);
    }

    // Показываем заметки в ландшафтной ориентации
    private void showLandCoatOfArms(Notes notes) {
        // Создаём новый фрагмент с текущей позицией для вывода заметок
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(notes);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms_container, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putInt(CURRENT_NOTE, currentPosition);
        outState.putParcelable(CURRENT_NOTE, notes);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static CoatOfArmsFragment newInstance(Notes notes) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, notes);
        fragment.setArguments(args);
        return fragment;
    }
}
