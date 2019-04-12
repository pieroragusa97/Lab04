package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> btnScegliCorso;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField NumeroM;

    @FXML
    private TextField NomeStudente;

    @FXML
    private TextField CognomeStudente;

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

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void doScegliCorso(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnScegliCorso != null : "fx:id=\"btnScegliCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert NumeroM != null : "fx:id=\"NumeroM\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert NomeStudente != null : "fx:id=\"NomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert CognomeStudente != null : "fx:id=\"CognomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert AreaRisultato != null : "fx:id=\"AreaRisultato\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
}
