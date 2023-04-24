package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GetClientsByIDServlet extends JsonServlet {

    private final ClientService clientService;

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        long id = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
        return new Response(clientService.findClientById(id));
    }
}