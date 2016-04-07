package com.gestion.rel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerExample {


	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public String prueba(){
		return "a";
	}
}
