package com.example.projetoteste.pojo.output;

import java.util.List;

public class VendaVO {
    private Integer id;
    private String cliente;
    private Double valor_total;
    private List<VendaProdutoVO> vendaProdutos;

    public List<VendaProdutoVO> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(List<VendaProdutoVO> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
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
