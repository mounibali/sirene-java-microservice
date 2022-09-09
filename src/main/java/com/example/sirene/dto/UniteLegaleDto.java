package com.example.sirene.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniteLegaleDto implements Serializable {

	@JsonProperty("denomination")
	private String fullName;

	@JsonProperty("numero_tva_intra")
	private String tva;

}
