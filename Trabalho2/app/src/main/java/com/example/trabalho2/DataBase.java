package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final String ID = "id";
    public static final String ID_TIME = "idTime";
    public static final String DESCRICAO = "descricao";
    public static final String TIME = "time";
    public static final String JOGADOR = "jogador";
    public static final String ID_TIME1 = "idTime";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String NASCIMENTO = "nascimento";

    public DataBase(@Nullable Context context) {
        super(context, "jogos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTime = "CREATE TABLE " + TIME + " ( " + ID_TIME + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DESCRICAO + " STRING);" ;
        String createTableJogador = "CREATE TABLE " + JOGADOR + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                NOME + " STRING, " +
                CPF + " STRING, " +
                NASCIMENTO + " INTEGER," +
                ID_TIME1 + " INTEGER," +
                "FOREIGN KEY ("+ID_TIME+") REFERENCES "+TIME+"("+ID+") );";

        db.execSQL(createTableTime);
        db.execSQL(createTableJogador);

    }

    public boolean addOne (Time time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DESCRICAO, time.getDescricao());
        long insert=db.insert(TIME, null, cv);

        if(insert==-1){
            return false;
        }else{
            return true;
        }


    }
    public boolean addOneJogador (Jogador jogador){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOME, jogador.getNome());
        cv.put(CPF, jogador.getCpf());
        cv.put(ID_TIME1, jogador.getIdTime());
        cv.put(NASCIMENTO, jogador.getAnoNascimento());
        long insert=db.insert(JOGADOR, null, cv);

        if(insert==-1){
            return false;
        }else{
            return true;
        }


    }
    public String getIdTime (String time){
        String query = String.format("SELECT %s FROM %s WHERE %s = ?", ID_TIME, TIME, DESCRICAO );
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(time)});
        cursor.moveToFirst();
        System.out.println(cursor.getString(0));
        return  cursor.getString(0);
    }
    public String getIdJogador (String nome){
        String query = String.format("SELECT id FROM %s WHERE %s = ?;", JOGADOR, NOME );
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(nome)});
        cursor.moveToFirst();

        return  cursor.getString(0);
    }
    public boolean deleteJogador (String id){


        SQLiteDatabase db= this.getWritableDatabase();


        return db.delete(JOGADOR, ID + "=?", new String[]{id}) > 0;
    }
    public List<Time> getAll(){

        List<Time> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TIME;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);

                String descricao = cursor.getString(1);


                Time time = new Time(id, descricao);

                returnList.add(time);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;




    }
    public List<Jogador> getAllJogadores(){

        List<Jogador> returnList = new ArrayList<>();
        String query = String.format("SELECT * FROM %s;",  JOGADOR );

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);



        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                int idTime = cursor.getInt(4); //idTime
                String  nome= cursor.getString(1);
                String  cpf= cursor.getString(2);
                String  idade= cursor.getString(3);

                System.out.println("id " +idade);
                if(idade==null){
                    idade="0";
                }

                Jogador jogador = new Jogador(id, idTime,nome,cpf,Integer.parseInt(idade));

                returnList.add(jogador);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return returnList;




    }
    public List<String> getAllJogadorSpinner(){

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT nome FROM " + JOGADOR + ";";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{


                String descricao = cursor.getString(0);




                returnList.add(descricao);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public List<String> getAllSpinner(){

        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT descricao FROM " + TIME + ";";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{


                String descricao = cursor.getString(0);




                returnList.add(descricao);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;




    }
    public boolean delete (String descricao){

        SQLiteDatabase db= this.getWritableDatabase();


        return db.delete(TIME, ID_TIME + "=?", new String[]{descricao}) > 0;
    }
    public long update (String descricao, String novaDescricao){

        String query = String.format("SELECT %s FROM %s WHERE %s = ?", ID_TIME, TIME, DESCRICAO );
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(descricao)});
        cursor.moveToFirst();
        System.out.println(cursor.getString(0));
        ContentValues values = new ContentValues();
        values.put(DESCRICAO, novaDescricao);

        String[] args = {String.valueOf(cursor.getInt(0))};
        long retorno = db.update(TIME, values,"idTime=?", args);
        db.close();
        return  retorno;
    }
    public long updateJogador (String nomeAntigo, String nome,String cpf, String idade,String time){

        String query = String.format("SELECT %s FROM %s WHERE %s = ?", ID, JOGADOR, NOME );
        String idTime = String.format("SELECT %s FROM %s WHERE %s = ?", ID_TIME, TIME, DESCRICAO );
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(nomeAntigo)});
        Cursor cursor2 = db.rawQuery(idTime, new String[]{String.valueOf(time)});

        cursor.moveToFirst();
        cursor2.moveToFirst();



        ContentValues values = new ContentValues();
        values.put(NOME, nome);
        values.put(CPF, cpf);
        values.put(NASCIMENTO, Integer.parseInt(idade));
        values.put(ID_TIME1, Integer.parseInt(cursor2.getString(0)));






        String[] args = {String.valueOf(cursor.getInt(0))};

        long retorno = db.update(JOGADOR, values,"id=?", args);
        db.close();


       return  retorno;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
