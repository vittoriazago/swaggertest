package br.com.swaggertest.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.automation.service.swagger.SwaggerTest;

@RequestMapping(value = "/teste")
@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
public class SwaggerController {

	@Autowired
	public SwaggerTest swaggerTest;
	
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String Teste(String url) throws Exception {
		swaggerTest.executaFluxo(url);
		return url;
	}

}
