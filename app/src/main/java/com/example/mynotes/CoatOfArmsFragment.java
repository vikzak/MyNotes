package com.example.mynotes;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            int index = arguments.getInt(ARG_INDEX);
            // найдем в root view нужный ImageView
            ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            // найдем в root view нужный TextView для текста заметки
            TextView fulltextCoatOfArms = view.findViewById(R.id.notes_fulltext_TW);
            // найдем в root view нужный TextView для заголовка заметки
            TextView headertextCoatOfArms = view.findViewById(R.id.notes_header_TW);
            // Получим из ресурсов массив указателей на изображения гербов
            // Обратите внимание на тип - TypedArray, и способ получения - obtainTypedArray
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            TypedArray stringFullText = getResources().obtainTypedArray(R.array.coat_of_arms_notes);
            TypedArray stringHeaderText = getResources().obtainTypedArray(R.array.coat_of_arms_header);

            // Возьмем нужное изображение и отобразим в ImageView
            imageCoatOfArms.setImageResource(images.getResourceId(index, 0));
            // TypedArray рекомендуется закрыть после использования
            images.recycle();

            fulltextCoatOfArms.setText(stringFullText.getResourceId(index,0));
            stringFullText.recycle();

            //String[] cities = getResources().getStringArray(R.array.notes_name);
            headertextCoatOfArms.setText(stringHeaderText.getResourceId(index,0));
            stringHeaderText.recycle();
        }
    }

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы
    public static CoatOfArmsFragment newInstance(int index) {
        // Создание фрагмента
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        // Передача параметра через бандл
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}
