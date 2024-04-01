package com.example.projetoteste.dao;

import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.jdbc.ConexaoJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProdutoDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    private class ProdutoRowMapper implements RowMapper<Produto> {
        @Override
        public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQuantidade_disponivel(rs.getInt("quantidade_disponivel"));
            produto.setValor_unitario(rs.getDouble("valor_unitario"));
            return produto;
        }
    }

    public void criar(Produto produto) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO produto (nome, descricao, quantidade_disponivel, valor_unitario) VALUES (?, ?, ?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), produto.getNome(), produto.getDescricao(), produto.getQuantidade_disponivel(), produto.getValor_unitario());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Produto> encontrarProdutos(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM produto");

            if(disponivel) {
                sql.append(" WHERE quantidade_disponivel > 0 ");
            }

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

    public Produto encontraPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM produto WHERE id = ?");
            List<Produto> produtos = conexaoJDBC.getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper(), id);

            return produtos.size() > 0 ? produtos.get(0) : null;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deletaPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM produto WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void atualizarPorId(Produto produto, Integer id) {
        try {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE produto SET nome = ?, descricao = ?, quantidade_disponivel = ?, valor_unitario = ? WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sqlBuilder.toString(), produto.getNome(), produto.getDescricao(), produto.getQuantidade_disponivel(), produto.getValor_unitario(), id);
        } catch (Exception e) {
            throw e;
        }
    }
}
