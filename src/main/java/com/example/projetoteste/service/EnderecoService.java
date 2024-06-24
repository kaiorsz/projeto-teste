package com.example.projetoteste.service;

import com.example.projetoteste.entity.Endereco;
import com.example.projetoteste.entity.Evento;
import com.example.projetoteste.pojo.input.EnderecoDTO;
import com.example.projetoteste.pojo.input.EventoDTO;

import java.util.List;

public interface EnderecoService {

    List<Endereco> findAll(Integer page, Integer size, String sortBy, String sortOrder);

    void criaEndereco(EnderecoDTO enderecoDTO);
}
