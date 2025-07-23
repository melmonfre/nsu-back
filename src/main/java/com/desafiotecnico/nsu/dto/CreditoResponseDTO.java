package com.desafiotecnico.nsu.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.desafiotecnico.nsu.entity.Credito;

import lombok.Data;

@Data
public class CreditoResponseDTO {
	private Long id;
	private String numeroCredito;
	private String numeroNfse;
	private LocalDate dataConstituicao;
	private BigDecimal valorIssqn;
	private String tipoCredito;
	private boolean simplesNacional;
	private BigDecimal aliquota;
	private BigDecimal valorFaturado;
	private BigDecimal valorDeducao;
	private BigDecimal baseCalculo;

	public CreditoResponseDTO(Credito credito) {
		this.id = credito.getId();
		this.numeroCredito = credito.getNumeroCredito();
		this.numeroNfse = credito.getNumeroNfse();
		this.dataConstituicao = credito.getDataConstituicao();
		this.valorIssqn = credito.getValorIssqn();
		this.tipoCredito = credito.getTipoCredito();
		this.simplesNacional = credito.isSimplesNacional();
		this.aliquota = credito.getAliquota();
		this.valorFaturado = credito.getValorFaturado();
		this.valorDeducao = credito.getValorDeducao();
		this.baseCalculo = credito.getBaseCalculo();
	}

}