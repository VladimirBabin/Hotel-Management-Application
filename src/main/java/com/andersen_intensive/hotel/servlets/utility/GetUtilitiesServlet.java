package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetUtilitiesServlet extends JsonServlet {

    private final UtilityServiceImpl utilityService;

    public GetUtilitiesServlet(UtilityServiceImpl utilityService) {
        this.utilityService = utilityService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(utilityService.showAllUtilities());
    }
}
