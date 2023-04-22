package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.DEPRECATEDservice.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class GetClientsServlet extends JsonServlet {

    private final ClientServiceImpl clientService;

    public GetClientsServlet(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(clientService.getClientListWithoutSorting());
    }
}