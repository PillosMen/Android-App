package com.example.adm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Carreras extends AppCompatActivity {

    String carrera [] = new String[]{"Ciencias Computacionales", "Administracion Negocios", "NanoTecnologias", "Abogado", "Contaduria Publica", "Energia", "Salud Publica", "Medicina", "Gerontologia", "Nutricion", "Estudios Liberales", "Artesanias", "Historia Arte", "Administracion"};

    ListView lista;
    String pasarcodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_carreras);

        lista = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adaptador = (new ArrayAdapter<String>(this,R.layout.activity_carreras,R.id.nombre_fila_lista, carrera));

        lista.setAdapter(adaptador);

        pasarcodigo = getIntent().getExtras().getString("parametro");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String Slecteditem = carrera[+position];

                if(Slecteditem.equals("Ciencias Computacionales")){
                    Intent i = new Intent(Carreras.this, Cursos.class);
                    i.putExtra("parametro", pasarcodigo);
                    i.putExtra("parametro2", Slecteditem);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(), "Presionates boton abogado", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }

        });



    }



   /* lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String Slecteditem = canciones[+position];

            Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

        }

    });
    */
}
