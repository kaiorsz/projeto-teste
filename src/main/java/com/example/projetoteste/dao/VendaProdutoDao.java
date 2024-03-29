package com.example.projetoteste.dao;

import com.example.projetoteste.entity.Venda;
import com.example.projetoteste.entity.VendaProduto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendaProdutoDao {

    JdbcTemplate jdbcTemplate;

    public VendaProdutoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM venda_produto WHERE id = ?");
            jdbcTemplate.update(sql.toString(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void criar(VendaProduto vendaProdutoEntity) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO venda_produto (produto, quantidade, venda) VALUES (?, ?, ?)");
            jdbcTemplate.update(sql.toString(), vendaProdutoEntity.getProduto(), vendaProdutoEntity.getQuantidade(), vendaProdutoEntity.getVenda());
        } catch (Exception e) {
            throw e;
        }
    }

    private class VendaProdutoRowMapper implements RowMapper<VendaProduto> {
        @Override
        public VendaProduto mapRow(ResultSet rs, int rowNum) throws SQLException {
            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setId(rs.getInt("id"));
            vendaProduto.setProduto(rs.getInt("produto"));
            vendaProduto.setQuantidade(rs.getInt("quantidade"));
            vendaProduto.setVenda(rs.getInt("venda"));
            return vendaProduto;
        }
    }

    public List<VendaProduto> encontraPorVenda(Venda venda) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM venda_produto WHERE venda = ?");
            List<VendaProduto> vendaProdutos = jdbcTemplate.query(sql.toString(), new VendaProdutoRowMapper(), venda.getId());

            return vendaProdutos;
        } catch (Exception e) {
            throw e;
        }
    }
}
