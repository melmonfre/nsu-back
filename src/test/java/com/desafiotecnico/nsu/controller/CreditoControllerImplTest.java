package com.desafiotecnico.nsu.controller;

import com.desafiotecnico.nsu.controller.impl.CreditoControllerImpl;
import com.desafiotecnico.nsu.dto.CreditoResponseDTO;
import com.desafiotecnico.nsu.entity.Credito;
import com.desafiotecnico.nsu.service.impl.CreditoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoControllerImplTest {

    @Mock
    private CreditoServiceImpl creditoServiceImpl;

    @InjectMocks
    private CreditoControllerImpl creditoControllerImpl;

    private CreditoResponseDTO creditoResponseDTO;

    @BeforeEach
    void setUp() {
        Credito credito = new Credito();
        credito.setId(1L);
        credito.setNumeroCredito("CRED123");
        credito.setNumeroNfse("NFSE456");
        credito.setDataConstituicao(LocalDate.of(2023, 10, 1));
        credito.setValorIssqn(new BigDecimal("100.00"));
        credito.setTipoCredito("TIPO1");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("0.05"));
        credito.setValorFaturado(new BigDecimal("1000.00"));
        credito.setValorDeducao(new BigDecimal("50.00"));
        credito.setBaseCalculo(new BigDecimal("950.00"));

        creditoResponseDTO = new CreditoResponseDTO(credito);
        creditoResponseDTO.setId(1L);
        creditoResponseDTO.setNumeroCredito("CRED123");
        creditoResponseDTO.setNumeroNfse("NFSE456");
        creditoResponseDTO.setDataConstituicao(LocalDate.of(2023, 10, 1));
        creditoResponseDTO.setValorIssqn(new BigDecimal("100.00"));
        creditoResponseDTO.setTipoCredito("TIPO1");
        creditoResponseDTO.setSimplesNacional(true);
        creditoResponseDTO.setAliquota(new BigDecimal("0.05"));
        creditoResponseDTO.setValorFaturado(new BigDecimal("1000.00"));
        creditoResponseDTO.setValorDeducao(new BigDecimal("50.00"));
        creditoResponseDTO.setBaseCalculo(new BigDecimal("950.00"));
    }

    @Test
    void findByNumeroNfseReturnsOkWithListOfCreditoResponseDTO() {
        when(creditoServiceImpl.findByNumeroNfse("NFSE456")).thenReturn(List.of(creditoResponseDTO));

        ResponseEntity<List<CreditoResponseDTO>> response = creditoControllerImpl.findByNumeroNfse("NFSE456");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        assertEquals(creditoResponseDTO, response.getBody().get(0));
    }

    @Test
    void findByNumeroNfseReturnsOkWithEmptyList() {
        when(creditoServiceImpl.findByNumeroNfse("NFSE999")).thenReturn(Collections.emptyList());

        ResponseEntity<List<CreditoResponseDTO>> response = creditoControllerImpl.findByNumeroNfse("NFSE999");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void findByNumeroCreditoReturnsOkWithCreditoResponseDTO() {
        when(creditoServiceImpl.findByNumeroCredito("CRED123")).thenReturn(creditoResponseDTO);

        ResponseEntity<CreditoResponseDTO> response = creditoControllerImpl.findByNumeroCredito("CRED123");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(creditoResponseDTO, response.getBody());
    }

    @Test
    void findByNumeroCreditoThrowsIndexOutOfBoundsExceptionWhenNotFound() {
        when(creditoServiceImpl.findByNumeroCredito("CRED999")).thenThrow(IndexOutOfBoundsException.class);

        assertThrows(IndexOutOfBoundsException.class, () -> creditoControllerImpl.findByNumeroCredito("CRED999"));
    }
}