package com.example.projetoteste.service.impl;

import com.example.projetoteste.dao.ProdutoDao;
import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.jdbc.ConexaoJDBC;
import com.example.projetoteste.pojo.input.ProdutoDTO;
import com.example.projetoteste.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class ProdutoServiceImpl implements ProdutoService {

    private ProdutoDao produtoDao;

    public ProdutoServiceImpl() {
        this.produtoDao = new ProdutoDao(ConexaoJDBC.getJdbcTemplate());
    }

    @Override
    public void criaProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO.getQuantidade_disponivel() <= 0) {
            throw new RuntimeException("Quantidade disponível não pode ser vazia.");
        }
        if(produtoDTO.getValor_unitario() <= 0) {
            throw new RuntimeException("Valor unitário não pode ser vazio.");
        }
        Produto produto = dtoToEntiy(produtoDTO);
        produtoDao.criar(produto);
    }

    private static Produto dtoToEntiy(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade_disponivel(produtoDTO.getQuantidade_disponivel());
        produto.setValor_unitario(produtoDTO.getValor_unitario());
        return produto;
    }

    @Override
    public List<Produto> findAll(Integer page, Integer size, String nome, String sortBy, String sortOrder) {
        List<Produto> produtos = produtoDao.encontrarProdutos(page, size, nome, sortBy, sortOrder);

        return produtos;
    }

    @Override
    public void update(ProdutoDTO produtoDTO, Integer id) {

        if(produtoDao.encontraPorId(id) == null) {
            throw new RuntimeException("Produto não encontrado.");
        }

        Produto produto = dtoToEntiy(produtoDTO);

        produtoDao.atualizarPorId(produto, id);
    }

    @Override
    public void delete(Integer id) {
        produtoDao.deletaPorId(id);
    }

    @Override
    public Produto encontraPorId(Integer id) {
        return produtoDao.encontraPorId(id);
    }
}
