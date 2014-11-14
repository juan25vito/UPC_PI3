package com.distribuidora.appandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.distribuidora.appandroid.DAO.ArticuloDAO;
import com.distribuidora.appandroid.DAO.ClienteDAO;
import com.distribuidora.appandroid.Model.Articulo;
import com.distribuidora.appandroid.Model.Cliente;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button button1;
    private Button button2;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_home, container, false);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return view;
        /*
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
        */
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this.getActivity(),"Button is clicked!", Toast.LENGTH_LONG).show();
        switch(view.getId()) {
            case R.id.button1:
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO(this.getActivity());
                cliente.setCodigo("C20100000002");cliente.setDocumento("20100000002");
                cliente.setRazonSocial("Minimarket luchito SA");cliente.setCondicion("Contado");
                cliente.setDireccion("Av AAAA 941");cliente.setTelefono("7171234");
                cliente.setEmail("123@123.com");
                clienteDAO.create(cliente);
                Toast.makeText(this.getActivity(), "Insertar Cliente!!!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                Articulo articulo = new Articulo();
                ArticuloDAO articuloDAO = new ArticuloDAO(this.getActivity());
                articulo.setCodigo("A00003");articulo.setDescripcion("Fideo");
                articuloDAO.create(articulo);
                Toast.makeText(this.getActivity(), String.valueOf(articuloDAO.error), Toast.LENGTH_LONG).show();
                //Toast.makeText(this.getActivity(), "Insert!!!", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
