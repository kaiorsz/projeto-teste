package com.example.projetoteste.pojo.output;

import java.util.List;

public class VendaVO {
    private Integer id;
    private String cliente;
    private Double valor_total;
    private List<VendaProdutoVO> venda_produtos;

    public List<VendaProdutoVO> getVenda_produtos() {
        return venda_produtos;
    }

    public void setVenda_produtos(List<VendaProdutoVO> venda_produtos) {
        this.venda_produtos = venda_produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }
}
