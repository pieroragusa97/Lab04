package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	public Model model=new Model();
	
	public void setModel(Model model) {
		this.model = model;
	}

	ObservableList<String> opzioni=FXCollections.observableArrayList(model.elencoCorsiconSpazio());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnScegliCorso;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField NumeroM;

    @FXML
    private TextField NomeStudente;

    @FXML
    private TextField CognomeStudente;
    
    @FXML
    private CheckBox completamentoAuto;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea AreaRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	try {
    	   if(btnCercaCorsi.isArmed()) {
    		   AreaRisultato.clear();
    		   if((model.Studentepresente(Integer.parseInt(NumeroM.getText()))==true))
    			  AreaRisultato.appendText(model.elencoCorsiStudente(Integer.parseInt(NumeroM.getText())));
    		   else
    			  AreaRisultato.appendText("Studente non trovato");
    	   }
    	}catch(NumberFormatException e) {
    		AreaRisultato.appendText("nessuna selezione effettuata");
    	}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	try {
    	   if(btnCercaIscritti.isArmed()) {
    		  AreaRisultato.clear();
    		  NomeStudente.clear();
  		      CognomeStudente.clear();
  		      NumeroM.clear();
    		  if(btnScegliCorso.getValue()!="") 
    			  AreaRisultato.appendText(model.elencoStudenti(btnScegliCorso.getValue()));
    		  else
    			  AreaRisultato.appendText("errore nella scelta del corso");
    	   }
    	}catch(NullPointerException e) {
    		AreaRisultato.appendText("selezione non valida");
    	}catch(NumberFormatException e) {
    		AreaRisultato.appendText("selezione non valida");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	if(btnIscrivi.isArmed())
    		AreaRisultato.clear();
    		if(model.presenteCorso((Integer.parseInt(NumeroM.getText())), btnScegliCorso.getValue())==true) {
    			AreaRisultato.appendText("studente gia iscritto");
    		}
    		else
    			model.iscrivi((Integer.parseInt(NumeroM.getText())), btnScegliCorso.getValue());
                 

    }
    
    @FXML
    void doCompleta(ActionEvent event) {
    	try {
    	   if(completamentoAuto.isArmed()) {
    		    NomeStudente.clear();
    		    CognomeStudente.clear();
    		    NomeStudente.appendText(model.ritornaGeneralita(Integer.parseInt(NumeroM.getText())).get(0));
    		    CognomeStudente.appendText(model.ritornaGeneralita(Integer.parseInt(NumeroM.getText())).get(1));
    	   }
    	}catch(NumberFormatException e) {
    		AreaRisultato.appendText("numero matricola non inserito\n");
    	}catch(IndexOutOfBoundsException e) {
    		AreaRisultato.appendText("numero matricola non valido\n");
    	}

    }
    
    @FXML
    void doReset(ActionEvent event) {
    	if(btnReset.isArmed()) {
    	    AreaRisultato.clear();
    	    NomeStudente.clear();
		    CognomeStudente.clear();
		    NumeroM.clear();
		    btnScegliCorso.setItems(opzioni);
    	}
    }

    @FXML
    void initialize() {
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert NumeroM != null : "fx:id=\"NumeroM\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert NomeStudente != null : "fx:id=\"NomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert CognomeStudente != null : "fx:id=\"CognomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert AreaRisultato != null : "fx:id=\"AreaRisultato\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert completamentoAuto != null : "fx:id=\"completamentoAuto\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        btnScegliCorso.setItems(opzioni);
    }
}
