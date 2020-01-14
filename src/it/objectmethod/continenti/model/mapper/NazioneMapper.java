package it.objectmethod.continenti.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.continenti.model.Nazione;

public class NazioneMapper implements RowMapper<Nazione>{

	@Override
	public Nazione mapRow(ResultSet rs, int rowNum) throws SQLException {
		Nazione nazione=new Nazione();
		nazione.setCode(rs.getString("Code"));
		nazione.setName(rs.getString("Name"));
		nazione.setContinent(rs.getString("Continent"));
		nazione.setPopulation(rs.getInt("Population"));
		return nazione;
	}
}