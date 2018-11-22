package com.example.lucas.trabalhobdmutantes;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class PesquisarMutante extends AppCompatActivity {

    private MutanteOperations mutanteDBoperations;
    RadioGroup rgPesquisar;
    RadioButton rbNome, rbHabilidade;
    TextView txtResultado, tx2;
    EditText edtPesquisar;
    ListView list;
    Button btnPesquisar;
    private List mutantes, achados;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_mutante);
        mutanteDBoperations = new MutanteOperations(this);
        mutanteDBoperations.open();
        alert = new AlertDialog.Builder(this);
        achados = new ArrayList<Mutante>();
        rgPesquisar = (RadioGroup) findViewById(R.id.rgPesquisar);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        mutantes = new ArrayList();


        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (rgPesquisar.getCheckedRadioButtonId()) {
                    case R.id.rbNome:
                        

                    break;

                    case R.id.rbHabilidade:
                        if(!(edtPesquisar.getText().toString().equals("") )) {
                            List<Mutante> mutantes = mutanteDBoperations.getAllMutantes();
                            for (Mutante m : mutantes) {
                                if (m.getHabilidade().equalsIgnoreCase(edtPesquisar.getText().toString())) {
                                    String nome = m.getNome();
                                    String hab = m.getHabilidade();
                                    txtResultado.append("Nome: ");
                                    txtResultado.append(nome);
                                    txtResultado.append("Habilidad: ");
                                    txtResultado.append(hab);
                                    txtResultado.append("\n");
                                }
                            }
                        } else {
                            alert.setTitle("Erro!")
                                    .setMessage("Favor preenhcer um dos campos")
                                    .setPositiveButton(
                                            "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                            alert.show();
                        }

                    break;
                }
            }
        });

    }

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


}
