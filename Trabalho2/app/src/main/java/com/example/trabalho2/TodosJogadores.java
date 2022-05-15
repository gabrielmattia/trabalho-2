package com.example.trabalho2;

import static com.example.trabalho2.MainActivity.db;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.trabalho2.databinding.FragmentThirdBinding;
import com.example.trabalho2.databinding.TodosJogadoresFragmentBinding;

import java.util.List;

public class TodosJogadores extends Fragment {

    private TodosJogadoresFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TodosJogadoresFragmentBinding.inflate(inflater, container, false);
        List<Jogador> todos = db.getAllJogadores();
        System.out.println(todos);
        ArrayAdapter times = new ArrayAdapter<Jogador>(TodosJogadores.this.getContext(), android.R.layout.simple_list_item_1,todos);

        binding.listJogadores.setAdapter(times);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}