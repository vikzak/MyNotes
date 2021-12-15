package com.example.mynotes;
import static com.example.mynotes.CoatOfArmsFragment.ARG_INDEX;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    // Текущая позиция (выбранный город)
    private int currentPosition = 0;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображению информации. Создаем список городов.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0);
        }
        // инициализация списка
        initList(view);
        // отображения открытого ранее герба в ландшафтной ориентации
        if (isLandscape()) {
            showLandCoatOfArms(currentPosition);
        }
    }

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.coat_of_arms_header);
        //fulltextCoatOfArms

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(20);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                currentPosition = position;
                showCoatOfArms(position);
            });
        }


    }

    private void showCoatOfArms(int index) {
        if (isLandscape()) {
            showLandCoatOfArms(index);
        } else {
            showPortCoatOfArms(index);
        }
    }

    // Показываем герб в портретной ориентации
    private void showPortCoatOfArms(int index) {
        Activity activity = requireActivity();
        final Intent intent = new Intent(activity, CoatOfArmsActivity.class);
        intent.putExtra(ARG_INDEX, index);
        activity.startActivity(intent);
    }

    // Показываем герб в ландшафтной ориентации
    private void showLandCoatOfArms(int index) {
        // Создаём новый фрагмент с текущей позицией для вывода герба
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(index);
        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms_container, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_CITY, currentPosition);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}
