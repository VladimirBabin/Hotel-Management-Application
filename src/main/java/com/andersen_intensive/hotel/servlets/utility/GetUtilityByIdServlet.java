package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GetUtilityByIdServlet extends JsonServlet {

    private final UtilityService utilityService;

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        Long id = Long.parseLong(uri.substring(uri.lastIndexOf('/')+1));
        return new Response(utilityService.findUtilityById(id));
    }
}
