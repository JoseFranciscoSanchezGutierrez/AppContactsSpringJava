package com.desa.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.desa.backend.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	
	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest() {
		
		ContactModel contactModel = new ContactModel(2, "Mikel", "Perez", "677211222", "Madrid");
		return new ResponseEntity<ContactModel>(contactModel, HttpStatus.OK);
	}

}
