# Projeto de Gerenciamento de Vendas

Este projeto consiste em um sistema de gerenciamento de vendas, desenvolvido em PostgreSQL, que inclui as tabelas de `produto`, `venda` e `venda_produto`.

## Como Rodar o Projeto

1. **Configura��o do Banco de Dados:**
    - Certifique-se de ter o PostgreSQL instalado em seu ambiente.
    - Execute as seguintes consultas SQL em seu banco de dados para criar as tabelas:

```sql
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL CHECK (nome <> ''),
    descricao VARCHAR(255),
    quantidade_disponivel INTEGER NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL
);

CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    cliente VARCHAR(255) NOT NULL CHECK (cliente <> ''),
    valor_total NUMERIC(10,2) NOT NULL
);

CREATE TABLE venda_produto (
    id SERIAL PRIMARY KEY,
    venda INTEGER REFERENCES venda(id) NOT NULL,
    produto INTEGER REFERENCES produto(id) NOT NULL,
    quantidade INTEGER NOT NULL
); 
```

2. **Configura��o do Projeto:**
    - Clone este reposit�rio em seu ambiente.
    - Abra o arquivo `config.py` e insira as credenciais do seu banco de dados.

3. **Execu��o do Projeto:**
    - Certifique-se de que todas as depend�ncias do projeto est�o instaladas.
    - Inicie o servidor.

4. **Acesso ao Swagger(Documenta��o da API):**
    - URL_DO_SEU_PROJETO/swagger-ui/index.html
    - URL padr�o: http://localhost:8080/swagger-ui/index.html
