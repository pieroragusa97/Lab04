package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");

				System.out.println(matricola + " " + nome + " " + cognome + " " + cds);

				Studente s=new Studente(matricola,nome,cognome,cds);
				studenti.add(s);
				// Crea un nuovo JAVA Bean Studente
				// Aggiungi il nuovo oggetto Studente alla lista studenti
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return studenti;
	}
	
	public List<Corso> getCorsiStudente(int m){
		
		String sql="SELECT c.* FROM corso c, iscrizione i WHERE i.matricola=? AND c.codins=i.codins";
		List<Corso> corsi=new LinkedList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1,m);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				   String codins = rs.getString("codins");
					int numeroCrediti = rs.getInt("crediti");
					String nome = rs.getString("nome");
					int periodoDidattico = rs.getInt("pd");

					System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

                    Corso c=new Corso(codins,numeroCrediti,nome,periodoDidattico);
                    corsi.add(c);
				
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
		
	}


}
