package com.invoice.backend.models.response;

import java.io.Serializable;
import java.util.List;

import com.invoice.backend.models.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvoiceResponse implements Serializable {

	private final List<InvoiceDTO> data;

}
