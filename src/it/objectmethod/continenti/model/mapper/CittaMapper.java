package it.objectmethod.continenti.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.continenti.model.Citta;
import it.objectmethod.continenti.model.Nazione;

public class CittaMapper implements RowMapper<Citta> {

	@Override
	public Citta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Citta citta = new Citta();
		citta.setId(rs.getInt("Id"));
		citta.setName(rs.getString("Name"));
		citta.setCountryCode(rs.getString("CountryCode"));
		citta.setPopulation(rs.getInt("Population"));
		return citta;
	}
}