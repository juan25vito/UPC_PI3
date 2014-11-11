package com.distribuidora.appsbo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Articulo {

    String Codigo;
    String Descripcion;
    Context Context;

    public Articulo() {
    }
    public Articulo(String codigo, String descripcion, Context context) {
        Codigo = codigo;
        Descripcion = descripcion;
        Context = context;
    }

    public String getCodigo() {
        return Codigo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public Context getContext() {
        return Context;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
    public void setContext(Context context) {
        Context = context;
    }

    public void add() {

        AdminSQLiteOpenHelper distribuidora;
        SQLiteDatabase bd;
        ContentValues registro;

        distribuidora = new AdminSQLiteOpenHelper(Context,"distribuidora",null,1);
        bd = distribuidora.getWritableDatabase();
        registro = new ContentValues();
        registro.put("codigo",Codigo);
        registro.put("descripcion", Descripcion);

        bd.insert("articulo", null, registro);
        bd.close();

    }
    public void add(Articulo articulo) {

        AdminSQLiteOpenHelper distribuidora;
        SQLiteDatabase bd;
        distribuidora = new AdminSQLiteOpenHelper(null,"distribuidora",null,1);
        bd = distribuidora.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("codigo", articulo.getCodigo());
        values.put("descripcion", articulo.getDescripcion());

        bd.insert("articulo", null, values);
        bd.close();
    }
}
