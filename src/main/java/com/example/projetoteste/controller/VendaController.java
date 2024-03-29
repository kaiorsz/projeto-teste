package com.example.projetoteste.controller;

import com.example.projetoteste.pojo.input.VendaProdutoDTO;
import com.example.projetoteste.pojo.output.VendaVO;
import com.example.projetoteste.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    VendaService vendaProdutoService;

    @Operation(summary = "Realizar uma venda")
    @PostMapping
    public ResponseEntity<Object> vende(@RequestBody List<VendaProdutoDTO> produtos,
                                         @RequestParam String cliente) {
        try {
            return ResponseEntity.ok(vendaProdutoService.realizaVenda(produtos, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todas as vendas")
    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(defaultValue = "asc") String sortOrder) {
        try {
            return ResponseEntity.ok(vendaProdutoService.findAll(page, size, sortBy, sortOrder));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody List<VendaProdutoDTO> vendaProduto, @PathVariable @Parameter(description = "Código da venda que deve ser alterada") Integer id) {
        try {
            return ResponseEntity.ok(vendaProdutoService.update(vendaProduto, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable @Parameter(description = "Código da venda que deve ser deletada") Integer id) {
        try {
            vendaProdutoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
