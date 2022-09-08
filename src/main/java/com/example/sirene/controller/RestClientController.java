
package com.example.sirene.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirene.dto.EtablissementDto;
import com.example.sirene.service.SireneService;
import com.example.sirene.utils.CommonUtils;

@RestController
@RequestMapping("/api")
public class RestClientController {

	@Value("${rest.csv.file.name}")
	private String fileName;

	private SireneService sireneService;
	private CommonUtils commonUtils;

	private static final String FILE_CONTENT = "text/csv";
	private static final String FILE_EXTENSION = ".csv";

	public RestClientController(SireneService sireneService, CommonUtils commonUtils) {
		this.sireneService = sireneService;
		this.commonUtils = commonUtils;
	}

	@GetMapping("/downloadCSV")
	public ResponseEntity<Resource> getInfoEtablissement(HttpServletResponse response) throws Exception {

		final EtablissementDto etablissementDto = sireneService.findBySiretNumber();

		final InputStreamResource fileInputStream = (InputStreamResource) sireneService.downloadMyCSV(etablissementDto);

		// setting HTTP headers
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + commonUtils.getFileName(FILE_EXTENSION, fileName));
		headers.set(HttpHeaders.CONTENT_TYPE, FILE_CONTENT);

		return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
	}

}
