package com.desafiotecnico.nsu.service.impl;

import com.desafiotecnico.nsu.dto.CreditoResponseDTO;
import com.desafiotecnico.nsu.dto.QueryEventDTO;
import com.desafiotecnico.nsu.repository.CreditoRepository;
import com.desafiotecnico.nsu.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final KafkaTemplate<String, QueryEventDTO> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Override
    public List<CreditoResponseDTO> findByNumeroNfse(String numeroNfse) {
        List<CreditoResponseDTO> result = this.creditoRepository.findByNumeroNfse(numeroNfse)
                .stream()
                .map(CreditoResponseDTO::new)
                .toList();
        kafkaTemplate.send(topicName, new QueryEventDTO("numeroNfse", numeroNfse));
        return result;
    }

    @Override
    public CreditoResponseDTO findByNumeroCredito(String numeroCredito) {
        CreditoResponseDTO result = this.creditoRepository.findByNumeroCredito(numeroCredito)
                .stream()
                .map(CreditoResponseDTO::new)
                .toList()
                .get(0);
        kafkaTemplate.send(topicName, new QueryEventDTO("numeroCredito", numeroCredito));
        return result;
    }
}