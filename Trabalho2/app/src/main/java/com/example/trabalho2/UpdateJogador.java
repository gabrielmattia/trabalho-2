package com.example.trabalho2;

import static com.example.trabalho2.MainActivity.db;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.trabalho2.databinding.UpdateJogadorFragmentBinding;

import java.util.List;

public class UpdateJogador extends Fragment {

    private UpdateJogadorFragmentBinding binding;
    public static String spinnerItem ;
    public static String spinnerItemTime ;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = UpdateJogadorFragmentBinding.inflate(inflater, container, false);
        List<String> todos = db.getAllJogadorSpinner();

        ArrayAdapter jogadores = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,todos);

        binding.spinnerUpdateJogador.setAdapter(jogadores);
        binding.spinnerUpdateJogador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = binding.spinnerUpdateJogador.getItemAtPosition(i).toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return binding.getRoot();

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnDeletarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println( db.deleteJogador( db.getIdJogador(spinnerItem)));
                //db.deleteJogador( db.getIdJogador(spinnerItem));
            }
        });

        List<String> todos = db.getAllSpinner();

        ArrayAdapter times = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,todos);

        binding.spinnerTime.setAdapter(times);
        binding.spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItemTime = binding.spinnerTime.getItemAtPosition(i).toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        binding.btnUpdateJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              long success =  db.updateJogador(spinnerItem ,binding.editName.getText().toString(), binding.editCpf.getText().toString(), binding.editIdade.getText().toString(),spinnerItemTime);
                if(success==1){
                    Toast.makeText(view.getContext(), "Atualizado com sucesso" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Não foi possivel atualizar" , Toast.LENGTH_SHORT).show();
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