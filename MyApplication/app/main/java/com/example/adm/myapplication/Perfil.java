package com.example.adm.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ADM on 20/10/2017.
 */

public class Perfil extends AppCompatActivity {

    CollapsingToolbarLayout barra_perfil;
    String name="Valentina";
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView imagen;
    TextView codigo;
    TextView carrera;
    TextView experencia;
    TextView biografia;
    String pasarcodigo;
    String pasarcarrera;
    String pasarcurso;
    Bitmap loadedImage;
    String imageHttpAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        barra_perfil = (CollapsingToolbarLayout) findViewById(R.id.collapser);

        FloatingActionButton photoButton = (FloatingActionButton) findViewById(R.id.fab);
        imagen = (ImageView) findViewById(R.id.image_paralax);
        codigo = (TextView) findViewById(R.id.codigo);
        carrera = (TextView) findViewById(R.id.carrera);
        experencia = (TextView) findViewById(R.id.exp_perfil);
        biografia = (TextView) findViewById(R.id.bio_perfil);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        pasarcodigo = getIntent().getExtras().getString("parametro");

        pasarcarrera = getIntent().getExtras().getString("parametro2");
        pasarcurso = getIntent().getExtras().getString("parametro3");
        barra_perfil.setTitle(name);
        codigo.setText(pasarcodigo);
        carrera.setText(pasarcarrera);
        //biografia.setText(pasarcurso);

        //imagen.setImageURI(foto);
        new ConsultarDatos().execute("http://10.226.132.18/api.cutclass.com/consultaperfil.php?codigo="+pasarcodigo);

        //"http://10.226.132.22/api.cutclass.com/images/foto01.jpg
        //E iniciar el asyntask
        int idfoto = imagen.getId();
        //new CargaImagenes().execute("http://10.226.132.18/api.cutclass.com/images/"+idfoto+".jpg");
        //pasarcurso = String.valueOf(idfoto);
        //biografia.setText(pasarcurso);
    }

    private class CargaImagenes extends AsyncTask<String, Void, Bitmap>{

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Perfil.this);
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0];
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            imagen.setImageBitmap(result);
            pDialog.dismiss();
        }

        private Bitmap descargarImagen (String imageHttpAddress){
            URL imageUrl = null;
            Bitmap imagen = null;
            try{
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagen = BitmapFactory.decodeStream(conn.getInputStream());
            }catch(IOException ex){
                ex.printStackTrace();
            }
            return imagen;
        }

    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();

            imagen.setImageURI(imageUri);
        }
    }



    private class ConsultarDatos extends AsyncTask<String, Void, String> {
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

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                Toast.makeText(getApplicationContext(), "se actualizaron los datos", Toast.LENGTH_LONG).show();
                codigo.setText(ja.getString(1));
                carrera.setText(ja.getString(2));
                experencia.setText(ja.getString(3));
                biografia.setText(ja.getString(4));
                barra_perfil.setTitle(ja.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error en cargar los datos", Toast.LENGTH_LONG).show();
            }
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

}
