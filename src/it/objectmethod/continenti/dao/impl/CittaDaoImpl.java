package it.objectmethod.continenti.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.continenti.config.ConnectionFactory;
import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.domain.Citta;

public class CittaDaoImpl implements ICittaDao {

	@Override
	public List<Citta> getCityByCountryCode(String nationCode) {
		Citta citta = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Citta> cities = new ArrayList<Citta>();
		try {
			conn = ConnectionFactory.getConnection();

			String query = "SELECT city.ID, city.Name,city.CountryCode,city.Population " + "FROM world.city "
					+ "WHERE city.CountryCode=?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, nationCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				citta = new Citta();
				citta.setId(rs.getInt("ID"));
				citta.setName(rs.getString("Name"));
				citta.setCountryCode(rs.getString("CountryCode"));
				citta.setPopulation(rs.getInt("Population"));
				cities.add(citta);
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

		return cities;
	}

	@Override
	public List<Citta> getSearch(String name, String code) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String nomeRicerca = "%" + name + "%";
		String codiceRicerca = code;
		List<Citta> cities = new ArrayList<Citta>();
		try {
			conn = ConnectionFactory.getConnection();

			String query = "SELECT ID,Name,CountryCode,Population FROM world.city WHERE Name LIKE ? AND (? = '' OR CountryCode = ?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, nomeRicerca);
			stmt.setString(2, codiceRicerca);
			stmt.setString(3, codiceRicerca);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Citta citta = new Citta();
				citta.setId(rs.getInt("ID"));
				citta.setName(rs.getString("Name"));
				citta.setCountryCode(rs.getString("CountryCode"));
				citta.setPopulation(rs.getInt("Population"));

				cities.add(citta);
			}
			rs.close();
		} catch (

		Exception e) {
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

		return cities;
	}

	@Override
	public int modifica(String name, int id, String codice) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows=0;
		try {
			conn = ConnectionFactory.getConnection();
			String queryMod = "UPDATE world.city SET Name = ?, CountryCode= ? WHERE ID=?";
			stmt = conn.prepareStatement(queryMod);
			stmt.setString(1, name);
			stmt.setString(2, codice);
			stmt.setInt(3, id);
			rows = stmt.executeUpdate();
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
		return rows;
	}

	@Override
	public Citta getCityById(int id) {
		Citta citta = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();

			String query = "SELECT city.ID, city.Name,city.CountryCode,city.Population " + "FROM world.city "
					+ "WHERE city.ID=?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				citta = new Citta();
				citta.setId(rs.getInt("ID"));
				citta.setName(rs.getString("Name"));
				citta.setCountryCode(rs.getString("CountryCode"));
				citta.setPopulation(rs.getInt("Population"));
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
		return citta;
	}
	
	@Override
	public int inserisci(String name, int id, String codice) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows=0;
		try {
			conn = ConnectionFactory.getConnection();
			String queryMod = "INSERT INTO world.city (ID, Name, CountryCode) VALUES (?, ?, ?)";
			stmt = conn.prepareStatement(queryMod);
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, codice);
			
			rows = stmt.executeUpdate();
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
		return rows;
	}
}
