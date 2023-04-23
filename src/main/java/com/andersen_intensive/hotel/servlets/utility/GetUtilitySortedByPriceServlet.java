package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GetUtilitySortedByPriceServlet extends JsonServlet {

    private final UtilityService utilityService;

    @Override
    public JsonServlet.Response get(String uri, Map<String, String[]> parameters) {
        return new JsonServlet.Response(utilityService.sortByPrice());
    }
}
