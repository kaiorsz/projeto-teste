package com.example.projetoteste.service;

import com.example.projetoteste.entity.Ingresso;
import com.example.projetoteste.entity.Usuario;
import com.example.projetoteste.pojo.input.UsuarioDTO;
import com.example.projetoteste.pojo.input.VendaDTO;

import java.util.List;

public interface UsuarioService {
    Usuario encontraPorId(Integer id);
    List<Usuario> encontraPorCpf(String cpf);

    List<Usuario> findAll(int page, int size, String sortBy, String sortOrder);

    void criaUsuario(UsuarioDTO usuario);
}
