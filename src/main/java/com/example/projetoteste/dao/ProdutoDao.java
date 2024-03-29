package com.example.projetoteste.dao;

import com.example.projetoteste.entity.Produto;
import com.example.projetoteste.pojo.input.ProdutoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDao {

    JdbcTemplate jdbcTemplate;

    public ProdutoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
            jdbcTemplate.update(sql.toString(), produto.getNome(), produto.getDescricao(), produto.getQuantidade_disponivel(), produto.getValor_unitario());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Produto> encontrarProdutos(Integer page, Integer size, String nome, String sortBy, String sortOrder) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM produto");

            if (nome != null && !nome.isEmpty()) {
                sql.append(" WHERE upper(nome) LIKE '%").append(nome.toUpperCase()).append("%'");
            }

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return jdbcTemplate.query(sql.toString(), new ProdutoRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

    public Produto encontraPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM produto WHERE id = ?");
            List<Produto> produtos = jdbcTemplate.query(sql.toString(), new ProdutoRowMapper(), id);

            return produtos.size() > 0 ? produtos.get(0) : null;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deletaPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM produto WHERE id = ?");
            jdbcTemplate.update(sql.toString(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void atualizarPorId(Produto produto, Integer id) {
        try {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE produto SET nome = ?, descricao = ?, quantidade_disponivel = ?, valor_unitario = ? WHERE id = ?");
            jdbcTemplate.update(sqlBuilder.toString(), produto.getNome(), produto.getDescricao(), produto.getQuantidade_disponivel(), produto.getValor_unitario(), id);
        } catch (Exception e) {
            throw e;
        }
    }
}
