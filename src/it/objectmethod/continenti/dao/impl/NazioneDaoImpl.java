package it.objectmethod.continenti.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.objectmethod.continenti.config.ConnectionFactory;
import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.domain.Nazione;

public class NazioneDaoImpl implements INazioneDao {
	
	@Override
	public List<Nazione> getNationByContinent(String continentNation) {
		List<Nazione> nazioni = new ArrayList<Nazione>();
		Nazione nazione = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT code,name,continent,population FROM world.country WHERE continent = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, continentNation);
			rs = stmt.executeQuery();
			while (rs.next()) {
				nazione = new Nazione();
				nazione.setCode(rs.getString("Code"));
				nazione.setName(rs.getString("Name"));
				nazione.setContinent(rs.getString("Continent"));
				nazione.setPopulation(rs.getInt("Population"));
				nazioni.add(nazione);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return nazioni;
	}
	
	@Override
	public List<Nazione> getNations(){
		
		List<Nazione> nazioni = new ArrayList<Nazione>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rsDue = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT country.Code,country.Name FROM world.country;";
			stmt = conn.prepareStatement(sql);
			rsDue = stmt.executeQuery();
			while (rsDue.next()) {
				String codice = rsDue.getString("Code");
				String nome = rsDue.getString("Name");
				Nazione nazioneProva = new Nazione();
				nazioneProva.setCode(codice);
				nazioneProva.setName(nome);
				nazioni.add(nazioneProva);
			}
			rsDue.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return nazioni;
		
		
	}
	
	@Override
	public List<String> getContinents() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<String> continents = new ArrayList<String>();
		try {
			conn = ConnectionFactory.getConnection();

			String query = "SELECT DISTINCT country.continent FROM world.country;";
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			rs = stmt.executeQuery();
			while (rs.next()) {
				continents.add(rs.getString("Continent"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return continents;
	}

}