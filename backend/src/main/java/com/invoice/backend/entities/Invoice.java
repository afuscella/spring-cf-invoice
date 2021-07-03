package com.invoice.backend.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Builder
@Entity
@EqualsAndHashCode(of = { "uuid" })
@Table(name = "tb_invoice")
public class Invoice {

	@Id
	@Column(nullable = false,
			updatable = false)
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUIDGenerator",
			strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGenerator")
	private UUID uuid;

	private String fiscalNumber;

	private String model;

	private BigDecimal total;

	private Instant issuedAt;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_uuid")
	private Recipient recipient;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issuer_uuid")
	private Issuer issuer;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "invoice",
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
