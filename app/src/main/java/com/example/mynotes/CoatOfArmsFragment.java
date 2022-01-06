package com.example.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        // Аргументы могут быть null (как в случае с методом Activity getIntent())
        // поэтому обязательно проверяем на null
        if (arguments != null) {
            Notes notes = (Notes) arguments.getParcelable(ARG_INDEX);
            // найдем в root view нужный ImageView (скрепка)
            ///////////ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            // Получим из ресурсов массив данных: скрепка / заголовок / текст / дата
            ////////TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);

            // Возьмем нужное изображение и отобразим в ImageView
            ////////imageCoatOfArms.setImageResource(images.getResourceId(notes.getImageIndex(),0));
            ///////images.recycle();
            // Возьмем текст заметки и отобразим в  TextView
            /////fulltextCoatOfArms.setText(stringFullText.getResourceId(notes, 0));
            //stringFullText.recycle();
            // Возьмем заголовок заметки и отобразим в  TextView
            ImageView imageViewNote = view.findViewById(R.id.coat_of_arms_image_view);
            TypedArray imageNotes = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            imageViewNote.setImageResource(imageNotes.getResourceId(notes.getImageIndex(),0));
            //imageNotes.recycle();

            TextView textViewNotesHeader = view.findViewById(R.id.notes_header_TW);
            //TypedArray noteHeader = getResources().obtainTypedArray(R.id.notes_header_TW);
            textViewNotesHeader.setText(notes.getNoteName());
            //noteHeader.recycle();

            TextView textViewNotesText = view.findViewById(R.id.notes_fulltext_TW);
            //TypedArray notesText = getResources().obtainTypedArray(R.id.notes_fulltext_TW);
            textViewNotesText.setText(notes.getNoteText());
            //notesText.recycle();

            EditText editTextNotesDate = view.findViewById(R.id.notesTextDate);
            //TypedArray notesData = getResources().obtainTypedArray(R.id.notesTextDate);
            editTextNotesDate.setText(notes.getNoteData());
            //notesData.recycle();




            //headertextCoatOfArms.setText(stringHeaderText.getResourceId(index, 0));
            //stringHeaderText.recycle();
            // Дата заметки в EditText
            ///////dateTextCoatOfArms.setText(dateNotesText.getResourceId(notes, 0));
            ///////dateNotesText.recycle();
        }
    }

    public static CoatOfArmsFragment newInstance(Notes notes) {
        // Создание фрагмента
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        // Передача параметра через бандл
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX,notes);
        //args.putInt(ARG_INDEX, notes);
        fragment.setArguments(args);
        return fragment;
    }
}
