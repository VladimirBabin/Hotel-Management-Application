package com.andersen_intensive.hotel.servlets.utility;

import com.andersen_intensive.hotel.service.UtilityService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class UpdateUtilityPriceServlet extends JsonServlet {

    private final UtilityService utilityService;

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(utilityService.changePrice(
                Long.parseLong(body.get("id")),
                new BigDecimal(body.get("price"))
        ));
    }
}
