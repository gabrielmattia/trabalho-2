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
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho2.databinding.SecondJogadoresFragmentBinding;

import java.util.List;

public class SecondJogadores extends Fragment {

    private SecondJogadoresFragmentBinding binding;

    public static String spinnerItem ;
    public Jogador jogador;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SecondJogadoresFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> todos = db.getAllSpinner();

        ArrayAdapter times = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,todos);

        binding.spinnerIdTime.setAdapter(times);
        binding.spinnerIdTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = binding.spinnerIdTime.getItemAtPosition(i).toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnShowAllJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NavHostFragment.findNavController(SecondJogadores.this).navigate(R.id.action_secondJogadores_to_todosJogadores);
            }
        });
        binding.btnUpdateJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NavHostFragment.findNavController(SecondJogadores.this).navigate(R.id.action_secondJogadores_to_updateJogador);
            }
        });
        binding.btnAddJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                List<String> todos = db.getAllSpinner();

                ArrayAdapter times = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,todos);

                binding.spinnerIdTime.setAdapter(times);
                binding.spinnerIdTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinnerItem = binding.spinnerIdTime.getItemAtPosition(i).toString();



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                try {
                    String nome = binding.editName.getText().toString();
                    String cpf = binding.editCpf.getText().toString();
                    String idade= binding.editIdade.getText().toString();

                    //System.out.println(nome, cpf ,idade ,spinnerItem.toString());
                    System.out.println(nome);
                    System.out.println(cpf);
                    System.out.println(idade);

                    System.out.println(spinnerItem);

                     String idTime = db.getIdTime(spinnerItem);
                     jogador = new Jogador(-1,Integer.parseInt(idTime),nome,cpf,Integer.parseInt(idade));
                }catch (Exception e){
                    Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();


                }
                boolean success=db.addOneJogador(jogador);


                Toast.makeText(view.getContext(), "success" + success, Toast.LENGTH_SHORT).show();






            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}