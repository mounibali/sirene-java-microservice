package com.example.sirene.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sirene.dto.EtablissementDto;
import com.example.sirene.response.RequestResponse;
import com.example.sirene.service.SireneService;
import com.example.sirene.utils.CommonUtils;

@Service
public class SireneServiceImpl implements SireneService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.api.to.database.job.api.url}")
	private String urlWS;

	@Autowired
	private CommonUtils commonUtils;

	ByteArrayInputStream byteArrayOutputStream = null;
	InputStreamResource fileInputStream = null;

	@Override
	public EtablissementDto findBySiretNumber() {

		final ResponseEntity<RequestResponse> response = restTemplate.getForEntity(urlWS, RequestResponse.class);

		final EtablissementDto etablissementDto = response.getBody().getEtablissement();

		return etablissementDto;
	}

	@Override
	public Resource downloadMyCSV(EtablissementDto etablissementDto) {
		// Header CSV
		String[] csvHeader = { "idSiret", "fullAdresse", "dateCreation", "fullName", "tvaSerial" };

		// Values to print in CSV file
		List<List<String>> csvBody = new ArrayList<>();
		csvBody.add(Arrays.asList(etablissementDto.getIdSiret(), etablissementDto.getFullAdresse(),
				commonUtils.dateToFormat(etablissementDto.getDateCreation(), "dd/MM/yyyy"),
				etablissementDto.getUniteLegale().getFullName(), etablissementDto.getUniteLegale().getTva()));

		// closing resources by using a try with resources
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				// defining the CSV printer
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),
						// withHeader is optional
						CSVFormat.DEFAULT.withHeader(csvHeader).withDelimiter(';'));) {

			for (List<String> record : csvBody)
				for (int i = 0; i < record.size(); i++) {
					csvPrinter.print(record.get(i));
				}
			// writing the underlying stream
			csvPrinter.flush();

			byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
			fileInputStream = new InputStreamResource(byteArrayOutputStream);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

		return fileInputStream;
	}

}
