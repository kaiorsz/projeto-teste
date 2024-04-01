package com.example.projetoteste.dao;

import com.example.projetoteste.entity.Venda;
import com.example.projetoteste.entity.VendaProduto;
import com.example.projetoteste.jdbc.ConexaoJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class VendaProdutoDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public void delete(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM venda_produto WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void criar(VendaProduto vendaProdutoEntity) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO venda_produto (produto, quantidade, venda) VALUES (?, ?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), vendaProdutoEntity.getProduto(), vendaProdutoEntity.getQuantidade(), vendaProdutoEntity.getVenda());
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
            List<VendaProduto> vendaProdutos = conexaoJDBC.getJdbcTemplate().query(sql.toString(), new VendaProdutoRowMapper(), venda.getId());

            return vendaProdutos;
        } catch (Exception e) {
            throw e;
        }
    }
}
