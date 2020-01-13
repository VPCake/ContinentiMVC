package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.dao.impl.CittaDaoImpl;
import it.objectmethod.continenti.dao.impl.NazioneDaoImpl;
import it.objectmethod.continenti.domain.Citta;
import it.objectmethod.continenti.domain.Nazione;

@Controller
public class WorldController {

	@GetMapping("/index")
	public String index(ModelMap model) {

		List<String> elencoContinenti = new ArrayList<>();
		INazioneDao continentiDao = new NazioneDaoImpl();
		elencoContinenti = continentiDao.getContinents();

		model.addAttribute("continents", elencoContinenti);
		return "continenti";
	}

	@GetMapping("/nazioni")
	public String elencoNazioni(@RequestParam("codiceContinente") String codice, ModelMap model) {

		List<Nazione> elencoNaz = new ArrayList<>();
		INazioneDao codCont = new NazioneDaoImpl();
		elencoNaz = codCont.getNationByContinent(codice);
		model.addAttribute("nazioni", elencoNaz);
		return "nazioni";
	}

	@GetMapping("/citta/{codice}/show")
	public String elencoCitta(@PathVariable("codice") String codice, ModelMap model) {

		List<Citta> elencoCitta = new ArrayList<>();
		ICittaDao codCont = new CittaDaoImpl();
		elencoCitta = codCont.getCityByCountryCode(codice);
		model.addAttribute("citta", elencoCitta);
		return "citta";
	}
}
