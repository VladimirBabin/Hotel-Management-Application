package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class AddClientServlet extends JsonServlet {

    private final ClientServiceImpl clientService;

    public AddClientServlet(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(clientService.createClient(body.get("firstName"), body.get("lastName"),
                body.get("phoneNumber"), Integer.parseInt(body.get("personalID"))));
    }
}