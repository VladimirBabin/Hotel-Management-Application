package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.math.BigDecimal;
import java.util.Map;

public class AddUtilitiesServlet extends JsonServlet {

    private final UtilityServiceImpl utilityService;

    public AddUtilitiesServlet(UtilityServiceImpl utilityService) {
        this.utilityService = utilityService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(utilityService.saveService(Integer.parseInt(body.get("id")),
                body.get("name"),
                new BigDecimal(body.get("price"))));
    }
}
