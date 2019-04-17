package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
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
		
		String sql="SELECT * FROM Corso WHERE codins=?";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, codice);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			     Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
			     return c;
			}
			
		    conn.close();
		    return  null;
		   
		    
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
		String sql="SELECT S.* FROM studente S, iscrizione I WHERE I.codins=? AND S.matricola=I.matricola";
		List<Studente> studenti=new LinkedList<Studente>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
			    System.out.println(rs.getInt("matricola") +" "+ rs.getString("nome")+" " + rs.getString("cognome")+" " + rs.getString("CDS"));
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
	public boolean inscriviStudenteACorso(int mat, String codice) {
		Model model=new Model();
		Corso corso=this.getCorso(codice);
		String sql="INSERT INTO iscrizione SET matricola=?,codins=?";
		
		if(model.Studentepresente(mat)==true&&model.presenteCorso(mat, corso.getNome())==false&&this.getTuttiICorsi().contains(corso)) {	
		    try {
			    Connection conn = ConnectDB.getConnection();
			    PreparedStatement st=conn.prepareStatement(sql);
			    st.setInt(1, mat);
			    st.setString(2, codice);
			    st.executeUpdate();
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
