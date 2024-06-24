package com.example.projetoteste.service;

import com.example.projetoteste.entity.Ingresso;
import com.example.projetoteste.pojo.input.VendaDTO;

import java.util.List;

public interface IngressoService {

//    void criaIngresso(IngressoDTO ingressoDTO);

    List<Ingresso> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel);

    void vendeIngresso(VendaDTO vendaDTO);
}
