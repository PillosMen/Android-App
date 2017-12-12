package com.example.adm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Cursos extends AppCompatActivity {


    String cursos [] = new String[]{"Estructura de Datos", "Sistemas Inteligentes", "Programacion", "Videojuegos"};

    ListView lista;
    String pasarcodigo;
    String pasarcarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cursos);

        lista = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adaptador = (new ArrayAdapter<String>(this,R.layout.activity_carreras,R.id.nombre_fila_lista, cursos));

        lista.setAdapter(adaptador);
        pasarcodigo = getIntent().getExtras().getString("parametro");
        pasarcarrera = getIntent().getExtras().getString("parametro2");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String Slecteditem = cursos[+position];

                if(Slecteditem.equals("Estructura de Datos")){

                    Intent i = new Intent(Cursos.this, EstructuraDatos.class);
                    i.putExtra("parametro", pasarcodigo);
                    i.putExtra("parametro2", pasarcarrera);
                    i.putExtra("parametro3", Slecteditem);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(), "Presionates boton abogado", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }

        });

    }
}
