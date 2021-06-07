package com.invoice.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItems extends JpaRepository<InvoiceItems, UUID> {

}
