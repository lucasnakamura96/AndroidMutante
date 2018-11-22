package com.example.lucas.trabalhobdmutantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class MutantesBDWrapper extends SQLiteOpenHelper {

        //Tabela Mutante
        public static final String MUTANTES = "Mutantes";
        public static final String MUTANTE_ID = "id_mutante";
        public static final String MUTANTE_NOME = "nome_mutante";
        public static final String MUTANTE_HABILIDADE = "habilidade_mutante";
        //public static final String MUTANTE_HABILIDADE = "habilidade_mutante";


        //Tabela Associativa
        //public static final String HAB_MUT = "Habilidades_mutantes";
        //public static final String ID_HABILIDADE = "id_habilidade";
        //public static final String ID_MUTANTE = "id_mutante";


        //Tabela Habilidades
        //public static final String HABILIDADES = "Habilidades";
        //public static final String HABILIDADE_ID = "id_habilidade";
        //public static final String HABILIDADE_DESC = "desc_habilidade";
        //public static final String HABILIDADE_MUTANTE = "habilidade_mutante";

        //Constraints
        //public static final String FK_HABILIDADE = "fk_habilidade";
        //public static final String FK_MUTANTE = "fk_mutante";


        private static final String DATABASE_NAME = "Trabalho.db";
        private static final int DATABASE_VERSION = 1;

        //Criação Statament SQLite
        private static final String DATABASE_CREATE_MUT = "create table IF NOT EXISTS " + MUTANTES  + " ("
                + MUTANTE_ID + " integer primary key autoincrement, "
                + MUTANTE_NOME + " text not null, "
                + MUTANTE_HABILIDADE + " text not null); ";


        public MutantesBDWrapper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE_MUT);
            db.execSQL("INSERT INTO Mutantes(`nome_mutante`, `habilidade_mutante`) values ('Wolverine','Regeneração')");
            //db.execSQL("CREATE TABLE IF NOT EXISTS Mutantes(id_mutante integer PRIMARY KEY autoincrement, nome_mutante text not null)");
                //db.execSQL("CREATE TABLE IF NOT EXISTS Habilidades(id_habilidade integer PRIMARY KEY autoincrement, desc_habilidade text not null)");
                //db.execSQL("CREATE TABLE IF NOT EXISTS Habilidades_mutantes(id_habilidade integer not null REFERENCES Habilidades(id_habilidade), id_mutante integer not null references Mutante(id_mutante))");

                //db.execSQL("INSERT INTO Habilidades(`desc_habilidade`) values ('Regeneração')");
                //db.execSQL("INSERT INTO Habilidades(`desc_habilidade`) values ('Força')");
                //db.execSQL("INSERT INTO Habilidades(`desc_habilidade`) values ('Garras')");
                //db.execSQL("INSERT INTO Habilidades(`desc_habilidade`) values ('Reflexo')");
                //db.execSQL("INSERT INTO Habilidades(`desc_habilidade`) values ('Agilidade')");
         }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + MUTANTES);
            onCreate(db);
        }

    }

