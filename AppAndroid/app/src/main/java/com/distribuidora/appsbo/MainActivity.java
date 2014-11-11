package com.distribuidora.appsbo;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.distribuidora.appsbo.adapter.TitleNavigationAdapter;
import com.distribuidora.appsbo.distribuidora.R;
import com.distribuidora.appsbo.model.SpinnerNavItem;

import java.util.ArrayList;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.app.Fragment;

//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements
        //HeadlinesFragment.OnHeadlineSelectedListener,
        ActionBar.OnNavigationListener {

    private ShareActionProvider mShareActionProvider;

    private ProgressDialog pDialog;

    // action bar
    private ActionBar actionBar;

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
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Home", R.drawable.ic_distribuidora_home));
        navSpinner.add(new SpinnerNavItem("Clientes", R.drawable.ic_distribuidora_clientes));
        navSpinner.add(new SpinnerNavItem("Productos", R.drawable.ic_distribuidora_productos));
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

        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MainSample fragment = new MainSample();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
        */
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout

        /*
        if (findViewById(R.id.contenedor) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            HeadlinesFragment firstFragment = new HeadlinesFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor, firstFragment).commit();
        }
        */

        /*
        // Create fragment and give it an argument specifying the article it should show
        ArticleFragment newFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ArticleFragment.ARG_POSITION, 100);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.contenedor, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        */
        /*
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        Fragment fragment = new MainSample();
        //fragment.setArguments(getIntent().getExtras());
        FT.add(R.id.contenedor,fragment);
        //FT.add(R.id.contenedor, fragment);
        FT.commit();
        */
        Fragment firstFragment = new FragmentHome();
        firstFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedor, firstFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));


        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        //  mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        initializeShareAction(item);

// Return true to display menu
        return true;
        //	return super.onCreateOptionsMenu(menu);
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
        Intent i = new Intent(MainActivity.this, LocationFoundActivity.class);
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
            FragmentHome newFragment = new FragmentHome();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

            Toast.makeText(getApplication(),"Triggered 0",Toast.LENGTH_LONG).show();
            return true;
        }
        if(itemPosition==1)
        {
            // Create fragment and give it an argument specifying the article it should show
            FragmentCliente newFragment = new FragmentCliente();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

            Toast.makeText(getApplication(),"Triggered 1",Toast.LENGTH_LONG).show();
            return true;
        }
        if(itemPosition==2)
        {
            // Create fragment and give it an argument specifying the article it should show
            FragmentProducto newFragment = new FragmentProducto();
            Bundle args = new Bundle();
            //args.putInt(FragmentCliente.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.contenedor, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

            Toast.makeText(getApplication(),"Triggered 2",Toast.LENGTH_LONG).show();
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
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
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

        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
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




    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
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
