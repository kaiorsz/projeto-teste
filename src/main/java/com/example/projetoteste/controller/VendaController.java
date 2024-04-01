package com.example.projetoteste.controller;

import com.example.projetoteste.pojo.input.VendaDTO;
import com.example.projetoteste.pojo.input.VendaProdutoDTO;
import com.example.projetoteste.service.VendaService;
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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    VendaService vendaProdutoService;

    @Operation(summary = "Realizar uma venda")
    @PostMapping
    public ResponseEntity<Object> vende(@RequestBody VendaDTO vendaDTO) {
        try {
            return ResponseEntity.ok(vendaProdutoService.realizaVenda(vendaDTO.getVenda_produtos(), vendaDTO.getCliente()));
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

    @GetMapping("{id}")
    public ResponseEntity<Object> encontraPorId(@PathVariable @Parameter(description = "Código da venda") Integer id) {
        try {
            return ResponseEntity.ok(vendaProdutoService.encontraPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody VendaDTO venda, @PathVariable @Parameter(description = "Código da venda que deve ser alterada") Integer id) {
        try {
            return ResponseEntity.ok(vendaProdutoService.update(venda, id));
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
