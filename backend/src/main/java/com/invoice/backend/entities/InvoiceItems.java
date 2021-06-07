package com.invoice.backend.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_invoice_items")
public class InvoiceItems {

	@Id
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUIDGenerator",
			strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGenerator")
	@EqualsAndHashCode.Include
	private UUID uuid;

	private Integer item;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String unitOfMeasure;

	private Integer quantity;

	private BigDecimal priceUnit;

	private BigDecimal total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_uuid",
			referencedColumnName = "uuid")
	private Invoice invoice;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Builder.Default
	private Instant createdAt = Instant.now();

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Builder.Default
	private Instant updatedAt = Instant.now();

	@PrePersist
	public void prePersist() {
		total = priceUnit.multiply(new BigDecimal(quantity));
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

}
