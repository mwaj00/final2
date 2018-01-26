/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import tridy.Uzivatel;
import tridy.Hodnoceni;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tridy.Akce;
import tridy.Kurz;
import tridy.Ucast;


public class Database 
{
private Connection con;
    private Statement st;
    private ResultSet rs;
   // konstruktor  třídy databáze, kterým se aplikace připojí k databázi
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }

     public ObservableList<Hodnoceni> listHodnoceni(int id)
     {
         try {
            String query = "SELECT * FROM `hodnoceni` WHERE kurz = \"" + id + "\"";
            rs = st.executeQuery(query);
            ObservableList<Hodnoceni>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Hodnoceni pom = new Hodnoceni();
                pom.setUzivatel(rs.getString(1));
                pom.setHvezdy(rs.getDouble(2));
                pom.setKurz(rs.getInt(3));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
     System.out.println(ex);
            }
         return null;
      }
     
     public ObservableList<Uzivatel> Uzivatele()
    {
        try {
            String query = "SELECT * FROM `uzivatel`";
            rs = st.executeQuery(query);
            ObservableList<Uzivatel>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Uzivatel pom = new Uzivatel();
                pom.setUjmeno(rs.getString(1));
                pom.setHeslo(rs.getString(2));
                pom.setJmeno(rs.getString(3));
                pom.setEmail(rs.getString(4));
                pom.setAdmin(rs.getInt(5));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     public ObservableList<Kurz> Kurzy()
    {
        try {
            String query = "SELECT * FROM `kurz`";
            rs = st.executeQuery(query);
            ObservableList<Kurz>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Kurz pom = new Kurz();
                pom.setId(rs.getInt(1));
                pom.setNazev(rs.getString(2));
                pom.setUniverzita(rs.getString(3));
                pom.setObor(rs.getString(4));
                pom.setPrinos(rs.getString(5));
                pom.setPopis(rs.getString(6));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
    public ObservableList<Akce> Akce()
    {
        try {
            String query = "SELECT * FROM `akce`";
            rs = st.executeQuery(query);
            ObservableList<Akce> list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Akce pom = new Akce();
                pom.setId(rs.getInt(1));
                pom.setKurz(rs.getInt(2));
                pom.setDen(rs.getString(3));
                pom.setCas(rs.getString(4));
                pom.setMistnost(rs.getString(5));
                pom.setKapacita(rs.getInt(6));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
    
    public ObservableList<Integer> idKurzu()
    {
        try {
            String query = "SELECT * FROM `kurz`";
            rs = st.executeQuery(query);
            ObservableList<Integer> list = FXCollections.observableArrayList();
            while(rs.next())
            {
                list.add(rs.getInt(1));
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
    
    
    public Akce getAkce(int id)
    {
        try {
            String query = "SELECT * FROM `akce` WHERE id = \"" + id + "\"";
            rs = st.executeQuery(query);
            Akce pom = new Akce();
            while(rs.next())
            {
                pom.setId(rs.getInt(1));
                pom.setKurz(rs.getInt(2));
                pom.setDen(rs.getString(3));
                pom.setCas(rs.getString(4));
                pom.setMistnost(rs.getString(5));
                pom.setKapacita(rs.getInt(6));
            }
            return pom;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
    
    public String getNazevKurzu(int id)
    {
        try {
            String query = "SELECT * FROM `kurz` WHERE id = \"" + id + "\"";
            rs = st.executeQuery(query);
            String pom = "";
            while(rs.next())
            {
                pom += rs.getString(2);
                
            }
            return pom;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
     public ObservableList<Akce> MojeAkce(String Ujmeno)
    {
        ObservableList<Ucast> listUcasti = FXCollections.observableArrayList();
        
        try {
                String query = "SELECT * FROM `ucastnaakci` WHERE uzivatel = \"" + Ujmeno + "\"";
                rs = st.executeQuery(query);
                while(rs.next())
                {
                    Ucast pom = new Ucast();
                    pom.setAkce(rs.getInt(1));
                    listUcasti.add(pom);
                }
            } 
        catch(Exception ex) 
            {
                System.out.println(ex);
            }
        
        ObservableList<Akce> list = FXCollections.observableArrayList();
        for(Ucast ucast : listUcasti)
        {
            Akce pomocna = new Akce();
            pomocna = getAkce(ucast.getAkce());
            list.add(pomocna);
        }
        
        return list;
    }
     
     public Hodnoceni getHodnoceni(String uzivatel,int kurz)
    {
        try {
            String query = "SELECT * FROM `hodnoceni` WHERE uzivatel = \"" + uzivatel + "\" AND kurz = \"" + kurz + "\"";
            rs = st.executeQuery(query);
            Hodnoceni pom = new Hodnoceni();
            while(rs.next())
            {
                pom.setUzivatel(rs.getString(1));
                pom.setHvezdy(rs.getDouble(2));
                pom.setKurz(rs.getInt(3));
            }
            
            if(pom.getHvezdy() == null)
            {
              pom.setHvezdy(0.0);
            }
            
            return pom;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
     public Uzivatel getUzivatele(String parametr)
    {
        try {
            String query = "SELECT * FROM `uzivatel` WHERE Ujmeno = \"" + parametr + "\"";
            rs = st.executeQuery(query);
            Uzivatel pom = new Uzivatel();
            while(rs.next())
            {
                pom.setUjmeno(rs.getString(1));
                pom.setHeslo(rs.getString(2));
                pom.setJmeno(rs.getString(3));
                pom.setEmail(rs.getString(4));
                pom.setAdmin(rs.getInt(5));
            }
            return pom;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
    public int getUcast(int id)
    {
        try {
            String query = "SELECT Count(*) FROM `ucastnaakci` WHERE akce = \"" + id + "\"";
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next())
            {
                i = rs.getInt(1);
            }
            return i;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
        return 99999; // chybne číslo
    }
     
     
     public ObservableList<Kurz> getWishlist(String uzivatel)
    {
        try {
            String query = "SELECT * FROM `wishlist` where uzivatel = \"" + uzivatel + "\"";
            rs = st.executeQuery(query);
            ObservableList<Kurz>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Kurz pom = new Kurz();
                pom.setId(rs.getInt(1));
                pom.setNazev(rs.getString(2));
                pom.setUniverzita(rs.getString(3));
                pom.setObor(rs.getString(4));
                pom.setPrinos(rs.getString(5));
                pom.setPopis(rs.getString(6));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
     
    public ObservableList<Kurz> najdiKurzy(String nazev)
    {
        String pomocny = "%";
        pomocny += nazev + "%";
        try {
            String query = "SELECT * FROM `kurz` where nazev like \"" + pomocny + "\"";
            rs = st.executeQuery(query);
            ObservableList<Kurz>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Kurz pom = new Kurz();
                pom.setId(rs.getInt(1));
                pom.setNazev(rs.getString(2));
                pom.setUniverzita(rs.getString(3));
                pom.setObor(rs.getString(4));
                pom.setPrinos(rs.getString(5));
                pom.setPopis(rs.getString(6));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    } 
     
     
  
    public void PridejUzivatele(String ujmeno,String heslo,String jmeno,String email)
    {
        try {
            String query = "Insert into `uzivatel` SET `Ujmeno`= \"" + ujmeno + "\",`heslo`= \"" + heslo + "\",`jmeno`= \"" + jmeno + "\",`email`= \"" + email + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    } 
    
    public void PridejKurz(String nazev,String univerzita,String obor,String prinos,String popis)
    {
        try {
            String query = "Insert into `kurz` SET `nazev`= \"" + nazev + "\",`univerzita`= \"" + univerzita + "\",`obor`= \"" + obor + "\",`prinos`= \"" + prinos + "\",`popis`= \"" + popis + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
     public void PridejKurzDoWishlistu(String nazev,String univerzita,String obor,String prinos,String popis,String uzivatel)
    {
        try {
            String query = "Insert into `wishlist` SET `nazev`= \"" + nazev + "\",`univerzita`= \"" + univerzita + "\",`obor`= \"" + obor + "\",`prinos`= \"" + prinos + "\",`popis`= \"" + popis + "\",`uzivatel`= \"" + uzivatel + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) 
        {
            Error("Tento kurz už jsi si do oblíbených dal.");
        }  
    }
    
    public void UpravKurz(String nazev,String univerzita,String obor,String prinos,String popis,int id)
    {
        try {
            String query = "UPDATE `kurz` SET `nazev`= \"" + nazev + "\",`univerzita`= \"" + univerzita + "\",`obor`= \"" + obor + "\",`prinos`= \"" + prinos + "\",`popis`= \"" + popis + "\" WHERE id = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void SmazKurz(int id)
    {
        try {
            String query = "DELETE from `kurz` WHERE id = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void SmazZWishlistu(int id)
    {
        try {
            String query = "DELETE from `wishlist` WHERE id = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void PridejAkci(int kurz,String den,String cas,String mistnost,int kapacita)
    {
        try {
            String query = "Insert into `akce` SET `kurz`= \"" + kurz + "\",`den`= \"" + den + "\",`cas`= \"" + cas + "\",`mistnost`= \"" + mistnost + "\",`kapacita`= \"" + kapacita + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void UpravAkci(int kurz,String den,String cas,String mistnost,int kapacita,int id)
    {
        try {
            String query = "UPDATE `akce` SET `kurz`= \"" + kurz + "\",`den`= \"" + den + "\",`cas`= \"" + cas + "\",`mistnost`= \"" + mistnost + "\",`kapacita`= \"" + kapacita + "\" WHERE id = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void SmazAkci(int id)
    {
        try {
            String query = "DELETE from `akce` WHERE id = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void PridejHodnoceni(String uzivatel,Double hvezdy,int kurz)
    {
        try {
            String query = "Insert into `hodnoceni` SET `uzivatel`= \"" + uzivatel + "\",`hvezdy`= \"" + hvezdy + "\",`kurz`= \"" + kurz + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void UpravHodnoceni(String uzivatel,Double hvezdy,int kurz)
    {
        try {
            String query = "UPDATE `hodnoceni` SET `hvezdy`= \"" + hvezdy + "\" WHERE uzivatel = \"" + uzivatel + "\" AND kurz = \"" + kurz + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    
    public void PrihlasNaAkci(String uzivatel,int akce)
    {
        try {
            String query = "Insert into `ucastnaakci` SET `akce`= \"" + akce + "\",`uzivatel`= \"" + uzivatel + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error("Na tuto akci už jste přihlášený!");
        }  
    }
    
    public void OdhlasZAkce(String uzivatel,int akce)
    {
        try {
            String query = "DELETE from `ucastnaakci` WHERE akce = \"" + akce + "\" AND uzivatel = \"" + uzivatel + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }

    
    
    
    public void Error(String error)
     {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba!");
        alert.setHeaderText("Jedno nebo více políček je špatně");
        alert.setContentText(error);
        alert.showAndWait();
     }
     
     public void Uspech()
     {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oznámení");
        alert.setHeaderText("Úspěch");
        alert.setContentText("Operace proběhla úspěšně");
        alert.showAndWait();
     }

    
}
