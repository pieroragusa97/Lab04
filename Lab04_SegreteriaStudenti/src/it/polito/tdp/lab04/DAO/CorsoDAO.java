package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso c=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codice) {
		String sql="SELECT * FROM Corso WHERE codins=codice";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
		
		    conn.close();
		    return c;
		    
	       }catch(SQLException e) {
		         throw new RuntimeException("Errore nel DB",e);
	       }
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String codice=corso.getCodins();
		String sql="SELECT S.* FROM studente S, iscrizione I WHERE I.codins=codice AND S.matricola=I.matricola";
		List<Studente> studenti=new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("pd"));
			    System.out.println(rs.getInt("matricola") +" "+ rs.getString("nome")+" " + rs.getString("cognome")+" " + rs.getString("pd"));
			    studenti.add(s);
			}
			conn.close();
			
		}catch(SQLException e) {
	         throw new RuntimeException("Errore nel DB",e);
      }
		return studenti;
		
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		StudenteDAO sd=new StudenteDAO();
		int mat=studente.getMatricola();
		String codice=corso.getCodins();
		String sql="INSERT INTO iscrizione SET matricola=mat,codins=codice";
		List<Studente> studenticorso=this.getStudentiIscrittiAlCorso(corso);
		
		if(sd.getTuttiStudenti().contains(studente)&&!studenticorso.contains(studente)&&this.getTuttiICorsi().contains(corso)) {
			
		    try {
			    Connection conn = ConnectDB.getConnection();
			    PreparedStatement st=conn.prepareStatement(sql);
			    ResultSet rs = st.executeQuery();
			    conn.close();
			    
		    }catch(SQLException e) {
		         throw new RuntimeException("Errore nel DB",e);
		    }
		    
		    return true;
		}
		
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
