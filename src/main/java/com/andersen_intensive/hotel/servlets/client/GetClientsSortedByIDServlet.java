package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class GetClientsSortedByIDServlet extends JsonServlet {

    private final ClientService clientService;

    public GetClientsSortedByIDServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(clientService.sortClientsByID());
    }
}