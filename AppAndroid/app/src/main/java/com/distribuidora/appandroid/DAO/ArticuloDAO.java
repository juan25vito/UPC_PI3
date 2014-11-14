package com.distribuidora.appandroid.DAO;

import android.content.Context;
import com.distribuidora.appandroid.Model.Articulo;
import java.util.List;

public class ArticuloDAO {

    public long error;
    private Context contextDAO;

    public ArticuloDAO(Context context) {
        contextDAO=context;
    }

    //Metodos para CRUD:  Create, Read, Update and Delete
    public void create(Articulo articulo) {
        AbstractDAO dao = new AbstractDAO(contextDAO);
        error = dao.insert(articulo);
        //error = abstractDAO.insert(articulo);
    }
    public List<Articulo> readAll(){
        Articulo articulo=new Articulo();
        AbstractDAO dao = new AbstractDAO(contextDAO);
        return (List<Articulo>) dao.selectMany(articulo,null);
    }
    public void update(Articulo articulo) {
        //AbstractDAO dao = new AbstractDAO(contextDAO);
        //abstractDAO.insert(articulo);
    }
    public void delete(Articulo articulo) {
        //AbstractDAO dao = new AbstractDAO(contextDAO);
        //dao.delete(articulo,where);
    }

}
