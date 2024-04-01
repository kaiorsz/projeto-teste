package com.example.projetoteste.service;

import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.pojo.input.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    void criaProduto(ProdutoDTO produtoDTO);

    List<Produto> findAll(Integer page, Integer size, String nome, String sortBy, String sortOrder);

    void update(ProdutoDTO produto, Integer id);

    void delete(Integer id);

    Produto encontraPorId(Integer id);
}
