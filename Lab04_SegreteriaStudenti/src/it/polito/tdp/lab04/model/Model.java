package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	public List<String> elencoCorsiconSpazio(){
		CorsoDAO c=new CorsoDAO();
		List<String> corsi=new LinkedList<String>();
		corsi.add("");
		for(Corso cr:c.getTuttiICorsi()){
			corsi.add(cr.getNome());
		}
	    return corsi;
	}
	
	public List<String> ritornaGeneralita(int matricola) {
		StudenteDAO d=new StudenteDAO();
		List<String> generalita=new LinkedList<String>();
	    for(Studente s:d.getTuttiStudenti()) {
			if(s.getMatricola()==matricola) {
				generalita.add(s.getNome());
				generalita.add(s.getCognome());
		    }
		}
		return generalita;
	}
	
	public String elencoStudenti(String nomeCorso) {
		String elenco="";
		CorsoDAO c=new CorsoDAO();
		for(Corso cr:c.getTuttiICorsi()) {
			if(cr.getNome().compareTo(nomeCorso)==0) {
				for(Studente s:c.getStudentiIscrittiAlCorso(cr)) {
					elenco+=s.toString()+"\n";
					}
			}
		}
	    return elenco;
	}
	
	public boolean Studentepresente(int matricola) {
		StudenteDAO s=new StudenteDAO();
		for(Studente st:s.getTuttiStudenti()) {
			if(st.getMatricola()==matricola) {
				return true;
			}
		}
		return false;
	}
	
	public String elencoCorsiStudente(int matricola) {
		StudenteDAO s=new StudenteDAO();
		String elenco="";
		for(Corso c:s.getCorsiStudente(matricola))
			elenco+=c.toString()+"\n";
		return elenco;
	}
	
	public boolean presenteCorso(int matricola, String nomeCorso) {
		CorsoDAO c=new CorsoDAO();
		for(Corso cr:c.getTuttiICorsi()) {
			if(cr.getNome().compareTo(nomeCorso)==0) {
				for(Studente s:c.getStudentiIscrittiAlCorso(cr)) { 
			         if(s.getMatricola()==matricola)
				           return true;
				}
			}
		}
		return false;
	}
	
	public void iscrivi(int mat,String nomeCorso) {
		CorsoDAO c=new CorsoDAO();
		for(Corso cr:c.getTuttiICorsi()) {
			if(cr.getNome().compareTo(nomeCorso)==0)
				c.inscriviStudenteACorso(mat, cr.getCodins());
		}
	}

}
