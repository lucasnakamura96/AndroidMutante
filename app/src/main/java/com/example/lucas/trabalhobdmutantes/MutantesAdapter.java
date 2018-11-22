package com.example.lucas.trabalhobdmutantes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public abstract class MutantesAdapter extends BaseAdapter {
    private Mutante mutante;
    private List<Mutante> mutantes;
    private LayoutInflater inflater;

    public MutantesAdapter(Context context, List<Mutante> mutantes) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mutantes = mutantes;
    }

    public void novosDados(List<Mutante> mutantes){
        this.mutantes = mutantes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return mutantes.size();
    }

    @Override
    public Object getItem(int position){
        return mutantes.get(position);
    }

    @Override
    public long getItemId(int arg0){
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2){
        View v = inflater.inflate(R.layout.item_mutante, null);
        ((TextView) (v.findViewById(R.id.textViewNome))).setText(mutantes.get(position).getNome());
        ((TextView) (v.findViewById(R.id.textViewHabilidade))).setText(mutantes.get(position).getHabilidade());

        ((Button)(v.findViewById(R.id.buttonEdit))).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                edita(mutantes.get(position));
            }
        });
        ((Button)(v.findViewById(R.id.buttonExcluir))).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                excluir(mutantes.get(position));
            }
        });
        return v;
    }

    public abstract void edita(Mutante mutante);
    public abstract void excluir(Mutante mutante);

}
