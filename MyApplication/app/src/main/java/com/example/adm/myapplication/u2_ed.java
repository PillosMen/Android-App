package com.example.adm.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class u2_ed extends AppCompatActivity {

    private  RadioButton r1_a, r1_b, r1_c, r1_d, r2_a, r2_b, r2_c, r2_d,
            r3_a, r3_b, r3_c, r3_d, r4_a, r4_b, r4_c, r4_d,
            r5_a, r5_b, r5_c, r5_d;
    private RadioGroup grupo1, grupo2;
    private View verPregunta1, verPregunta2, verPregunta3;
    TextView cal;
    Button enviar;
    String hola;
    String resta, resta1, resta2, resta3, resta4, resta5;
    String suma, suma1, suma2, suma3, suma4, suma5;
    private int x=0,x1, x2, x3, x4, x5;
    private int y=0,y1, y2, y3, y4, y5;
    private int total;
    String pasarcodigo="123456789";
    String exp;
    int t=100;

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_u2_ed);

        grupo1 = (RadioGroup) findViewById(R.id.opcion_p1);
        grupo2 = (RadioGroup) findViewById(R.id.opcion_p2);
        enviar = (Button) findViewById(R.id.guardar);

        r1_a = (RadioButton) findViewById(R.id.p1_a);
        r1_b = (RadioButton) findViewById(R.id.p1_b);
        r1_c = (RadioButton) findViewById(R.id.p1_c);


        r2_a = (RadioButton) findViewById(R.id.p2_a);
        r2_b = (RadioButton) findViewById(R.id.p2_b);
        r2_c = (RadioButton) findViewById(R.id.p2_c);
        r2_d = (RadioButton) findViewById(R.id.p2_d);

        r3_a = (RadioButton) findViewById(R.id.p3_a);
        r3_b = (RadioButton) findViewById(R.id.p3_b);
        r3_c = (RadioButton) findViewById(R.id.p3_c);
        r3_d = (RadioButton) findViewById(R.id.p3_d);

        r4_a = (RadioButton) findViewById(R.id.p4_a);
        r4_b = (RadioButton) findViewById(R.id.p4_b);
        r4_c = (RadioButton) findViewById(R.id.p4_c);
        r4_d = (RadioButton) findViewById(R.id.p4_d);

        r5_a = (RadioButton) findViewById(R.id.p5_a);
        r5_b = (RadioButton) findViewById(R.id.p5_b);
        r5_c = (RadioButton) findViewById(R.id.p5_c);
        r5_d = (RadioButton) findViewById(R.id.p5_d);

        // boolean estado = r1_a.isChecked();
        //p2_opc = (RadioButton) findViewById(R.id.p2_b);

        verPregunta3 = findViewById(R.id.pregunta3);
        verPregunta2 = findViewById(R.id.pregunta2);
        verPregunta1 = findViewById(R.id.pregunta1);

        cal = (TextView) findViewById(R.id.calificacion);

    }

    public void guardarEncuesta(View v){

            if ( r1_c.isChecked() ){

                x=30;

                total = x+t;

                suma = String.valueOf(total);

                cal.setText(suma);
                mostrarPregunta2(true);
            }else if (r1_a.isChecked() || r1_b.isChecked()  ){

                y = 10;

                total = y+t;

                resta = String.valueOf(total);

                cal.setText(resta);
                mostrarPregunta2(true);
            }
            if (r2_b.isChecked() ){

            suma = (String) cal.getText();

            x1 = Integer.parseInt(suma);

            x2 = x1 + 30;

            suma1 = String.valueOf(x2);

            cal.setText(suma1);

            mostrarPregunta3(true);
                enviar.setText("Finalizar");
        }else if (r2_a.isChecked() || r2_c.isChecked() ){
                resta = (String) cal.getText();

                y1 = Integer.parseInt(resta);

                y2 = y1 + 10;

                resta1 = String.valueOf(y2);

                cal.setText(resta1);

                mostrarPregunta3(true);
                enviar.setText("Finalizar");
            }
        if (r3_d.isChecked()){
            //Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();
            suma1 = (String) cal.getText();
            x2 = Integer.parseInt(suma1);

            x3 = x2 +30;

            suma2 = String.valueOf(x3);

            cal.setText(suma2);
            Intent i = new Intent (this, EstructuraDatos.class);
            i.putExtra("puntos", cal.getText());
            new ActualizarExp().execute("http://pillos.scienceontheweb.net/updatexp.php?codigo="+pasarcodigo+"&experencia="+cal.getText().toString() );

            startActivity(i);


        }else if (r3_b.isChecked() || r3_c.isChecked() || r3_a.isChecked()){
            resta1 = (String) cal.getText();
            y2 = Integer.parseInt(resta1);
            y3 = y2 + 10;
            resta2 = String.valueOf(y3);
            cal.setText(resta2);
            Intent i = new Intent (this, EstructuraDatos.class);
            i.putExtra("puntos", cal.getText());
            new ActualizarExp().execute("http://pillos.scienceontheweb.net/updatexp.php?codigo="+pasarcodigo+"&experencia="+cal.getText().toString() );

            startActivity(i);
        }


    }

    private class ActualizarExp extends AsyncTask<String, Void, String> {
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


    private void mostrarPregunta2(boolean b) {
        verPregunta2.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta1.setVisibility(b ? View.GONE : View.VISIBLE);

    }
    private void mostrarPregunta3(boolean b) {
        verPregunta3.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta2.setVisibility(b ? View.GONE : View.VISIBLE);
    }

}
