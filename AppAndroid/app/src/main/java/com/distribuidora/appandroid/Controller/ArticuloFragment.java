package com.distribuidora.appandroid.Controller;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.distribuidora.appandroid.DAO.ArticuloDAO;
import com.distribuidora.appandroid.Model.Articulo;
import com.distribuidora.appandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ArticuloFragment extends ListFragment implements View.OnClickListener {

    public ArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Articulo articulo;
        ArticuloDAO articuloDAO=new ArticuloDAO(this.getActivity());
        List articulos = new ArrayList();
        articulos = articuloDAO.readAll();

        ArrayList<String> columna1 = new ArrayList<String>();
        //ArrayList<String> columna2 = new ArrayList<String>();
        for (int i = 0, z = articulos.size()/*Cheeses.CHEESES.length*/ ; i < z ; i++) {
            //items.add(articulos.listIterator(i).getClass(). Cheeses.CHEESES[i]);
            articulo=(Articulo)articulos.get(i);
            columna1.add(articulo.getCodigo()+"\t"+articulo.getDescripcion());
        }
        // Set the ListAdapter
        setListAdapter(new PopupAdapter(columna1));
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        String item = (String) listView.getItemAtPosition(position);

        // Show a toast if the user clicks on an item
        Toast.makeText(getActivity(), "Item Clicked: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(final View view) {
        // We need to post a Runnable to show the popup to make sure that the PopupMenu is
        // correctly positioned. The reason being that the view may change position before the
        // PopupMenu is shown.
        view.post(new Runnable() {
            @Override
            public void run() {
                showPopupMenu(view);
            }
        });
    }

    // BEGIN_INCLUDE(show_popup)
    private void showPopupMenu(View view) {
        final PopupAdapter adapter = (PopupAdapter) getListAdapter();

        // Retrieve the clicked item from view's tag
        final String item = (String) view.getTag();

        // Create a PopupMenu, giving it the clicked view for an anchor
        PopupMenu popup = new PopupMenu(getActivity(), view);

        // Inflate our menu resource into the PopupMenu's Menu
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        // Set a listener so we are notified if a menu item is clicked
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_remove:
                        // Remove the item from the adapter
                        adapter.remove(item);
                        return true;
                    case R.id.menu_agregar:
                        return true;
                }
                return false;
            }
        });

        // Finally show the PopupMenu
        popup.show();
    }
    // END_INCLUDE(show_popup)

    class PopupAdapter extends ArrayAdapter<String> {

        PopupAdapter(ArrayList<String> items) {
            super(getActivity(), R.layout.fragment_articulo, android.R.id.text2, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            // Let ArrayAdapter inflate the layout and set the text
            View view = super.getView(position, convertView, container);

            // BEGIN_INCLUDE(button_popup)
            // Retrieve the popup button from the inflated view
            View popupButton = view.findViewById(R.id.button_popup);

            // Set the item as the button's tag so it can be retrieved later
            popupButton.setTag(getItem(position));

            // Set the fragment instance as the OnClickListener
            popupButton.setOnClickListener(ArticuloFragment.this);
            // END_INCLUDE(button_popup)

            // Finally return the view to be displayed
            return view;
        }
    }
}
