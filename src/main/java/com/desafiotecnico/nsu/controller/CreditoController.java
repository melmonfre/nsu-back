package com.desafiotecnico.nsu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafiotecnico.nsu.dto.CreditoResponseDTO;

public interface CreditoController {

    ResponseEntity<CreditoResponseDTO> findByNumeroCredito(@PathVariable String numeroCredito);

    ResponseEntity<List<CreditoResponseDTO>> findByNumeroNfse(@PathVariable String numeroNfse);
}
