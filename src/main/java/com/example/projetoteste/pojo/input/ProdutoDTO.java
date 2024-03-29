package com.example.projetoteste.pojo.input;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoDTO {
    @Schema(description = "Nome do produto", example = "Celular", required = true)
    private String nome;
    @Schema(description = "Descrição do produto", example = "Celular novo", required = true)
    private String descricao;
    @Schema(description = "Quantidade disponível do produto", example = "10", required = true)
    private Integer quantidade_disponivel;
    @Schema(description = "Valor unitário do produto", example = "1000.00", required = true)
    private Double valor_unitario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public void setQuantidade_disponivel(Integer quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
}
