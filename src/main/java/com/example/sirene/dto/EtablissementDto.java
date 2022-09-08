package com.example.sirene.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementDto implements Serializable {

	@JsonProperty("id")
	private String idSiret;

	private String nic;

	@JsonProperty("geo_adresse")
	private String fullAdresse;

	@JsonProperty("date_creation")
	private Date dateCreation;

	@JsonProperty("unite_legale")
	private UniteLegale uniteLegale;

}
