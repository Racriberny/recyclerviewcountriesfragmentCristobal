package com.germangascon.recyclerviewcountries;

/**
 * Created by ggascon on 22/10/15.
 * Nuestro modelo para albergar los datos de cada país
 */
public class Country {
    /** Código ISO (de 2 caracteres) del país */
    private String code;
    /** Nombre del país */
    private String name;
    /** Número total de habitantes del país */
    private long population;
    /** Nombre de la capital del país */
    private String capital;
    /** Código ISO (de 3 caracteres) del país. Aunque no hacemos uso de él */
    private String iso3;

    /**
     * Construye un objeto de tipo Country
     * @param code Código ISO (de 2 caracteres)
     * @param name Nombre del país
     * @param population Número total de habitantes
     * @param capital Nombre de la capital del país
     * @param iso3 Código ISO (de 3 caracteres)
     */
    public Country(String code, String name, long population, String capital, String iso3) {
        this.code = code;
        this.name = name;
        this.population = population;
        this.capital = capital;
        this.iso3 = iso3;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public String getIso3() {
        return iso3;
    }
}
