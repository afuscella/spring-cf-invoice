package com.invoice.backend.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "tb_invoice")
public class Invoice {

	@Id
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUIDGenerator",
			strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGenerator")
	@EqualsAndHashCode.Include
	private UUID uuid;

	private String fiscalNumber;

	private String model;

	private BigDecimal total;

	private Instant issuedAt;

	@OneToMany(mappedBy = "invoice",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<InvoiceItems> items = new HashSet<>();

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Builder.Default
	private Instant createdAt = Instant.now();

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Builder.Default
	private Instant updatedAt = Instant.now();

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

}
