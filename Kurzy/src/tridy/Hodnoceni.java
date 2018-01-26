/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;


public class Hodnoceni 
{
    private String uzivatel;
    private Double hvezdy;
    private int kurz;

    public Hodnoceni() {
    }

    public Hodnoceni(String uzivatel, Double hvezdy, int kurz) {
        this.uzivatel = uzivatel;
        this.hvezdy = hvezdy;
        this.kurz = kurz;
    }

    public String getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Double getHvezdy() {
        return hvezdy;
    }

    public void setHvezdy(Double hvezdy) {
        this.hvezdy = hvezdy;
    }

    public int getKurz() {
        return kurz;
    }

    public void setKurz(int kurz) {
        this.kurz = kurz;
    }

   

    
    
    
}
