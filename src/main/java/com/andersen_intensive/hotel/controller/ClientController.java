package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @PostMapping("/")
    public int addClient() {
        return 1;
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable long clientId) {
        return null;
    }

    @PutMapping("/")
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
