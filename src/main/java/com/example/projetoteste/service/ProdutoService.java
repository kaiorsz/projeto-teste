package com.example.projetoteste.service;

import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.pojo.input.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    void criaProduto(ProdutoDTO produtoDTO);

    List<Produto> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel);

    void update(ProdutoDTO produto, Integer id);

    void delete(Integer id);

    Produto encontraPorId(Integer id);
}
