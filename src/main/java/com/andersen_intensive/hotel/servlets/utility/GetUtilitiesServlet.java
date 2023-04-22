package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GetUtilitiesServlet extends JsonServlet {

    private final UtilityService utilityService;

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(utilityService.showAll());
    }
}
