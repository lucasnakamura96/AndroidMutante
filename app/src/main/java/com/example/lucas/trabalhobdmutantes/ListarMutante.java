package com.example.lucas.trabalhobdmutantes;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarMutante extends ListActivity {


    private List<Mutante> mutantes = new ArrayList<Mutante>();
    private MutantesAdapter mutantesAdapter;
    private MutanteOperations mutanteDBoperations;
    ListView list;
    public static final int REQUEST_EDICAO =0;
    public static final int REQUEST_SALVOU =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mutante);

        mutanteDBoperations = new MutanteOperations(this);

        lerDados();

//        list = (ListView) findViewById(R.id.listMutante);
//
//        ArrayAdapter<Mutante> adapter = new ArrayAdapter<Mutante>(this, android.R.layout.simple_list_item_1, values);
//
//        list.setAdapter(adapter);
    }

    public void lerDados(){
        mutanteDBoperations.open();
        mutantes = mutanteDBoperations.getAllMutantes();

        if(mutantes.size() > 0){
            if(mutantesAdapter == null){
                mutantesAdapter = new MutantesAdapter(this, mutantes) {
                    @Override
                    public void edita(Mutante mutante) {
                        Intent intent = new Intent(getApplicationContext(), CadastroMutante.class);
                        intent.putExtra("mutante", mutante);
                        startActivityForResult(intent, REQUEST_EDICAO);
                    }

                    @Override
                    public void excluir(Mutante mutante) {
                        mutanteDBoperations.deleteMutante(mutante);
                        Toast.makeText(ListarMutante.this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };
                setListAdapter(mutantesAdapter);
            }else 
                mutantesAdapter.novosDados(mutantes);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_EDICAO)
            if(resultCode == REQUEST_SALVOU)
                lerDados();
    }

    @Override
    protected void onResume(){
        mutanteDBoperations.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        mutanteDBoperations.close();
        super.onPause();
    }


}
