package com.example.sirene.service;

import org.springframework.core.io.Resource;

import com.example.sirene.dto.EtablissementDto;

public interface SireneService {

	public EtablissementDto findBySiretNumber();

	public Resource downloadMyCSV(EtablissementDto list);
}
