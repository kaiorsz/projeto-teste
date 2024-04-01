package com.example.projetoteste.controller;

import com.example.projetoteste.pojo.input.ProdutoDTO;
import com.example.projetoteste.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                          @RequestParam(defaultValue = "10", required = false) int size,
                                          @RequestParam(defaultValue = "id", required = false) String sortBy,
                                          @RequestParam(defaultValue = "asc", required = false) String sortOrder,
                                          @RequestParam(required = false, defaultValue = "false") boolean disponivel) {
        try {
            return ResponseEntity.ok(produtoService.findAll(page, size, sortBy, sortOrder, disponivel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Cadastrar um produto")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProdutoDTO produto) {
        try {
            produtoService.criaProduto(produto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um produto")
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody ProdutoDTO produto,
                                         @PathVariable @Parameter(description = "Código do produto que deve ser alterado") Integer id) {
        try {
            produtoService.update(produto, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Deletar um produto")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable @Parameter(description = "Código do produto a ser deletado") Integer id) {
        try {
            produtoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e.getMessage().contains("venda_produto_produto_fkey")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Produto não pode ser deletado pois está associado a uma venda.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Encontra por id")
    @GetMapping("{id}")
    public ResponseEntity<Object> encontraPorId(@PathVariable @Parameter(description = "Código do produto a ser encontrado") Integer id) {
        try {
            ;
            return ResponseEntity.ok(produtoService.encontraPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
