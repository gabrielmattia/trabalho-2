package com.example.trabalho2;

import static com.example.trabalho2.MainActivity.db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.trabalho2.databinding.FragmentThirdBinding;

import java.util.List;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    public static String spinnerItem ;
    public static int spinnerId ;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> todos = db.getAllSpinner();

        ArrayAdapter times = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,todos);

        binding.spinner.setAdapter(times);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




            @Override
              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 spinnerItem = binding.spinner.getItemAtPosition(i).toString();



              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               long success = db.update(spinnerItem , binding.novaDescricao.getText().toString());

                if(success==1){
                    Toast.makeText(view.getContext(), "Atualizado com sucesso" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Não foi possivel atualizar" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnDeleteTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean success = db.delete( db.getIdTime(spinnerItem));

                if(success){
                    Toast.makeText(view.getContext(), "Deletado com sucesso" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Não foi possivel deletar" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}