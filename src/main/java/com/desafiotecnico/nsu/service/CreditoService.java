package com.desafiotecnico.nsu.service;

import java.util.List;

import com.desafiotecnico.nsu.dto.CreditoResponseDTO;

public interface CreditoService {
    List<CreditoResponseDTO> findByNumeroNfse(String numeroNfse);
    CreditoResponseDTO findByNumeroCredito(String numeroCredito);
}
