package com.distribuidora.appandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

import com.distribuidora.appandroid.Controller.ArticuloFragment;
import com.distribuidora.appandroid.Controller.ClienteActivity;
import com.distribuidora.appandroid.Controller.ClienteFragment;
import com.distribuidora.appandroid.Model.SpinnerNavItem;
import com.distribuidora.appandroid.Controller.TitleNavigationAdapter;

import java.util.ArrayList;


public class MainActivity extends Activity implements
        ActionBar.OnNavigationListener{

    private ShareActionProvider mShareActionProvider;
    private ProgressDialog pDialog;
    // action bar
    private android.app.ActionBar actionBar;
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;
    // Navigation adapter
    private TitleNavigationAdapter adapter;
    // Refresh menu item
    private MenuItem refreshMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getActionBar();

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);

        // Enabling Spinner dropdown navigation
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);

        // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Home", R.drawable.ic_distribuidora_home));
        navSpinner.add(new SpinnerNavItem("Clientes", R.drawable.ic_distribuidora_clientes));
        navSpinner.add(new SpinnerNavItem("Articulos", R.drawable.ic_distribuidora_productos));
        navSpinner.add(new SpinnerNavItem("Cuentas por Cobrar", R.drawable.ic_distribuidora_cuentas));
        navSpinner.add(new SpinnerNavItem("Lista de Precios", R.drawable.ic_distribuidora_lista));
        navSpinner.add(new SpinnerNavItem("Ruta de Visitas", R.drawable.ic_distribuidora_ruta));
        navSpinner.add(new SpinnerNavItem("Orden de Pedido", R.drawable.ic_distribuidora_orden));

        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(),
                navSpinner);

        // assigning the spinner navigation
        actionBar.setListNavigationCallbacks(adapter, this);

        // Changing the action bar icon
        // actionBar.setIcon(R.drawable.ico_actionbar);


       /* ImageView photoView = (ImageView) findViewById(R.id.imgIcon);
        photoView.setImageURI(uri);*/
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if ( getActionBar().getTitle().toString().equals("Clientes") ){
            ClienteFragment newFragment = new ClienteFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if ( getActionBar().getTitle().toString().equals("Articulos") ){
            ArticuloFragment newFragment = new ArticuloFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
            case R.id.action_location_found:
                // location found
                LocationFound();
                return true;
            case R.id.action_refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();
                return true;
            case R.id.action_help:
                // help action
                return true;
            case R.id.action_check_updates:
                // check for updates action
                return true;
            case R.id.menu_item_share:
                Log.d("LOGTAG", "intent share selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Launching new activity
     * */
    private void LocationFound() {
        //Intent i = new Intent(MainActivity.this, LocationFoundActivity.class);
        Intent i = new Intent(MainActivity.this, ClienteActivity.class);
        startActivity(i);
    }

    /*
     * Actionbar navigation item select listener
     */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item

        if(itemPosition==0)
        {
            // Create fragment and give it an argument specifying the article it should show
            HomeFragment newFragment = new HomeFragment();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            //Toast.makeText(getApplication(),"Triggered 0",Toast.LENGTH_LONG).show();
            return true;
        }
        if(itemPosition==1)
        {
            getActionBar().setTitle("Clientes");
            // Create fragment and give it an argument specifying the article it should show
            ClienteFragment newFragment = new ClienteFragment();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            //Toast.makeText(getApplication(),"Triggered 1",Toast.LENGTH_LONG).show();
            return true;
        }
        if(itemPosition==2)
        {
            getActionBar().setTitle("Articulos");
            // Create fragment and give it an argument specifying the article it should show
            ArticuloFragment newFragment = new ArticuloFragment();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            //Toast.makeText(getApplication(),"Triggered 2",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public void RunDialog(View view) {
        new GetData().execute();
    }

    /**
     * Async task to load the data from server
     * **/
    private class SyncData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // set the progress bar view
            refreshMenuItem.setActionView(R.layout.action_progressbar);

            refreshMenuItem.expandActionView();
        }

        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            refreshMenuItem.collapseActionView();
            // remove the progress bar view
            refreshMenuItem.setActionView(null);
        }
    };

    private class GetData extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(Void... arg0) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

        }

    }

    private void initializeShareAction(MenuItem shareItem) {
        //    uri = getIntent().getData();

        ShareActionProvider shareProvider = (ShareActionProvider) shareItem.getActionProvider();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        String mySharedString =
                "hellow world" ;
        shareIntent.putExtra(Intent.EXTRA_TEXT,mySharedString);

       /* shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType(MIME_TYPE);
        */
        shareProvider.setShareIntent(shareIntent);
    }

}
