package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import java.util.Map;

public class GetClientByPhoneNumber extends JsonServlet {

    private final ClientService clientService;

    public GetClientByPhoneNumber(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
       String phoneNumber = uri.substring(uri.lastIndexOf('/') + 1);
       return new Response(clientService.findClientByPhoneNumber(phoneNumber));
    }
}