package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetUtilityByIdServlet extends JsonServlet {

    private final UtilityServiceImpl utilityService;

    public GetUtilityByIdServlet(UtilityServiceImpl utilityService) {
        this.utilityService = utilityService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        int id = Integer.parseInt(uri.substring(uri.lastIndexOf('/')+1));
        return new Response(utilityService.getUtilityById(id));
    }
}
