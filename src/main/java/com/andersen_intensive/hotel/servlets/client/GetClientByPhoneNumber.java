package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.DEPRECATEDservice.ClientServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetClientByPhoneNumber extends JsonServlet {

    private final ClientServiceImpl clientService;

    public GetClientByPhoneNumber(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
       String phoneNumber = uri.substring(uri.lastIndexOf('/') + 1);
       return new Response(clientService.getClientByPhoneNumber(phoneNumber));
    }
}
