/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;



public class Akce 
{
    private int id;
    private int kurz;
    private String den;
    private String cas;
    private String mistnost;
    private int kapacita;
    private int ucast;
    private String nazev;

    public Akce() {
    }

    public Akce(int id, int kurz, String den, String cas, String mistnost, int kapacita, int ucast) {
        this.id = id;
        this.kurz = kurz;
        this.den = den;
        this.cas = cas;
        this.mistnost = mistnost;
        this.kapacita = kapacita;
        this.ucast = ucast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKurz() {
        return kurz;
    }

    public void setKurz(int kurz) {
        this.kurz = kurz;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getMistnost() {
        return mistnost;
    }

    public void setMistnost(String mistnost) {
        this.mistnost = mistnost;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public int getUcast() {
        return ucast;
    }

    public void setUcast(int ucast) {
        this.ucast = ucast;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    
    
    
    
}
