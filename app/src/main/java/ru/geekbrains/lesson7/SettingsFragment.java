package ru.geekbrains.lesson7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loadSettings();
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initSwitchBackStack(view);
        initSwitchBackAsRemove(view);
        initSwitchDeleteBeforeAdd(view);
        initRadioAdd(view);
        initRadioReplace(view);

    }

    // инициализируем радио кнопку, отвечающую за замену фрагметов
    private void initRadioReplace(View view) {
        RadioButton radioButtonReplace = view.findViewById(R.id.radioButtonReplace);
        radioButtonReplace.setChecked(Settings.isReplaceFragment );
        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isReplaceFragment =isChecked;
                saveSettings();
            }
        });
    }


    // инициализируем радио кнопку, отвечающую за добавление фрагментов
    private void initRadioAdd(View view) {
        RadioButton radioButtonAdd = view.findViewById(R.id.radioButtonAdd);
        radioButtonAdd.setChecked(Settings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment =isChecked;
                saveSettings();
            }
        });
    }


    // инициализируем переключатель ответчающий за удаление фрагмента из стека перед добавлением нового
    private void initSwitchDeleteBeforeAdd(View view) {
        SwitchCompat switchCompatDeleteBeforeAdd = view.findViewById(R.id.switchDeleteBeforeAdd);
        switchCompatDeleteBeforeAdd.setChecked(Settings.isDeleteBeforeAdd);
        switchCompatDeleteBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isDeleteBeforeAdd  =isChecked;
                saveSettings();
            }
        });
    }


    // инициализируем переключатель ответчающий за работу кнопки Back как очистителя/удалитель фрагментов
    private void initSwitchBackAsRemove(View view) {
        SwitchCompat switchCompatBackAsRemove = view.findViewById(R.id.switchBackAsRemove);
        switchCompatBackAsRemove.setChecked(Settings.isBackAsRemove);
        switchCompatBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackAsRemove  =isChecked;
                saveSettings();
            }
        });
    }

    // инициализируем переключатель отвечающий за стек
    private void initSwitchBackStack(View view) {
        SwitchCompat switchCompatBackStack = view.findViewById(R.id.switchBackStack);
        switchCompatBackStack.setChecked(Settings.isBackStack);
        switchCompatBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackStack  =isChecked;
                saveSettings();
            }
        });
    }

    // сохраняем настройки, чтобы они были доступны после перезагрузки приложения
    private void saveSettings() {
        SharedPreferences shared = requireActivity().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(Settings.IS_BACK_STACK_USED, Settings.isBackStack);
        editor.putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, Settings.isDeleteBeforeAdd);
        editor.putBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, Settings.isBackAsRemove);
        editor.putBoolean(Settings.IS_ADD_FRAGMENT_USED, Settings.isAddFragment);
        editor.putBoolean(Settings.IS_REPLACE_FRAGMENT_USED, Settings.isReplaceFragment);
        editor.apply();
    }


    private void loadSettings(){
        SharedPreferences shared = requireActivity().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Settings.isBackAsRemove = shared.getBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT,false);
        Settings.isBackStack = shared.getBoolean(Settings.IS_BACK_STACK_USED,false);
        Settings.isAddFragment = shared.getBoolean(Settings.IS_ADD_FRAGMENT_USED,true);
        Settings.isReplaceFragment = shared.getBoolean(Settings.IS_REPLACE_FRAGMENT_USED,false);
        Settings.isDeleteBeforeAdd = shared.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD,false);
    }
}