package com.germangascon.recyclerviewcountries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Establecemos como layout de nuestra Activity el archivo activity_main.xml */
        setContentView(R.layout.activity_main);

        /** Obtenemos la referencia al RecyclerView */
        recView = (RecyclerView)findViewById(R.id.recView);
        recView.setHasFixedSize(true);

        /** Creamos una instancia de nuestro parser personalizado */
        CountryParser parser = new CountryParser(this);
        /** Parseamos el archivo */
        if(parser.parse()) {
            /** Si ha ido bien, obtenemos el array de objetos Country */
            final Country[] countries = parser.getCountries();
            /** Creamos una instancia de nuestro adaptador personalizado */
            CountryAdapter adapter = new CountryAdapter(this, countries);

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "País: " + countries[recView.getChildAdapterPosition(view)].getName(), Toast.LENGTH_SHORT).show();
                }
            });
            /** Asignamos el adaptador a nuestro RecyclerView */
            recView.setAdapter(adapter);
            /** Creamos y asignamos el modo de visualización, en este caso una Lista vertical */
            recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

            /** Ampliación: Creamos y mostramos en formato Grid con 3 columnas */
            //recView.setLayoutManager(new GridLayoutManager(this,3));
        } else {
            /** Si no se han podido parsear los datos de los países, mostramos un mensaje de error */
            Toast.makeText(this, "No se pudieron obtener los datos de los países", Toast.LENGTH_SHORT).show();
        }
    }
}
