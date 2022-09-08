package com.example.sirene.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniteLegale implements Serializable {

	@JsonProperty("denomination")
	private String fullName;

	@JsonProperty("numero_tva_intra")
	private String tva;

}
