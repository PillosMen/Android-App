package com.example.adm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class u1_ed extends AppCompatActivity {

    private RadioButton r1_a, r1_b, r1_c, r1_d, r2_a, r2_b, r2_c, r2_d,
            r3_a, r3_b, r3_c, r3_d, r4_a, r4_b, r4_c, r4_d,
            r5_a, r5_b, r5_c, r5_d;
    private RadioGroup grupo1, grupo2;
    private View verPregunta1, verPregunta2,verPregunta3, verPregunta4, verPregunta5;
    TextView cal;
    Button enviar;
    String hola;
    String resta, resta1, resta2, resta3, resta4, resta5;
    String suma, suma1, suma2, suma3, suma4, suma5;
    private int x=0,x1, x2, x3, x4, x5;
    private int y=0,y1, y2, y3, y4, y5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1_ed);
        grupo1 = (RadioGroup) findViewById(R.id.opcion_p1);
        grupo2 = (RadioGroup) findViewById(R.id.opcion_p2);
        enviar = (Button) findViewById(R.id.guardar);

        r1_a = (RadioButton) findViewById(R.id.p1_a);
        r1_b = (RadioButton) findViewById(R.id.p1_b);
        r1_c = (RadioButton) findViewById(R.id.p1_c);
        r1_d = (RadioButton) findViewById(R.id.p1_d);

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
        verPregunta5 = findViewById(R.id.pregunta5);
        verPregunta4 = findViewById(R.id.pregunta4);
        verPregunta3 = findViewById(R.id.pregunta3);
        verPregunta2 = findViewById(R.id.pregunta2);
        verPregunta1 = findViewById(R.id.pregunta1);

        cal = (TextView) findViewById(R.id.calificacion);


       // boolean estado = radioButton.isCheked();
    }



    public void guardarEncuesta(View v){

        if (r1_d.isChecked()){

            x = 20;

            suma = String.valueOf(x);

            cal.setText(suma);
            //Toast.makeText(this, "Respuesta Correcta", Toast.LENGTH_LONG).show();
            mostrarPregunta2(true);


        }else if (r1_a.isChecked() || r1_b.isChecked() || r1_c.isChecked()){

            y = 10;

            resta = String.valueOf(y);

            cal.setText(resta);

            mostrarPregunta2(true);

        }

        if (r2_b.isChecked() ){

            suma = (String) cal.getText();

            x1 = Integer.parseInt(suma);

            x2 = x1 + 20;

            suma1 = String.valueOf(x2);

            cal.setText(suma1);

            mostrarPregunta3(true);

        }else if (r2_a.isChecked() || r2_c.isChecked() || r2_d.isChecked()){
            resta = (String) cal.getText();

            y1 = Integer.parseInt(resta);

            y2 = y1 + 10;

            resta1 = String.valueOf(y2);

            cal.setText(resta1);

            mostrarPregunta3(true);
        }

        if (r3_a.isChecked()){
            //Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();
            suma1 = (String) cal.getText();
            x2 = Integer.parseInt(suma1);

            x3 = x2 +20;

            suma2 = String.valueOf(x3);

            cal.setText(suma2);
            mostrarPregunta4(true);

        }else if (r3_b.isChecked() || r3_c.isChecked() || r3_d.isChecked()){
            resta1 = (String) cal.getText();
            y2 = Integer.parseInt(resta1);
            y3 = y2 + 10;
            resta2 = String.valueOf(y3);
            cal.setText(resta2);
            mostrarPregunta4(true);
        }
        if (r4_c.isChecked() ){

            suma2 = (String) cal.getText();
            x3 = Integer.parseInt(suma2);

            x4 = x3 + 20;

            suma3 = String.valueOf(x4);

            cal.setText(suma3);
            mostrarPregunta5(true);
            enviar.setText("Finalizar");
        }else if (r4_a.isChecked() || r4_b.isChecked() || r4_d.isChecked()){
            resta2 = (String) cal.getText();
            y3 = Integer.parseInt(resta2);
            y4 = y3 + 10;
            resta3 = String.valueOf(y4);
            cal.setText(resta3);
            mostrarPregunta5(true);
            enviar.setText("Finalizar");
        }
        if (r5_b.isChecked()){

            suma3 = (String) cal.getText();
            x4 = Integer.parseInt(suma3);

            x5 = x4 + 20;

            suma4 = String.valueOf(x5);

            cal.setText(suma4);

            Intent i = new Intent (u1_ed.this, EstructuraDatos.class);
            i.putExtra("puntos", cal.getText());
            startActivity(i);

        }else if (r5_a.isChecked() || r5_c.isChecked() || r5_d.isChecked()){
            resta3 = (String) cal.getText();
            y4 = Integer.parseInt(resta3);
            y5 = y4 + 10;
            resta4 = String.valueOf(y5);
            cal.setText(resta4);

            Toast.makeText(this, "mal", Toast.LENGTH_LONG).show();
            Intent i = new Intent (u1_ed.this, EstructuraDatos.class);
            i.putExtra("puntos", cal.getText());
            startActivity(i);
        }



    }

    public void onRadioButtonClicked(View view) {


    }

    private void mostrarPregunta2(boolean b) {
        verPregunta2.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta1.setVisibility(b ? View.GONE : View.VISIBLE);

    }
    private void mostrarPregunta3(boolean b) {
        verPregunta3.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta2.setVisibility(b ? View.GONE : View.VISIBLE);

    }
    private void mostrarPregunta4(boolean b) {
        verPregunta4.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta3.setVisibility(b ? View.GONE : View.VISIBLE);
    }
    private void mostrarPregunta5(boolean b) {
        verPregunta5.setVisibility(b ? View.VISIBLE : View.GONE);
        verPregunta4.setVisibility(b ? View.GONE : View.VISIBLE);
    }
}
