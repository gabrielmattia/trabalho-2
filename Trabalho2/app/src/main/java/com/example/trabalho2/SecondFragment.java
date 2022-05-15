package com.example.trabalho2;

import static com.example.trabalho2.MainActivity.db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.trabalho2.databinding.FragmentSecondBinding;

import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private EditText descricao;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.idBtnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Time time;
                 try {
                     String descricao = binding.editDescricao.getText().toString();
                     time = new Time(-1, descricao);
                     //Toast.makeText(view.getContext(), descricao, Toast.LENGTH_SHORT).show();
                 }catch (Exception e){
                     Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();
                     time = new Time(-1, "error");

                 }

                  boolean success=db.addOne(time);


                 Toast.makeText(view.getContext(), "success" + success, Toast.LENGTH_SHORT).show();
                List<Time> todos = db.getAll();
                ArrayAdapter times = new ArrayAdapter<Time>(view.getContext(), android.R.layout.simple_list_item_1,todos);

                binding.viewTime.setAdapter(times);

            }
        });
        binding.btnShowTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Time> todos = db.getAll();
                ArrayAdapter times = new ArrayAdapter<Time>(v.getContext(), android.R.layout.simple_list_item_1,todos);

                binding.viewTime.setAdapter(times);
            }
        });
        binding.btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}