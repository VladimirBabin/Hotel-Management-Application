package com.andersen_intensive.hotel.servlets.apartment;

import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class UpdateApartmentPriceServlet extends JsonServlet {
    private final ApartmentService apartmentService;
    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(apartmentService.changePrice(
                Long.parseLong(body.get("id")),
                new BigDecimal(body.get("price"))
        ));
    }
}
