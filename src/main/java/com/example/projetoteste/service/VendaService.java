package com.example.projetoteste.service;

import com.example.projetoteste.pojo.input.ProdutoDTO;
import com.example.projetoteste.pojo.input.VendaProdutoDTO;
import com.example.projetoteste.pojo.output.VendaVO;

import java.util.List;

public interface VendaService {

    VendaVO realizaVenda(List<VendaProdutoDTO> vendaProdutoDTOS, String cliente) throws Exception;

    List<VendaVO> findAll(Integer page, Integer size, String sortBy, String sortOrder);

    VendaVO update(List<VendaProdutoDTO> vendaProduto, Integer id);

    void delete(Integer id);
}
