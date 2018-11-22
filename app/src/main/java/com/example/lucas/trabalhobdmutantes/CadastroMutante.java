package com.example.lucas.trabalhobdmutantes;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroMutante extends AppCompatActivity {

    private MutanteOperations mutanteDBoperations;

    TextView text1, text2, text3;
    private EditText textNome;
    private EditText textHab;
    ArrayAdapter adapter;
    private Mutante mutante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_mutante);

        textNome = (EditText) findViewById(R.id.editTextNome);
        textHab = (EditText) findViewById(R.id.editTextHabilidade);

        mutanteDBoperations = new MutanteOperations(this);

        Intent intent = getIntent();
        if(intent != null){
            mutante = (Mutante)intent.getSerializableExtra("mutante"); // possivel erro
            if(mutante != null){
                textNome.setText(mutante.getNome());
                textHab.setText(mutante.getHabilidade());
            }
        }


    }

    public void addMutante(View view) {
        //ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        //AlertDialog.Builder alert = new AlertDialog.Builder(this);
        mutanteDBoperations.open();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (mutante != null) {
            mutanteDBoperations.atualizaMutante(mutante.getId(), textNome.getText().toString(), textHab.getText().toString());
            Toast.makeText(CadastroMutante.this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }else {
            try {
                EditText textNome = (EditText) findViewById(R.id.editTextNome);
                EditText textHab = (EditText) findViewById(R.id.editTextHabilidade);

                if (!(textNome.getText().toString().equals("") || textHab.getText().toString().equals(""))) {
                    List<Mutante> mutantes = mutanteDBoperations.getAllMutantesC();
                    for (Mutante m : mutantes) {
                        if (m.getNome().equalsIgnoreCase(textNome.getText().toString())) {
                            //Toast.makeText(CadastroMutante.this, "Ja existe um Mutante cadastrado com esse nome!", Toast.LENGTH_SHORT).show();
                            String s = "Mutante ja cadastrado!";
                            throw new Exception(s);
                        }
                    }
                } else {
                    String s = "Preencher todos os campos!";
                    throw new Exception(s);
                    //Toast.makeText(CadastroMutante.this, "Favor preencher todos os campos!", Toast.LENGTH_SHORT).show();
                }
                Mutante mutan = mutanteDBoperations.addMutante(textNome.getText().toString(), textHab.getText().toString());
                Toast.makeText(CadastroMutante.this, "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();


                mutanteDBoperations.close();
                setResult(ListarMutante.REQUEST_SALVOU);
                finish();
            } catch (Exception e) {
                builder.setTitle("Erro")
                        .setMessage(e.getMessage())
                        .setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                builder.show();
            }
        }

    }
//        boolean resp = mutanteDBoperations.verificaMutante(textNome.getText().toString());
//        if(!resp){
//            Toast.makeText(CadastroMutante.this, "Já existe um mutante com este nome!", Toast.LENGTH_SHORT).show();
//        }else{
//            Mutante mut = mutanteDBoperations.addMutante(textNome.getText().toString(), textHab.getText().toString());
//            Toast.makeText(CadastroMutante.this, "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();
//            //adapter.add(mut);
//        }
    }

//
//    @Override
//    protected void onResume(){
//        mutanteDBoperations.open();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause(){
//        mutanteDBoperations.close();
//        super.onPause();
//    }

