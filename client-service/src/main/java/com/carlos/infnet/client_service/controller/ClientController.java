package com.carlos.infnet.client_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import com.carlos.infnet.client_service.model.Client;
import com.carlos.infnet.client_service.service.ClientService;

@RestController
@RequestMapping("/")
@Tag(name = "Client API", description = "API para gerenciar clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente e retorna o cliente criado")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    public ResponseEntity<?> create(
        @RequestBody @Parameter(description = "Cliente a ser criado") Client client) {
            Client saved = clientService.create(client);
            return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encontrar um cliente por ID", description = "Retorna o cliente com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public Optional<Client> findById(@PathVariable @Parameter(description = "ID do cliente") Long id) {
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um cliente por ID", description = "Exclui o cliente com o ID fornecido")
    @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public void delete(@PathVariable @Parameter(description = "ID do cliente") Long id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente", description = "Atualiza o cliente com o ID fornecido e retorna o cliente atualizado")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    public Client update(
        @PathVariable @Parameter(description = "ID do cliente") Long id, 
        @RequestBody @Parameter(description = "Cliente com as informações atualizadas") Client client) {
        return clientService.update(id, client);
    }
}
