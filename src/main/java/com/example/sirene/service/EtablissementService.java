package com.example.sirene.service;

import java.io.File;

import com.example.sirene.dto.EtablissementDto;

public interface EtablissementService {

	public EtablissementDto findBySiretNumber(String siret);

	public File sendDataToCsv(EtablissementDto etablissementDto);
}
