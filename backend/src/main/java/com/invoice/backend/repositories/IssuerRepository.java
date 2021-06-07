package com.invoice.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.backend.entities.Issuer;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, UUID> {

}
