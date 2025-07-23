package com.desafiotecnico.nsu.service;

import com.desafiotecnico.nsu.dto.CreditoResponseDTO;
import com.desafiotecnico.nsu.dto.QueryEventDTO;
import com.desafiotecnico.nsu.entity.Credito;
import com.desafiotecnico.nsu.repository.CreditoRepository;
import com.desafiotecnico.nsu.service.impl.CreditoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoServiceImplTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private KafkaTemplate<String, QueryEventDTO> kafkaTemplate;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    private Credito credito;
    private CreditoResponseDTO creditoResponseDTO;

    @BeforeEach
    void setUp() {
        credito = new Credito();
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
    }

    @Test
    void findByNumeroNfseReturnsListOfCreditoResponseDTO() {
        when(creditoRepository.findByNumeroNfse("NFSE456")).thenReturn(List.of(credito));

        List<CreditoResponseDTO> result = creditoService.findByNumeroNfse("NFSE456");

        assertEquals(1, result.size());
        assertEquals(creditoResponseDTO, result.get(0));
    }

    @Test
    void findByNumeroNfseReturnsEmptyListWhenNoResults() {
        when(creditoRepository.findByNumeroNfse("NFSE999")).thenReturn(Collections.emptyList());

        List<CreditoResponseDTO> result = creditoService.findByNumeroNfse("NFSE999");

        assertEquals(0, result.size());
    }

    @Test
    void findByNumeroCreditoReturnsCreditoResponseDTO() {
        when(creditoRepository.findByNumeroCredito("CRED123")).thenReturn(List.of(credito));

        CreditoResponseDTO result = creditoService.findByNumeroCredito("CRED123");

        assertEquals(creditoResponseDTO, result);
    }

    @Test
    void findByNumeroCreditoThrowsIndexOutOfBoundsExceptionWhenNotFound() {
        when(creditoRepository.findByNumeroCredito("CRED999")).thenReturn(List.of());

        assertThrows(IndexOutOfBoundsException.class, () -> creditoService.findByNumeroCredito("CRED999"));
    }
}