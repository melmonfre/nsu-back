package com.desafiotecnico.nsu.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiotecnico.nsu.dto.CreditoResponseDTO;
import com.desafiotecnico.nsu.service.impl.CreditoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CreditoControllerImpl {

    private final CreditoServiceImpl creditoServiceImpl;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoResponseDTO>> findByNumeroNfse(@PathVariable String numeroNfse){
        List<CreditoResponseDTO> result = creditoServiceImpl.findByNumeroNfse(numeroNfse);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoResponseDTO> findByNumeroCredito(@PathVariable String numeroCredito){
        CreditoResponseDTO result = creditoServiceImpl.findByNumeroCredito(numeroCredito);
        return ResponseEntity.ok(result);
    }
    
}
