package com.example.adm.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EstructuraDatos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView cali;
    int cambio;
    String puntos, salto;
    String pasarcodigo;
    String pasarcarrera;
    String pasarcurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estructura_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //barra inferior
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        cali = (TextView) findViewById(R.id.exp);

        puntos = getIntent().getStringExtra("puntos");

        cali.setText(puntos);

        pasarcodigo = getIntent().getExtras().getString("parametro");
        pasarcarrera = getIntent().getExtras().getString("parametro2");
        pasarcurso = getIntent().getExtras().getString("parametro3");

        new registroPerfil().execute("http://10.226.132.18/api.cutclass.com/registroperfil.php?codigo="+pasarcodigo+"&nombre="+""+"&carrera="+pasarcarrera+"&experencia="+cali.getText().toString()+"&cursos="+pasarcurso);
        /*Fab de ejemplo
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */
        //Fab unidad 1
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        int col_oro = getResources().getColor(R.color.color_oro);
        ColorStateList csl_oro = new ColorStateList(new int[][]{new int[0]}, new int[]{col_oro});
        int col_plata = getResources().getColor(R.color.color_plata);
        ColorStateList csl_plata = new ColorStateList(new int[][]{new int[0]}, new int[]{col_plata});
        int col_bronce = getResources().getColor(R.color.color_bronce);
        ColorStateList csl_bronce = new ColorStateList(new int[][]{new int[0]}, new int[]{col_bronce});

        if (cali.getText().equals("100")){

            fab1.setBackgroundTintList(csl_oro);
        }

        if (cali.getText().equals("90")){

            fab1.setBackgroundTintList(csl_oro);
        }
        if (cali.getText().equals("80")){

            fab1.setBackgroundTintList(csl_plata);
        }
        if (cali.getText().equals("70")){

            fab1.setBackgroundTintList(csl_plata);
        }
        if (cali.getText().equals("60")){

            fab1.setBackgroundTintList(csl_bronce);
        }
        if (cali.getText().equals("50")){

            fab1.setBackgroundTintList(csl_bronce);
        }

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Toast toast1 = Toast.makeText(getApplicationContext(),
                        //"Presionaste la unidad 1", Toast.LENGTH_SHORT);
                //toast1.show();
                lanzarUnidad1(null);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Toast toast1 = Toast.makeText(getApplicationContext(),
                //"Presionaste la unidad 1", Toast.LENGTH_SHORT);
                //toast1.show();
                lanzarUnidad2(null);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Registro de perfil
    private class registroPerfil extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Se Registro con exito!!!", Toast.LENGTH_LONG).show();
        }
    }
    //metodo conectar mysql
    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.estructura_datos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */
        if (id == R.id.acerca){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.acercade);
            dialog.setTitle("Acerca de:");
            TextView text = (TextView) dialog.findViewById(R.id.TextView01);
            text.setText("mensaje");
            dialog.show();
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            Intent in = new Intent(this, Perfil.class);
            in.putExtra("parametro", pasarcodigo);
            in.putExtra("parametro2", pasarcarrera);
            in.putExtra("parametro3", pasarcurso);
            startActivity(in);
        } else if (id == R.id.mis_cursos) {

        } else if (id == R.id.logros) {

        } else if (id == R.id.regresar) {

        } else if (id == R.id.compartir) {

        } else if (id == R.id.enviar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lanzarUnidad1(View view){
        Intent i = new Intent(this, u1_ed.class);
        startActivity(i);
    }

    public void lanzarUnidad2(View view){
        Intent i = new Intent(this, u2_ed.class);
        startActivity(i);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    cali.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    cali.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    cali.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
}
