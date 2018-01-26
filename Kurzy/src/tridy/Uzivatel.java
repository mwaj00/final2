/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;


public class Uzivatel 
{
    private String Ujmeno;
    private String heslo;
    private String jmeno;
    private String email;
    private int admin;

    public Uzivatel() {
    }

    public Uzivatel(String Ujmeno, String heslo, String jmeno, String email, int admin) {
        this.Ujmeno = Ujmeno;
        this.heslo = heslo;
        this.jmeno = jmeno;
        this.email = email;
        this.admin = admin;
    }

    public String getUjmeno() {
        return Ujmeno;
    }

    public void setUjmeno(String Ujmeno) {
        this.Ujmeno = Ujmeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    
}
