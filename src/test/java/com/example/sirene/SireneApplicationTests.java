package com.example.sirene;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sirene.dto.EtablissementDto;
import com.example.sirene.service.EtablissementService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SireneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = { "com.example.sirene" })
public class SireneApplicationTests {

	@Autowired
	private EtablissementService etablissementService;

	private static final String SIRET_NUMBER = "31302979500017";

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetInfoEtablissement() {

		EtablissementDto etablissementDto = etablissementService.findBySiretNumber(SIRET_NUMBER);
		Assert.assertNotNull(etablissementDto);
	}

}
