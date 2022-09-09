
package com.example.sirene.controller;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirene.dto.EtablissementDto;
import com.example.sirene.service.EtablissementService;
import com.example.sirene.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/compagnies")
@Slf4j
public class EtablissementController {

	private EtablissementService sireneService;

	public EtablissementController(EtablissementService sireneService, CommonUtils commonUtils) {
		this.sireneService = sireneService;
	}

	@GetMapping("/addCompagnieToCsv/{siret}")
	public EtablissementDto updateDataToCsv(@PathVariable String siret) throws Exception {

		final EtablissementDto etablissementDto = sireneService.findBySiretNumber(siret);

		final File file = sireneService.sendDataToCsv(etablissementDto);

		if (file.length() == 0) {
			log.info("CSV File {} is not updated ", file.getAbsolutePath());
		}

		return etablissementDto;
	}

}
