package com.distribuidora.appandroid.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "distribuidora";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public SQLiteDatabase getDatabase() {
        //return getReadableDatabase();
        return getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cliente (codigo text primary key, documento text, razonSocial text, condicion text, direccion text, telefono text, email text)");
        db.execSQL("create table articulo (codigo text primary key, descripcion text, unidadDeMedida text)");
        db.execSQL("create table listaDePrecios (codigo text primary key, Nombre text)");
        db.execSQL("create table ListaArticulo (codigoLista text, CodigoArticulo text, precio real)");
        //db.execSQL("create table articulos(codigo text primary key, descripcion text, unidad text, precio real)");
        //db.execSQL("create table orden(numero integer primary key, fecha text, codigo text, importe real, impuesto real, monto real)");
        //db.execSQL("create table ordenDetalle(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
        //db.execSQL("create table ordenDetalleTemp(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists cliente");
        db.execSQL("create table cliente (codigo text primary key, documento text, razonSocial text, condicion text, direccion text, telefono text, email text)");
        db.execSQL("drop table if exists articulo");
        db.execSQL("create table articulo (codigo text primary key, descripcion text, unidadDeMedida text)");
        db.execSQL("drop table if exists listaDePrecios");
        db.execSQL("create table listaDePrecios (codigo text primary key, Nombre text)");
        db.execSQL("drop table if exists ListaArticulo");
        db.execSQL("create table ListaArticulo (codigoLista text, CodigoArticulo text, precio real)");
        //db.execSQL("drop table if exists articulos");
        //db.execSQL("create table articulos(codigo text primary key, descripcion text, unidad text, precio real)");
        //db.execSQL("drop table if exists orden");
        //db.execSQL("create table orden(numero integer primary key, fecha text, codigo text, importe real, impuesto real, monto real)");
        //db.execSQL("drop table if exists ordenDetalle");
        //db.execSQL("create table ordenDetalle(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
        //db.execSQL("drop table if exists ordenDetalleTemp");
        //db.execSQL("create table ordenDetalleTemp(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
    }

}