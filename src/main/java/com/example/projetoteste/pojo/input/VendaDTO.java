package com.example.projetoteste.pojo.input;

import java.util.List;

public class VendaDTO {

    private String cliente;
    private List<VendaProdutoDTO> venda_produtos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<VendaProdutoDTO> getVenda_produtos() {
        return venda_produtos;
    }

    public void setVenda_produtos(List<VendaProdutoDTO> venda_produtos) {
        this.venda_produtos = venda_produtos;
    }
}
