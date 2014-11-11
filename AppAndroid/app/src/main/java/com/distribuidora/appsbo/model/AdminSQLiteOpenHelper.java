package com.distribuidora.appsbo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table articulo (codigo text primary key, descripcion text)");
		//db.execSQL("create table clientes(codigo text primary key, nombre text, documento text)");
		//db.execSQL("create table articulos(codigo text primary key, descripcion text, unidad text, precio real)");
		//db.execSQL("create table orden(numero integer primary key, fecha text, codigo text, importe real, impuesto real, monto real)");
		//db.execSQL("create table ordenDetalle(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
		//db.execSQL("create table ordenDetalleTemp(numero integer, linea int, codigo text, cantidad int, precio real, importe real)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists articulo");
        db.execSQL("create table articulo (codigo text primary key, descripcion text)");
        //db.execSQL("drop table if exists clientes");
		//db.execSQL("create table clientes(codigo text primary key, nombre text, documento text)");
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
