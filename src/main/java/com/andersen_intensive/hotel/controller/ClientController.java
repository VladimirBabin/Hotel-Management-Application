package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("")
    public long addClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return client.getId();
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable long clientId) {
        return clientService.findClientById(clientId);
    }

    @PutMapping("")
    public int updateClient() {
        return 1;
    }

    @GetMapping("/sort/{sortType}")
    public List<Client> getClientSortedBy(@PathVariable String sortType) {
        return null;
    }

    @GetMapping("/all")
    public List<Client> getClientWithoutSorting() {
        return null;
    }
}
