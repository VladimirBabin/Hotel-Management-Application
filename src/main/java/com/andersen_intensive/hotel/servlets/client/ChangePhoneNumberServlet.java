package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class ChangePhoneNumberServlet extends JsonServlet {
    private final ClientService clientService;

    public ChangePhoneNumberServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(clientService.changePhoneNumber(
                Long.parseLong(body.get("id")),
                body.get("phoneNumber")
        ));
    }
}