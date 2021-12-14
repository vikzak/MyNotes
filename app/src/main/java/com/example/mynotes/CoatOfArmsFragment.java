package com.example.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;


public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "index";


    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoatOfArmsFragment() {
        // Required empty public constructor
    }



    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы
    public static CoatOfArmsFragment newInstance(int index) {
        // Создание фрагмента
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        // Аргументы могут быть null (как в случае с методом Activity getIntent())
        // поэтому обязательно проверяем на null if (arguments != null) {
        int index = arguments.getInt(ARG_INDEX);
        // найдем в root view нужный ImageView
        ImageView imageCoatOfArms =
                view.findViewById(R.id.coat_of_arms_image_view);
        // Получим из ресурсов массив указателей на изображения гербов
        // Обратите внимание на тип - TypedArray, и способ получения - obtainTypedArray
        TypedArray images =
                getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
// Возьмем нужное изображение и отобразим в ImageView imageCoatOfArms.setImageResource(images.getResourceId(index, 0)); // TypedArray рекомендуется закрыть после использования images.recycle();
    }
}
