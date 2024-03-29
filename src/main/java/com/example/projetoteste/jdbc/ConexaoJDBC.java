package com.example.projetoteste.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public abstract class ConexaoJDBC {
    private static final String URL = "jdbc:postgresql://localhost:5432/bd_teste";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "testedb";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver"; // ou o driver do seu banco de dados

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            inicializarJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static void inicializarJdbcTemplate() {
        DataSource dataSource = criarDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static DataSource criarDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USUARIO);
        dataSource.setPassword(SENHA);
        return dataSource;
    }
}
