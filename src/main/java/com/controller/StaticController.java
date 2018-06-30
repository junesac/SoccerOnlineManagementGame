package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Country;
import com.model.PlayerType;

@RestController
@RequestMapping("/static")
public class StaticController {

	@RequestMapping("/getAllCountries")
	public Map<String, String> getAllCountries() throws Exception {
		Map<String, String> map = new HashMap<>();
		for (Country country : Country.values()) {
			map.put(country.toString(), country.getName());
		}
		return map;
	}

	@RequestMapping("/getAllPlayerTypes")
	public Map<String, String> getAllPlayerTypes() {
		Map<String, String> map = new HashMap<>();
		for (PlayerType playerType : PlayerType.values()) {
			map.put(playerType.toString(), playerType.getName());
		}
		return map;
	}

}
