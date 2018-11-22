package com.example.lucas.trabalhobdmutantes;

public class Habilidade {
    private int id;
    private String descricao;

    public Habilidade() {
    }


    public Habilidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
