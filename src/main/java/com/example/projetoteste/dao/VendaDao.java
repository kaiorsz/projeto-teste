package com.example.projetoteste.dao;

import com.example.projetoteste.entity.Venda;
import com.example.projetoteste.jdbc.ConexaoJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class VendaDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public void delete(Venda venda) {
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM venda WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), venda.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Venda> encontrar(Integer page, Integer size, String sortBy, String sortOrder) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM venda");

            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new VendaRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

    public Venda atualizar(Venda venda) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE venda SET cliente = ?, valor_total = ? WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), venda.getCliente(), venda.getValor_total(), venda.getId());

            return venda;
        } catch (Exception e) {
            throw e;
        }
    }

    private class VendaRowMapper implements RowMapper<Venda> {
        @Override
        public Venda mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venda venda = new Venda();
            venda.setId(rs.getInt("id"));
            venda.setCliente(rs.getString("cliente"));
            venda.setValor_total(rs.getDouble("valor_total"));
            return venda;
        }
    }

    public Venda criar(Venda venda) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO venda (cliente, valor_total) VALUES (?, ?)");

            KeyHolder keyHolder = new GeneratedKeyHolder();
            conexaoJDBC.getJdbcTemplate().update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, venda.getCliente());
                ps.setBigDecimal(2, new BigDecimal(venda.getValor_total()));
                return ps;
            }, keyHolder);

            Integer codigoGerado = (Integer) keyHolder.getKeys().get("id");
            if (codigoGerado != null) {
                venda.setId(codigoGerado);
            } else {
                throw new RuntimeException("ID da venda não foi gerado.");
            }

            return venda;
        } catch (Exception e) {
            throw e;
        }
    }

    public Venda encontrarPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM venda WHERE id = ?");
            List<Venda> vendas = conexaoJDBC.getJdbcTemplate().query(sql.toString(), new VendaRowMapper(), id);

            return vendas.size() > 0 ? vendas.get(0) : null;
        } catch (Exception e) {
            throw e;
        }
    }
}
