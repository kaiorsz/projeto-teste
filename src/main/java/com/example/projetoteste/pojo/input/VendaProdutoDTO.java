package com.example.projetoteste.pojo.input;

import io.swagger.v3.oas.annotations.media.Schema;

public class VendaProdutoDTO {

    private Integer codigoProduto;
    private Integer quantidade;

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
