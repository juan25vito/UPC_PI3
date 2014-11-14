package com.distribuidora.appandroid.Controller;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.distribuidora.appandroid.DAO.ClienteDAO;
import com.distribuidora.appandroid.Model.Cliente;
import com.distribuidora.appandroid.R;


public class ClienteActivity extends Activity implements View.OnClickListener {

    private EditText editText1,editText2,editText3,editText4,editText5,editText6;
    private Spinner spinner1;
    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        // get the action bar
        ActionBar actionBar = getActionBar();
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        editText1= (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
        editText3= (EditText) findViewById(R.id.editText3);
        editText4= (EditText) findViewById(R.id.editText4);
        editText5= (EditText) findViewById(R.id.editText5);
        editText6= (EditText) findViewById(R.id.editText6);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        String []opciones=new String[2];
        opciones[0] = "Contado";opciones[1] = "Credito";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //using xml
        //creates a menu inflater
        MenuInflater inflater = getMenuInflater();
        //generates a Menu from a menu resource file
        //R.menu.main_menu represents the ID of the XML resource file
        inflater.inflate(R.menu.toolbar_opciones, menu);

        /*
        //the menu option text is defined in resources
        menu.add(R.string.aboutOption);

        //get a SubMenu reference
        SubMenu sm = menu.addSubMenu("Options...");
        //add menu items to the submenu
        sm.add("Theme");
        sm.add("Settings");

        //it is better to use final variables for IDs than constant values
        //menu.add(Menu.NONE,1,Menu.NONE,"Exit");

        //get the MenuItem reference
        MenuItem item =
                menu.add(Menu.NONE,1,Menu.NONE,R.string.exitOption);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

               finish();
                return true;
            }
        });
        //set the shortcut
              item.setShortcut('5', 'x');

        //the menu option text is defined as constant String
        menu.add("Restart");
        */
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //check selected menu item
        if(item.getItemId() == R.id.exit){
            //close the Activity
            this.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO(view.getContext()/*this.getActivity()*/);
                cliente.setCodigo(editText1.getText().toString());
                cliente.setDocumento(editText2.getText().toString());
                cliente.setRazonSocial(editText3.getText().toString());
                cliente.setCondicion(spinner1.getSelectedItem().toString());
                cliente.setDireccion(editText4.getText().toString());
                cliente.setTelefono(editText5.getText().toString());
                cliente.setEmail(editText6.getText().toString());
                clienteDAO.create(cliente);
                if (clienteDAO.error==-1){
                    Toast.makeText(view.getContext(), "Se ha producido un error", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "Creado Exitosamente", Toast.LENGTH_LONG).show();
                    this.finish();
                }
                break;
            case R.id.button2:
                //close the Activity
                this.finish();
                break;
        }
    }
}
