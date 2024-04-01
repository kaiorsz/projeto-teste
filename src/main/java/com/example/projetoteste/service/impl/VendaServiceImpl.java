package com.example.projetoteste.service.impl;

import com.example.projetoteste.dao.ProdutoDao;
import com.example.projetoteste.dao.VendaDao;
import com.example.projetoteste.dao.VendaProdutoDao;
import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.entity.Venda;
import com.example.projetoteste.entity.VendaProduto;
import com.example.projetoteste.jdbc.ConexaoJDBC;
import com.example.projetoteste.pojo.input.VendaDTO;
import com.example.projetoteste.pojo.input.VendaProdutoDTO;
import com.example.projetoteste.pojo.output.VendaProdutoVO;
import com.example.projetoteste.pojo.output.VendaVO;
import com.example.projetoteste.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public @Service class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaDao vendaDao;

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private VendaProdutoDao vendaProdutoDao;

    @Override
    public VendaVO realizaVenda(List<VendaProdutoDTO> vendaProdutoDTOS, String cliente) throws Exception {
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setValor_total(0.0);

        if(vendaProdutoDTOS.isEmpty()) {
            throw new RuntimeException("Venda sem produtos. Tente novamente.");
        }

        if(cliente.isEmpty()) {
            throw new RuntimeException("Cliente não pode ser vazio. Tente novamente.");
        }

        List<Produto> produtosVendidos = new ArrayList<>();
        List<VendaProduto> vendasProdutos = new ArrayList<>();
        for (VendaProdutoDTO vendaProdutoDTO : vendaProdutoDTOS) {
            Produto produto = produtoDao.encontraPorId(vendaProdutoDTO.getId());
            if (produto == null) {
                throw new RuntimeException("Produto " + produto.getId() + " não encontrado. Tente novamente.");
            }
            if (produto.getQuantidade_disponivel() < vendaProdutoDTO.getQuantidade()) {
                throw new RuntimeException("Quantidade do produto " + produto.getId() + " indisponível. Tente novamente.");
            }
            produto.setQuantidade_disponivel(produto.getQuantidade_disponivel() - vendaProdutoDTO.getQuantidade());
            produtosVendidos.add(produto);

            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setProduto(produto.getId());
            vendaProduto.setQuantidade(vendaProdutoDTO.getQuantidade());
            vendasProdutos.add(vendaProduto);

            venda.setValor_total(venda.getValor_total() + (produto.getValor_unitario() * vendaProdutoDTO.getQuantidade()));
        }

        venda = vendaDao.criar(venda);

        for (VendaProduto vendaProduto : vendasProdutos) {
            vendaProduto.setVenda(venda.getId());
            vendaProdutoDao.criar(vendaProduto);
        }

        for (Produto produto : produtosVendidos) {
            produtoDao.atualizarPorId(produto, produto.getId());
        }

        return entityToVO(venda);
    }

    @Override
    public List<VendaVO> findAll(Integer page, Integer size, String sortBy, String sortOrder) {
        List<Venda> vendas = vendaDao.encontrar(page, size, sortBy, sortOrder);
        List<VendaVO> vendaVOS = new ArrayList<>();
        for (Venda venda : vendas) {
            vendaVOS.add(entityToVO(venda));
        }
        return vendaVOS;
    }

    @Transactional
    @Override
    public VendaVO update(VendaDTO vendaDTO, Integer id) {
        List<VendaProdutoDTO> vendaProdutoDTOS = vendaDTO.getVenda_produtos();
        if(vendaProdutoDTOS.isEmpty()) {
            throw new RuntimeException("Venda sem produtos. Tente novamente.");
        }

        Venda venda = vendaDao.encontrarPorId(id);
        if (venda == null) {
            throw new RuntimeException("Venda " + id + " não encontrada. Tente novamente.");
        }

        for(VendaProduto vendaProduto : vendaProdutoDao.encontraPorVenda(venda)) {
            Produto produto = produtoDao.encontraPorId(vendaProduto.getProduto());
            if(produto == null) {
                throw new RuntimeException("Produto " + vendaProduto.getProduto() + " não encontrado. Tente novamente.");
            }
            produto.setQuantidade_disponivel(produto.getQuantidade_disponivel() + vendaProduto.getQuantidade());

            produtoDao.atualizarPorId(produto, produto.getId());

            vendaProdutoDao.delete(vendaProduto.getId());
        }

        venda.setValor_total(0.0);
        venda.setCliente(vendaDTO.getCliente());

        List<Produto> produtosVendidos = new ArrayList<>();
        List<VendaProduto> vendasProdutos = new ArrayList<>();

        for (VendaProdutoDTO vendaProdutoDTO : vendaProdutoDTOS) {
            Produto produto = produtoDao.encontraPorId(vendaProdutoDTO.getId());
            if (produto == null) {
                throw new RuntimeException("Produto " + produto.getId() + " não encontrado. Tente novamente.");
            }
            if (produto.getQuantidade_disponivel() < vendaProdutoDTO.getQuantidade()) {
                throw new RuntimeException("Quantidade do produto " + produto.getId() + " indisponível. Tente novamente.");
            }
            produto.setQuantidade_disponivel(produto.getQuantidade_disponivel() - vendaProdutoDTO.getQuantidade());
            produtosVendidos.add(produto);

            VendaProduto vendaProdutoEntity = new VendaProduto();
            vendaProdutoEntity.setProduto(produto.getId());
            vendaProdutoEntity.setQuantidade(vendaProdutoDTO.getQuantidade());
            vendasProdutos.add(vendaProdutoEntity);

            venda.setValor_total(venda.getValor_total() + (produto.getValor_unitario() * vendaProdutoDTO.getQuantidade()));
        }

        venda = vendaDao.atualizar(venda);

        for (VendaProduto vendaProdutoEntity : vendasProdutos) {
            vendaProdutoEntity.setVenda(venda.getId());
            vendaProdutoDao.criar(vendaProdutoEntity);
        }

        for (Produto produto : produtosVendidos) {
            produtoDao.atualizarPorId(produto, produto.getId());
        }

        return entityToVO(venda);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Venda venda = vendaDao.encontrarPorId(id);
        if (venda == null) {
            throw new RuntimeException("Venda " + id + " não encontrada. Tente novamente.");
        }

        for(VendaProduto vendaProduto : vendaProdutoDao.encontraPorVenda(venda)) {
            Produto produto = produtoDao.encontraPorId(vendaProduto.getProduto());
            if(produto == null) {
                throw new RuntimeException("Produto " + vendaProduto.getProduto() + " não encontrado. Tente novamente.");
            }
            produto.setQuantidade_disponivel(produto.getQuantidade_disponivel() + vendaProduto.getQuantidade());
            produtoDao.atualizarPorId(produto, produto.getId());

            vendaProdutoDao.delete(vendaProduto.getId());
        }

        vendaDao.delete(venda);
    }

    @Override
    public VendaVO encontraPorId(Integer id) {
        Venda venda = vendaDao.encontrarPorId(id);
        if (venda == null) {
            throw new RuntimeException("Venda " + id + " não encontrada. Tente novamente.");
        }
        return entityToVO(venda);
    }

    private VendaVO entityToVO(Venda venda) {
        VendaVO vendaVO = new VendaVO();
        vendaVO.setId(venda.getId());
        vendaVO.setCliente(venda.getCliente());
        vendaVO.setValor_total(venda.getValor_total());
        vendaVO.setVenda_produtos(new ArrayList<>());
        List<VendaProduto> vendaProdutos = vendaProdutoDao.encontraPorVenda(venda);
        for (VendaProduto vendaProduto : vendaProdutos) {
            VendaProdutoVO vendaProdutoVO = new VendaProdutoVO();
            vendaProdutoVO.setId(vendaProduto.getProduto());
            vendaProdutoVO.setQuantidade(vendaProduto.getQuantidade());

            Produto produto = produtoDao.encontraPorId(vendaProduto.getProduto());
            vendaProdutoVO.setValor_unitario(produto.getValor_unitario());
            vendaProdutoVO.setValor_total(produto.getValor_unitario() * vendaProduto.getQuantidade());
            vendaProdutoVO.setNome(produto.getNome());
            vendaVO.getVenda_produtos().add(vendaProdutoVO);
        }
        return vendaVO;
    }
}
