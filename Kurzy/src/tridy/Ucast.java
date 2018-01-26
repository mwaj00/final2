/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;


public class Ucast 
{
    private int akce;
    private String uzivatel;

    public Ucast() {
    }

    public Ucast(int akce, String uzivatel) {
        this.akce = akce;
        this.uzivatel = uzivatel;
    }

    public int getAkce() {
        return akce;
    }

    public void setAkce(int akce) {
        this.akce = akce;
    }

    public String getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel = uzivatel;
    }
    
    
}
