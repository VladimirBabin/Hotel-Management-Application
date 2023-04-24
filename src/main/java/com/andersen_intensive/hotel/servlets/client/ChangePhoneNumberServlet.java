package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ChangePhoneNumberServlet extends JsonServlet {

    private final ClientService clientService;

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(clientService.changePhoneNumber(
                Long.parseLong(body.get("id")),
                body.get("phoneNumber")
        ));
    }
}