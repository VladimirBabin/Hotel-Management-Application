package com.andersen_intensive.hotel.servlets.client;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RequiredArgsConstructor
public class AddClientServlet extends JsonServlet {

    private final ClientService clientService;

    @Override
    public Response post(String uri, Map<String, String> body) {
        Client client = new Client(body.get("first_name"),
                body.get("last_name"),
                body.get("phone_number"));
        return new Response(clientService.saveClient(client));
    }
}