/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;


public class Kurz 
{
   private int id;
   private String nazev;
   private String univerzita;
   private String obor;
   private String prinos;
   private Double hodnoceni;
   private String popis;

    public Kurz() {
    }

    public Kurz(int id, String nazev, String univerzita, String obor, String prinos, Double hodnoceni, String popis) {
        this.id = id;
        this.nazev = nazev;
        this.univerzita = univerzita;
        this.obor = obor;
        this.prinos = prinos;
        this.hodnoceni = hodnoceni;
        this.popis = popis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getUniverzita() {
        return univerzita;
    }

    public void setUniverzita(String univerzita) {
        this.univerzita = univerzita;
    }

    public String getObor() {
        return obor;
    }

    public void setObor(String obor) {
        this.obor = obor;
    }

    public String getPrinos() {
        return prinos;
    }

    public void setPrinos(String prinos) {
        this.prinos = prinos;
    }

    public Double getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(Double hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    

    
   
   
}
