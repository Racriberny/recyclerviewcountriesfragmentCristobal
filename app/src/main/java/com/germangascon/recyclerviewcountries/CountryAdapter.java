package com.germangascon.recyclerviewcountries;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * RecyclerViewCountries
 *
 * @author Germán Gascón
 * @version 0.4, 2022-10-06
 * @since 0.1
 **/

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final Country[] countries;
    private final Context context;
    private final IClickListener listener;

    public CountryAdapter(Context context, Country[] countries, IClickListener listener) {
        this.context = context;
        this.countries = countries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /* Inflamos el layout preparado para la visualización en formato lista */
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        /* Ampliación: Inflamos el layout preparado para la visualización en formato Grid */
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem_country, parent, false);

        /* Creamos el ViewHolder personalizado y lo devolvemos */
        return new CountryViewHolder(itemView,context, listener);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        /* Obtenemos el país de la posición solicitada */
        Country country = countries[position];
        /* Llamamos a nuestro método personalizado que asigna los valores a los componentes del layout */
        holder.bindCountry(country);
    }

    @Override
    public int getItemCount() {
        /* Devolvemos el número de elementos del array de países */
        return countries.length;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivFlag;
        private final TextView tvCountryName;
        private final TextView tvCountryCapital;
        private final TextView tvCountryPopulation;
        private final Context context;
        private final IClickListener listener;

        public CountryViewHolder(View itemView, Context context, IClickListener listener) {
            super(itemView);
            /* Guardamos el contexto para poder acceder a los recursos de la aplicación */
            this.context = context;
            /* Obtenemos la referencia a los componentes del layout */
            ivFlag = itemView.findViewById(R.id.ivFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCountryCapital = itemView.findViewById(R.id.tvCountryCapital);
            tvCountryPopulation = itemView.findViewById(R.id.tvCountryPopulation);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindCountry(Country country) {
            /*
             * Intentamos obtener el ID del drawable asociado a la imagen a partir del códgio ISO
             * del país de 2 caracteres. En caso de que no exista una imagen para la bandera de dicho
             * país, se dejará el valor por defecto que tiene en el Layout.
             */
            try {
                /*
                 * Obtenemos el código de 2 caracteres del país y nos aseguramos que esté en minúsculas
                 * ya que las imágenes situadas en /res/drawable están en minúsculas.
                 * Además le añadimos el caracter "_" delante para que coincida con el nombre de los Drawables.
                 */
                String flagName = "_"+country.getCode().toLowerCase();
                /* Obtenemos el ID del drawable (imagen de la bandera) a partir del flagName */
                int resID = context.getResources().getIdentifier(flagName, "drawable", context.getPackageName());
                /* Si hemos conseguido obtener el ID del drawable asociado, se lo asignamos al ImageView */
                if(resID != 0) {
                    ivFlag.setImageResource(resID);
                } else {
                    flagName = "_onu";
                    resID = context.getResources().getIdentifier(flagName, "drawable", context.getPackageName());
                    ivFlag.setImageResource(resID);
                }

            } catch (Exception e) {
                /*
                 * Si falla la obtención del ID del drawable no hacemos nada. Simplemente se quedará
                 * con la imagen de la bandera que tenga asignada por defecto.
                 */
            }

            /* Asignamos el nombre del país */
            tvCountryName.setText(country.getName());

            /* Asignamos el nombre de la capital */
            tvCountryCapital.setText(country.getCapital());

            /* Asignamos el número de habitantes */
            tvCountryPopulation.setText(String.valueOf(country.getPopulation()));
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                listener.onClick(getBindingAdapterPosition());
            }
        }
    }
}
