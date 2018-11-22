package com.example.lucas.trabalhobdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class MutanteOperations {
    //Campos DataBase
    private MutantesBDWrapper dbHelper;
    private String[] MUTANTE_COLUNAS = {MutantesBDWrapper.MUTANTE_ID, MutantesBDWrapper.MUTANTE_NOME, MutantesBDWrapper.MUTANTE_HABILIDADE};
    private String[] NOME_HABILIDADE = {MutantesBDWrapper.MUTANTE_NOME, MutantesBDWrapper.MUTANTE_HABILIDADE};
    //private String[] HABILIDADE_COLUNAS = {MutantesBDWrapper.HABILIDADE_ID, MutantesBDWrapper.HABILIDADE_DESC};
    private SQLiteDatabase database;

    public MutanteOperations(Context context){
        dbHelper = new MutantesBDWrapper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void openR() throws SQLException{
        database = dbHelper.getReadableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public Mutante addMutante(String nome, String habilidade){
        ContentValues values = new ContentValues();
        values.put(MutantesBDWrapper.MUTANTE_NOME, nome);
        values.put(MutantesBDWrapper.MUTANTE_HABILIDADE, habilidade);
        //executa o insert e pega o id gerado pelo banco
        long mutanteId = database.insert(MutantesBDWrapper.MUTANTES, null, values);

        //seleciona o registro dos mutantes com a condição where
        Cursor cursor = database.query(MutantesBDWrapper.MUTANTES,MUTANTE_COLUNAS, MutantesBDWrapper.MUTANTE_ID+ " = " + mutanteId, null, null,
                null, null);
        cursor.moveToFirst();
        Mutante newComment = parseMutanteC(cursor);
        cursor.close();
        return newComment;
    }

    public void atualizaMutante(int id, String nome, String habilidade){
        ContentValues values = new ContentValues();
        values.put(MutantesBDWrapper.MUTANTE_ID, id);
        values.put(MutantesBDWrapper.MUTANTE_NOME, nome);
        values.put(MutantesBDWrapper.MUTANTE_HABILIDADE, habilidade);
        database.update(MutantesBDWrapper.MUTANTES, values, MutantesBDWrapper.MUTANTE_ID + "=" + id, null);
    }


    public void deleteMutante(Mutante comment){
        long id = comment.getId();
        System.out.println("Removido Id: " + id);
        database.delete(MutantesBDWrapper.MUTANTES, MutantesBDWrapper.MUTANTE_ID + " = " + id, null);
    }


    //GET sem as habilidades;
    public List getAllMutantesC(){
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutantesBDWrapper.MUTANTES,
                NOME_HABILIDADE, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Mutante mutante = parseMutanteC(cursor);
            mutantes.add(mutante);
            //mutantes.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public List getAllMutantes(){
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutantesBDWrapper.MUTANTES,
                MUTANTE_COLUNAS, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            //mutantes.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    private Mutante parseMutante(Cursor cursor) {
        Mutante mutante = new Mutante();
        mutante.setId((cursor.getInt(0)));
        mutante.setNome((cursor.getString(1)));
        mutante.setHabilidade((cursor.getString(2)));
        return mutante;
    }


    private Mutante parseMutanteC(Cursor cursor) {
        Mutante mutante = new Mutante();
        //mutante.setId((cursor.getInt(0)));
        mutante.setNome((cursor.getString(0)));
        mutante.setHabilidade((cursor.getString(1)));
        return mutante;
    }
//
//    public Cursor getMutanteByNome(String nome){
//        return database.query(MutantesBDWrapper.MUTANTES,NOME_HABILIDADE, MutantesBDWrapper.MUTANTE_NOME + " = " + nome, null, null,
//                null, null);
//    }
//
//    public List getMutanteByHabilidade(String habilidade){
//        List mutantes = new ArrayList();
//        Cursor cursor = database.query(MutantesBDWrapper.MUTANTES,NOME_HABILIDADE, MutantesBDWrapper.MUTANTE_HABILIDADE+ " = " + habilidade, null, null,
//                null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Mutante mutante = parseMutante(cursor);
//            mutantes.add(mutante);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return mutantes;
//    }
//
//    public Boolean verificaMutante(String nome){
//        Cursor cursor = database.query(MutantesBDWrapper.MUTANTES,
//                null, MutantesBDWrapper.MUTANTE_NOME+ " = " + nome,null, null, null, null);
//        if(cursor.moveToFirst()){
//            cursor.close();
//            return true;
//        }else{
//            cursor.close();
//            return false;
//        }
//    }

//    public List getHabilidades(){
//        List habilidades = new ArrayList();
//        Cursor cursor = database.query(MutantesBDWrapper.HABILIDADES,HABILIDADE_COLUNAS, null,null ,null,null,null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Habilidade habilidade = parseHabilidade(cursor);
//            habilidades.add(habilidade);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return habilidades;
//    }

//    private Habilidade parseHabilidade(Cursor cursor) {
//        Habilidade habilidade = new Habilidade();
//        habilidade.setId((cursor.getInt(0)));
//        habilidade.setDescricao((cursor.getString(1)));
//        return habilidade;
//    }


}
