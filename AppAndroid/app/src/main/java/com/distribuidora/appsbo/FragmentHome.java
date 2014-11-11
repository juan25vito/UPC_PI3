package com.distribuidora.appsbo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.distribuidora.appsbo.distribuidora.R;
import com.distribuidora.appsbo.model.Articulo;

public class FragmentHome extends Fragment implements View.OnClickListener{
    private Button button1;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_home, container, false);

        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(this);

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
                /*
                AdminSQLiteOpenHelper distribuidora;
                SQLiteDatabase bd;
                ContentValues registro;

                distribuidora = new AdminSQLiteOpenHelper(this.getActivity(),"distribuidora",null,1);
                bd = distribuidora.getWritableDatabase();
                registro = new ContentValues();
                registro.put("codigo", "A00001");
                registro.put("descripcion", "Arroz");

                bd.insert("articulo", null, registro);
                bd.close();
                */

                Articulo item=new Articulo();
                item.setCodigo("A00002");
                item.setDescripcion("Aceite");
                item.setContext(this.getActivity());
                item.add();
                Toast.makeText(this.getActivity(), "Importado!!!", Toast.LENGTH_LONG).show();

                break;
        }
    }
}
