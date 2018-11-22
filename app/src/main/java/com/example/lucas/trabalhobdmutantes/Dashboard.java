package com.example.lucas.trabalhobdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button cadastrar;
    Button listar;
    Button pesquisarNome;
    Button pesquisarHabilidade;
    Button sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cadastrar = (Button) findViewById(R.id.cadastrar);
        listar = (Button) findViewById(R.id.listar);
        pesquisarNome = (Button) findViewById(R.id.pesquisarMutante);
        sair = (Button) findViewById(R.id.sair);

    }

    public void telaCadastrar(View view){
        Intent intent = new Intent(this, CadastroMutante.class);
        startActivity(intent);
    }

    public void listarMutante(View view){
        Intent intent = new Intent(this, ListarMutante.class);
        startActivity(intent);
    }

    public void pesquisarMutante(View view){
        Intent intent = new Intent(this, PesquisarMutante.class);
        startActivity(intent);
    }

    public void close(View view){
        System.exit(0);
    }


    public void sairAplicativo(View view){

    }

}
