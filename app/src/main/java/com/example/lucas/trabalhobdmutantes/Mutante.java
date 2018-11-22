package com.example.lucas.trabalhobdmutantes;

import java.io.Serializable;
import java.util.ArrayList;

public class Mutante implements Serializable {
    private static final long serialVersionUID = 163383301108440384L;
    private int id;
    private String nome;
    private String habilidade;

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }
    //private ArrayList[] habilidade;

    public Mutante() {
    }


    public Mutante(int id, String nome, String habilidade) {
        this.id = id;
        this.nome = nome;
        this.habilidade = habilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //public ArrayList[] getHabilidade() {
    //    return habilidade;
    //}

    //public void setHabilidade(ArrayList[] habilidade) {
    //    this.habilidade = habilidade;
   // }

}
