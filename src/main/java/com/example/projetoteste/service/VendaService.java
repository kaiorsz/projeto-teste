package com.example.projetoteste.service;

import com.example.projetoteste.pojo.input.ProdutoDTO;
import com.example.projetoteste.pojo.input.VendaDTO;
import com.example.projetoteste.pojo.input.VendaProdutoDTO;
import com.example.projetoteste.pojo.output.VendaVO;

import java.util.List;

public interface VendaService {

    VendaVO realizaVenda(List<VendaProdutoDTO> vendaProdutoDTOS, String cliente) throws Exception;

    List<VendaVO> findAll(Integer page, Integer size, String sortBy, String sortOrder);

    VendaVO update(VendaDTO vendaProduto, Integer id);

    void delete(Integer id);

    VendaVO encontraPorId(Integer id);
}
