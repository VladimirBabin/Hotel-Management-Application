package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.DEPRECATEDservice.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class RemoveClientServlet extends JsonServlet {
    private final ClientServiceImpl clientService;

    public RemoveClientServlet(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(clientService.removeClient(Integer.parseInt(body.get("personalID"))));
    }
}