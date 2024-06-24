package com.example.projetoteste.service;

import com.example.projetoteste.entity.Evento;
import com.example.projetoteste.entity.Ingresso;
import com.example.projetoteste.pojo.input.EventoDTO;
import com.example.projetoteste.pojo.input.VendaDTO;

import java.util.List;

public interface EventoService {


    List<Evento> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel);

    void criaEvento(EventoDTO eventoDTO);
}
