package com.invoice.backend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.backend.models.dto.InvoiceDTO;
import com.invoice.backend.models.response.InvoiceResponse;
import com.invoice.backend.services.InvoiceService;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping
	public ResponseEntity<InvoiceResponse> findAll() {
		InvoiceResponse invoiceResponse = invoiceService.handleAllPaged();
		return ResponseEntity.ok().body(invoiceResponse);
	}

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<InvoiceDTO> findByIndex(
			@PathVariable
					UUID uuid) {
		InvoiceDTO invoiceDTO = invoiceService.handleIndex(uuid);
		return ResponseEntity.ok().body(invoiceDTO);
	}

}
