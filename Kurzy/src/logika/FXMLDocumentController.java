/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import logika.Database;
import tridy.Uzivatel;
import tridy.Kurz;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import static jdk.nashorn.internal.objects.NativeMath.round;
import tridy.Akce;
import tridy.Hodnoceni;

/**
 *
 * @author Hed94
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane pMenu,pRegistrace,pPrihlaseni,pKurzy,pPrihlaseniKurzy,pHodnoceni,pWishlist;
    @FXML
    private Button bPrihlaseni,bRegistrace,bRegistrovat,bZpetRegistrace,bKurzy,bPrihlaseniKurzy,bOdhlasit,bNovyk,bSmazk,bUpravk,bHodnotk,bUlozh,bZpeth,bZpetPrihlaseniKurzy,bPridejAkci,bUpravAkci,bSmazAkci;
    @FXML
    private TextField Ujmeno,rUjmeno,rJmeno,rEmail,textHledej;
    @FXML
    private PasswordField rHeslo,rHeslo2,Uheslo;
    @FXML
    private TableView<Kurz> tKurzyk;
    @FXML
    private TableColumn<Kurz,String> nazevk,univerzitak,obork,prinosk,popisk;
    @FXML
    private TableColumn<Kurz,Integer> idk;
    @FXML
    private TableColumn<Kurz,Double> hodnocenik;
    @FXML
    private TableView<Akce> tAkce,tAkceU;
    @FXML
    private TableColumn<Akce,String> dena,casa,mistnosta,nazevAu,denAu,casAu,mistnostAu;
    @FXML
    private TableColumn<Akce,Integer> ida,idka,ucasta,kapacitaa,idAu;
    @FXML
    private Label hNazev;
    @FXML
    private ComboBox comboH,comboK;
    
    
    @FXML
    private TableView<Kurz> tWishlist;
    @FXML
    private TableColumn<Kurz,String> nazevW,univerzitaW,oborW,prinosW,popisW;
    @FXML
    private TableColumn<Kurz,Integer> idW;
    
    
    private ObservableList<Uzivatel> uzivatele = FXCollections.observableArrayList();
    private ObservableList<Kurz> kurzy = FXCollections.observableArrayList();
    private ObservableList<Kurz> oblibeneKurzy = FXCollections.observableArrayList();
    private ObservableList<Hodnoceni> hodnoceni = FXCollections.observableArrayList();
    private ObservableList<Double> hvezdy = FXCollections.observableArrayList();
    private ObservableList<Akce> listAkci = FXCollections.observableArrayList();
    private ObservableList<Akce> mojeAkce = FXCollections.observableArrayList();
    private ObservableList<Integer> idKurzu = FXCollections.observableArrayList();
    
    
    private Database databaze = new Database();
    private Uzivatel prihlasenyU = new Uzivatel();
    private Kurz hodnocenyK = null;
    private boolean existujeH = false;
    
    @FXML
    public void Prihlasit()
    {
        Uzivatel pomocny = new Uzivatel();
        pomocny = databaze.getUzivatele(Ujmeno.getText());
        
        if(Uheslo.getText().equals(pomocny.getHeslo()))
        {
            pPrihlaseni.setVisible(false);
            pMenu.setVisible(true);
            prihlasenyU = pomocny;
        }
        else
        {
            databaze.Error("Špatné heslo.");
        }
    }
    
    @FXML
    public void Odhlasit()
    {
        pMenu.setVisible(false);
        pPrihlaseni.setVisible(true);
        prihlasenyU = null;
        Clean();
    }
    
    @FXML
    public void DoRegistrace()
    {
        pPrihlaseni.setVisible(false);
        pRegistrace.setVisible(true);
        Clean();
    }
    
    
    @FXML
    public void Registruj()
    {
        
        
        if(rHeslo.getText().equals(rHeslo2.getText()))
        {
            if(stejniUzivatele(rUjmeno.getText(),rEmail.getText()))
            {
                databaze.PridejUzivatele(rUjmeno.getText(), rHeslo.getText() , rJmeno.getText(), rEmail.getText());
                ZpetRegistrace();
            }
            else
            {
                databaze.Error("Uživatel s tímto jménem nebo emailem už existuje");
            }
        }
        else
        {
            databaze.Error("Hesla si musí být rovna");
        }
    }
    
    @FXML
    public void ZpetRegistrace()
    {
        pPrihlaseni.setVisible(true);
        pRegistrace.setVisible(false);
        Clean();
    }
    
    @FXML
    public void Menu()
    {
        pMenu.setVisible(true);
        pKurzy.setVisible(false);
    }
    
    @FXML
    public void Kurzy()
    {
        pMenu.setVisible(false);
        pKurzy.setVisible(true);
        kurzy = databaze.Kurzy();
        tKurzyk.setItems(kurzy);
        
        if(prihlasenyU.getAdmin() == 1)
        {
            bNovyk.setVisible(true);
            bSmazk.setVisible(true);
            bUpravk.setVisible(true);
            tKurzyk.setEditable(true);
        }
        else
        {
            bNovyk.setVisible(false);
            bSmazk.setVisible(false);
            bUpravk.setVisible(false);
            tKurzyk.setEditable(false);
        }
        
        for(Kurz k : kurzy)
        {
            if(k.getHodnoceni() == null)
            {
                k.setHodnoceni(0.0);
            }
            hodnoceni = databaze.listHodnoceni(k.getId());
            for(Hodnoceni h : hodnoceni)
            {
                k.setHodnoceni(k.getHodnoceni() + h.getHvezdy());
            }
            if(hodnoceni.size()>0)
            {
                k.setHodnoceni(k.getHodnoceni() / hodnoceni.size());
            }
        }
        
        Clean();
        
    }
    @FXML
    public void NovyKurz()
    {
        databaze.PridejKurz("----", "----", "----", "----", "----");
        Kurzy();
    }
    @FXML
    public void UpravKurz()
    {
        Kurz pomocny = tKurzyk.getSelectionModel().getSelectedItem();
        if(pomocny != null)
        {
            databaze.UpravKurz(pomocny.getNazev(), pomocny.getUniverzita(), pomocny.getObor(), pomocny.getPrinos(), pomocny.getPopis(), pomocny.getId());
            Kurzy();
        }
        else
        {
            databaze.Error("Nevybral jsi žádný záznam z tabulky.");
        }
    }
    @FXML
    public void SmazKurz()
    {
        Kurz pomocny = tKurzyk.getSelectionModel().getSelectedItem();
        
        if(pomocny != null)
        {
            databaze.SmazKurz(pomocny.getId());
            Kurzy();
        }
        else
        {
            databaze.Error("Nevybral jsi žádný záznam z tabulky.");
        }
    }
    
    @FXML
    public void Hodnoceni()
    {
        hodnocenyK = tKurzyk.getSelectionModel().getSelectedItem();
        if(hodnocenyK != null)
        {
            pKurzy.setVisible(false);
            pHodnoceni.setVisible(true);
            comboH.setItems(hvezdy);

            Hodnoceni hod = null;
            hod = databaze.getHodnoceni(prihlasenyU.getUjmeno(), hodnocenyK.getId());
            existujeH = false;

            if(hod.getHvezdy() > 0.0)
            {
                comboH.setValue(hod.getHvezdy());
                existujeH = true;
            }
            hNazev.setText(hodnocenyK.getNazev());
        }
        else
        {
            databaze.Error("Není vybrán žádný kurz!");
        }
    }
    
    @FXML
    public void UlozHodnoceni()
    {
        String pomocny = comboH.getSelectionModel().getSelectedItem().toString();
        if(existujeH)
        {
            databaze.UpravHodnoceni(prihlasenyU.getUjmeno(), Double.parseDouble(pomocny), hodnocenyK.getId());
        }
        else
        {
            databaze.PridejHodnoceni(prihlasenyU.getUjmeno(), Double.parseDouble(pomocny), hodnocenyK.getId());
        }
        ZpetHodnoceni();
    }
    
    @FXML
    public void ZpetHodnoceni()
    {
        pKurzy.setVisible(true);
        pHodnoceni.setVisible(false);
        comboH.setValue(null);
        hodnocenyK = null;
        Kurzy();
    }
    
    @FXML
    public void ZpetPrihlaseniKurzy()
    {
        pPrihlaseniKurzy.setVisible(false);
        pMenu.setVisible(true);
    }
    
    
    @FXML
    public void PrihlaseniNaKurzy()
    {
        pMenu.setVisible(false);
        pPrihlaseniKurzy.setVisible(true);
        
        listAkci = databaze.Akce();
        
        for(Akce akce : listAkci)
        {
            akce.setUcast(databaze.getUcast(akce.getId()));
        }
        tAkce.setItems(listAkci);
        
        mojeAkce = databaze.MojeAkce(prihlasenyU.getUjmeno());
        
        for(Akce a : mojeAkce)
        {
            a.setNazev(databaze.getNazevKurzu(a.getKurz()));
        }
        
        tAkceU.setItems(mojeAkce);
        
        
        idKurzu = databaze.idKurzu();
        comboK.setItems(idKurzu);
        comboK.setValue("Vyberte ID Akce");
        
        if(prihlasenyU.getAdmin() == 1)
        {
            comboK.setVisible(true);
            bPridejAkci.setVisible(true);
            bUpravAkci.setVisible(true);
            bSmazAkci.setVisible(true);
            tAkce.setEditable(true);
        }
        else
        {
            comboK.setVisible(false);
            bPridejAkci.setVisible(false);
            bUpravAkci.setVisible(false);
            bSmazAkci.setVisible(false);
            tAkce.setEditable(false);
        }
        
    }
    
    @FXML
    public void NovaAkce()
    {   
        String pom = comboK.getSelectionModel().getSelectedItem().toString();
        if(! pom.equals("Vyberte ID Akce"))
        {
            int id = Integer.parseInt(pom);
            databaze.PridejAkci(id, "--", "----", "----", 0);
            PrihlaseniNaKurzy();
        }
        else
        {
            databaze.Error("Nebyl vybrán žádný kurz z combo boxu!");
        }
    }
    @FXML
    public void UpravAkci()
    {
        Akce pomocna = tAkce.getSelectionModel().getSelectedItem();
        
        if(pomocna != null)
        {
            databaze.UpravAkci(pomocna.getKurz(), pomocna.getDen(), pomocna.getCas(), pomocna.getMistnost(), pomocna.getKapacita(), pomocna.getId());
            PrihlaseniNaKurzy();
        }
        else
        {
            databaze.Error("Nebyla vybrána žádná akce v tabulce!");
        }
        
    }
    @FXML
    public void SmazAkci()
    {
        Akce pomocna = tAkce.getSelectionModel().getSelectedItem();
        if(pomocna != null)
        {
            databaze.SmazAkci(pomocna.getId());
            PrihlaseniNaKurzy();
        }
        else
        {
            databaze.Error("Nebyla vybrána žádná akce v tabulce!");
        }
    }
    
    @FXML
    public void PrihlasMe()
    {
        Akce pomocna = tAkce.getSelectionModel().getSelectedItem();
        if(pomocna != null)
        {
            Akce pom = databaze.getAkce(pomocna.getId()); // Kontrola aby kapacita akce nebyla překročena.
            pom.setUcast(databaze.getUcast(pom.getId()));
            if(pom.getUcast()+1 > pom.getKapacita())
            {
                databaze.Error("Tento Kurz je už plný");
            }
            else
            {
                databaze.PrihlasNaAkci(prihlasenyU.getUjmeno(), pomocna.getId());
                PrihlaseniNaKurzy();
            }
        }
        else
        {
            databaze.Error("Nebyla vybrána žádná akce v tabulce!");
        }
    }
    
    @FXML
    public void OdhlasMe()
    {
        Akce pomocna = tAkceU.getSelectionModel().getSelectedItem();
        if(pomocna != null)
        {
            databaze.OdhlasZAkce(prihlasenyU.getUjmeno(), pomocna.getId());
            PrihlaseniNaKurzy();
        }
        else
        {
            databaze.Error("Nebyla vybrána žádná tvá akce v tabulce!");
        }
    }
    
    
    @FXML
    public void Clean()
    {
        Ujmeno.setText("");
        Uheslo.setText("");
        rUjmeno.setText("");
        rHeslo.setText("");
        rHeslo2.setText("");
        rJmeno.setText("");
        rEmail.setText("");
        textHledej.setText("");
    }
    
    @FXML
    public boolean stejniUzivatele(String Ujmeno,String email)
    {
        uzivatele = databaze.Uzivatele();
        
        for(Uzivatel pom : uzivatele)
        {
            if(pom.getUjmeno().equals(Ujmeno) || pom.getEmail().equals(email))
            {
                return false;
            }
        }
        return true;
    }
    
    
    @FXML
    public void Wishlist()
    {
        pMenu.setVisible(false);
        pWishlist.setVisible(true);
        
        oblibeneKurzy = databaze.getWishlist(prihlasenyU.getUjmeno());
        tWishlist.setItems(oblibeneKurzy);
    }
    
    @FXML
    public void ZpetWishList()
    {
        pMenu.setVisible(true);
        pWishlist.setVisible(false);
    }
    
    @FXML
    public void pridejDoWishlistu()
    {
        Kurz pomocny = tKurzyk.getSelectionModel().getSelectedItem();
        
        if(pomocny != null)
        {
            databaze.PridejKurzDoWishlistu(pomocny.getNazev(), pomocny.getUniverzita(), pomocny.getObor(), pomocny.getPrinos(), pomocny.getPopis(),prihlasenyU.getUjmeno());
        }
        else
        {
            databaze.Error("Nebyl vybrán žádný kurz v tabulce!");
        }
    }
    
    @FXML
    public void smazZWishlistu()
    {
        Kurz pomocny = tWishlist.getSelectionModel().getSelectedItem();
        
        if(pomocny != null)
        {
            databaze.SmazZWishlistu(pomocny.getId());
            Wishlist();
        }
        else
        {
            databaze.Error("Nebyl vybrán žádný kurz v tabulce!");
        } 
    }
    
    @FXML
    public void Hledej()
    {
        String text = "";
        text += textHledej.getText();
        
        kurzy = databaze.najdiKurzy(text);
        
        
        for(Kurz k : kurzy)
        {
            if(k.getHodnoceni() == null)
            {
                k.setHodnoceni(0.0);
            }
            hodnoceni = databaze.listHodnoceni(k.getId());
            for(Hodnoceni h : hodnoceni)
            {
                k.setHodnoceni(k.getHodnoceni() + h.getHvezdy());
            }
            if(hodnoceni.size()>0)
            {
                k.setHodnoceni(k.getHodnoceni() / hodnoceni.size());
            }
        }
        
        tKurzyk.setItems(kurzy);
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        hvezdy.add(1.0);
        hvezdy.add(2.0);
        hvezdy.add(3.0);
        hvezdy.add(4.0);
        hvezdy.add(5.0);
        
        
       idk.setCellValueFactory(new PropertyValueFactory<Kurz,Integer>("id")); 
       nazevk.setCellValueFactory(new PropertyValueFactory<Kurz,String>("nazev"));
       univerzitak.setCellValueFactory(new PropertyValueFactory<Kurz,String>("univerzita"));
       obork.setCellValueFactory(new PropertyValueFactory<Kurz,String>("obor"));
       hodnocenik.setCellValueFactory(new PropertyValueFactory<Kurz,Double>("hodnoceni"));
       prinosk.setCellValueFactory(new PropertyValueFactory<Kurz,String>("prinos"));
       popisk.setCellValueFactory(new PropertyValueFactory<Kurz,String>("popis"));
       
       nazevk.setCellFactory(TextFieldTableCell.forTableColumn());
       nazevk.setOnEditCommit(
                (TableColumn.CellEditEvent<Kurz,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNazev(t.getNewValue())
                );
       
       univerzitak.setCellFactory(TextFieldTableCell.forTableColumn());
       univerzitak.setOnEditCommit(
                (TableColumn.CellEditEvent<Kurz,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setUniverzita(t.getNewValue())
                );
       
       obork.setCellFactory(TextFieldTableCell.forTableColumn());
       obork.setOnEditCommit(
                (TableColumn.CellEditEvent<Kurz,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setObor(t.getNewValue())
                );
       
       prinosk.setCellFactory(TextFieldTableCell.forTableColumn());
       prinosk.setOnEditCommit(
                (TableColumn.CellEditEvent<Kurz,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrinos(t.getNewValue())
                );
       
       popisk.setCellFactory(TextFieldTableCell.forTableColumn());
       popisk.setOnEditCommit(
                (TableColumn.CellEditEvent<Kurz,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPopis(t.getNewValue())
                );
       
       
       dena.setCellValueFactory(new PropertyValueFactory<Akce,String>("den"));
       casa.setCellValueFactory(new PropertyValueFactory<Akce,String>("cas"));
       mistnosta.setCellValueFactory(new PropertyValueFactory<Akce,String>("mistnost"));
       ida.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("id")); 
       idka.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("kurz")); 
       ucasta.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("ucast")); 
       kapacitaa.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("kapacita")); 
       
       dena.setCellFactory(TextFieldTableCell.forTableColumn());
       dena.setOnEditCommit(
                (TableColumn.CellEditEvent<Akce,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDen(t.getNewValue())
                );
       casa.setCellFactory(TextFieldTableCell.forTableColumn());
       casa.setOnEditCommit(
                (TableColumn.CellEditEvent<Akce,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCas(t.getNewValue())
                );
       mistnosta.setCellFactory(TextFieldTableCell.forTableColumn());
       mistnosta.setOnEditCommit(
                (TableColumn.CellEditEvent<Akce,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setMistnost(t.getNewValue())
                );
       kapacitaa.setCellFactory(TextFieldTableCell.<Akce, Integer>forTableColumn(new IntegerStringConverter()));
       kapacitaa.setOnEditCommit(
                (TableColumn.CellEditEvent<Akce,Integer> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setKapacita(t.getNewValue())
                );

       nazevAu.setCellValueFactory(new PropertyValueFactory<Akce,String>("nazev"));
       denAu.setCellValueFactory(new PropertyValueFactory<Akce,String>("den"));
       casAu.setCellValueFactory(new PropertyValueFactory<Akce,String>("cas"));
       mistnostAu.setCellValueFactory(new PropertyValueFactory<Akce,String>("mistnost"));
       idAu.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("id"));
       
       
       
       
       idW.setCellValueFactory(new PropertyValueFactory<Kurz,Integer>("id")); 
       nazevW.setCellValueFactory(new PropertyValueFactory<Kurz,String>("nazev"));
       univerzitaW.setCellValueFactory(new PropertyValueFactory<Kurz,String>("univerzita"));
       oborW.setCellValueFactory(new PropertyValueFactory<Kurz,String>("obor"));
       prinosW.setCellValueFactory(new PropertyValueFactory<Kurz,String>("prinos"));
       popisW.setCellValueFactory(new PropertyValueFactory<Kurz,String>("popis"));
       
       
       
       
    }    
    
}
