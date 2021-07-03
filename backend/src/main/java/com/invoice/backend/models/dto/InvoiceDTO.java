package com.invoice.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invoice.backend.entities.Invoice;
import com.invoice.backend.entities.InvoiceItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDTO implements Serializable {

	private UUID uuid;

	private String fiscalNumber;

	private String model;

	private BigDecimal total;

	private Instant issuedAt;

	private List<InvoiceItemsDTO> items = new ArrayList<>();

	@JsonIgnore
	private Instant createdAt;

	@JsonIgnore
	private Instant updatedAt;

	public InvoiceDTO(Invoice invoice) {
		uuid = invoice.getUuid();
		fiscalNumber = invoice.getFiscalNumber();
		model = invoice.getModel();
		total = invoice.getTotal();
		issuedAt = invoice.getIssuedAt();
		createdAt = invoice.getCreatedAt();
		updatedAt = invoice.getUpdatedAt();
	}

	public InvoiceDTO(Invoice invoice, Set<InvoiceItems> invoiceItems) {
		this(invoice);
		invoiceItems.forEach(items -> this.getItems().add(new InvoiceItemsDTO(items)));
	}

}
