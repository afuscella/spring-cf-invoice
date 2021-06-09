package com.invoice.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.backend.entities.Invoice;
import com.invoice.backend.exceptions.ResourceNotFoundException;
import com.invoice.backend.models.dto.InvoiceDTO;
import com.invoice.backend.models.response.InvoiceResponse;
import com.invoice.backend.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Transactional(readOnly = true)
	public InvoiceResponse handleAllPaged() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceDTO> data = invoices.stream().map(invoice -> new InvoiceDTO(invoice, invoice.getItems())).collect(Collectors.toList());
		return new InvoiceResponse(data);
	}

	public InvoiceDTO handleIndex(UUID uuid) {
		Optional<Invoice> invoiceOptional = invoiceRepository.findById(uuid);
		Invoice invoiceEntity = invoiceOptional.orElseThrow(ResourceNotFoundException::new);

		return new InvoiceDTO(invoiceEntity, invoiceEntity.getItems());
	}

}
