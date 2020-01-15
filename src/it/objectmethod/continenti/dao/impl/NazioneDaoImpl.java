package it.objectmethod.continenti.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Nazione;
import it.objectmethod.continenti.model.mapper.NazioneMapper;

public class NazioneDaoImpl implements INazioneDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Nazione> getNationByContinent(String continent) {
		List<Nazione> nazioni = new ArrayList<Nazione>();
		
		String sql = "SELECT code,name,continent,population FROM world.country WHERE continent = ?";
		nazioni = this.jdbcTemplateObject.query(sql, new Object[] { continent }, new NazioneMapper());
		return nazioni;
	}

	@Override
	public List<Nazione> getNations() {

		List<Nazione> nazioni = new ArrayList<Nazione>();
		String sql = "SELECT country.Code,country.Name,country.Continent, country.Population FROM world.country;";
		nazioni = this.jdbcTemplateObject.query(sql, new Object[] {}, new NazioneMapper());
		return nazioni;

	}

	@Override
	public List<String> getContinents() {

		List<String> continenti = new ArrayList<String>();
		String sql = "SELECT DISTINCT country.continent FROM world.country;";
		continenti = this.jdbcTemplateObject.queryForList(sql, String.class);
		return continenti;

	}
}