package com.example.projetoteste.service.impl;

import com.example.projetoteste.dao.EventoDao;
import com.example.projetoteste.dao.IngressoDao;
import com.example.projetoteste.dao.UsuarioDao;
import com.example.projetoteste.entity.Evento;
import com.example.projetoteste.entity.Ingresso;
import com.example.projetoteste.entity.Usuario;
import com.example.projetoteste.pojo.input.VendaDTO;
import com.example.projetoteste.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class IngressoServiceImpl implements IngressoService {

    @Autowired
    private IngressoDao ingressoDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private EventoDao eventoDao;

    @Override
    public List<Ingresso> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel) {
        List<Ingresso> ingressos = ingressoDao.encontrarIngressosVendidos(page, size, sortBy, sortOrder, disponivel);

        return ingressos;
    }

    @Override
    public void vendeIngresso(VendaDTO vendaDTO) {
        Usuario usuario = usuarioDao.encontraPorId(vendaDTO.getUsuario());
        if(usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        Evento evento = eventoDao.encontraPorId(vendaDTO.getEvento());
        if(evento == null) {
            throw new RuntimeException("Evento não encontrado.");
        }

        ingressoDao.vendeIngresso(vendaDTO);
    }
}
