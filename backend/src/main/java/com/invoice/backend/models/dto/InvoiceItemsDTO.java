package com.invoice.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invoice.backend.entities.InvoiceItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceItemsDTO implements Serializable {

	private UUID uuid;

	private Integer item;

	private String description;

	private String unitOfMeasure;

	private Integer quantity;

	private BigDecimal priceUnit;

	private BigDecimal total;

	@JsonIgnore
	private Instant createdAt = Instant.now();

	@JsonIgnore
	private Instant updatedAt = Instant.now();

	public InvoiceItemsDTO(InvoiceItems invoiceItems) {
		this.uuid = invoiceItems.getUuid();
		this.item = invoiceItems.getItem();
		this.description = invoiceItems.getDescription();
		this.unitOfMeasure = invoiceItems.getUnitOfMeasure();
		this.quantity = invoiceItems.getQuantity();
		this.priceUnit = invoiceItems.getPriceUnit();
		this.total = invoiceItems.getTotal();
		this.createdAt = invoiceItems.getCreatedAt();
		this.updatedAt = invoiceItems.getUpdatedAt();
	}

}
