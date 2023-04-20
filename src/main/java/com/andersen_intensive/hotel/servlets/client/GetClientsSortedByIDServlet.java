package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetClientsSortedByIDServlet extends JsonServlet {

    private final ClientServiceImpl clientService;

    public GetClientsSortedByIDServlet(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(clientService.getClientListSortedByID());
    }
}
