package com.distribuidora.appandroid.DAO;

import android.content.Context;
import com.distribuidora.appandroid.Model.Cliente;
import java.util.List;

public class ClienteDAO {

    public long error;
    private Context contextDAO;

    public ClienteDAO(Context context) {
        contextDAO = context;
    }

    //Metodos para CRUD:  Create, Read, Update and Delete
    public void create(Cliente cliente) {
        AbstractDAO dao = new AbstractDAO(contextDAO);
        error = dao.insert(cliente);
    }
    public List<Cliente> readAll(){
        Cliente cliente = new Cliente();
        AbstractDAO dao = new AbstractDAO(contextDAO);
        return (List<Cliente>) dao.selectMany(cliente,null);
    }
    public void update(Cliente cliente) {
        //AbstractDAO dao = new AbstractDAO(contextDAO);
        //dao.insert(cliente);
    }
    public void delete(Cliente cliente) {
        //AbstractDAO dao = new AbstractDAO(contextDAO);
        //dao.delete(articulo,where);
    }

}
