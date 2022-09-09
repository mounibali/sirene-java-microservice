package com.example.sirene.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sirene.dto.EtablissementDto;
import com.example.sirene.response.RequestResponse;
import com.example.sirene.service.EtablissementService;
import com.example.sirene.utils.CommonUtils;
import com.opencsv.CSVWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EtablissementServiceImpl implements EtablissementService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.api.to.database.job.api.url}")
	private String urlWS;

	@Autowired
	private CommonUtils commonUtils;

	@Override
	public EtablissementDto findBySiretNumber(String siret) {

		// Consume web service :
		// https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/{siret}
		final ResponseEntity<RequestResponse> response = restTemplate.getForEntity(urlWS, RequestResponse.class, siret);

		// Save result into dto object : EtablissementDto
		final EtablissementDto etablissementDto = response.getBody().getEtablissement();

		return etablissementDto;
	}

	@Override
	public File sendDataToCsv(EtablissementDto etablissementDto) {

		File file = new File("data-csv\\etablissement.csv");
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsolutePath(), true));

			if (file.length() == 0) {
				// Insert header if file is empty
				String[] csvHeader = { "idSiret", "fullAdresse", "dateCreation", "fullName", "tvaSerial" };
				writer.writeNext(csvHeader);

			}
			// Insert values returned by webservice
			List<String> record = Arrays.asList(etablissementDto.getIdSiret(), etablissementDto.getFullAdresse(),
					commonUtils.dateToFormat(etablissementDto.getDateCreation(), "dd/MM/yyyy"),
					etablissementDto.getUniteLegale().getFullName(), etablissementDto.getUniteLegale().getTva());

			String[] array = record.toArray(new String[0]);
			writer.writeNext(array);
			writer.close();

		} catch (IOException exception) {
			log.error(exception.getMessage());
		}
		return file;
	}

}
